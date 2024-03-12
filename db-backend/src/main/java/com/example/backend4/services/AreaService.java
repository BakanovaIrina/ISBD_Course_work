package com.example.backend4.services;

import com.example.backend4.config.AreaChecker;
import com.example.backend4.model.request.AreaCheckRequest;
import com.example.backend4.model.response.AreaCheckResponse;
import com.example.backend4.model.AreaFunction;
import com.example.backend4.model.auth.User;
import com.example.backend4.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AreaService {

    @Autowired
    private AreaRepository areaRepository;

    public AreaCheckResponse handleRequest(AreaCheckRequest request, long userId) {
        long startTimeNano = System.nanoTime();

        double x = request.getX();
        double y = request.getY();
        double r = request.getR();

        AreaCheckResponse response = new AreaCheckResponse(x, y, r,
                checkForHit(x, y, r, AreaChecker.getAreas()),
                System.nanoTime() - startTimeNano, userId);

        areaRepository.save(response);
        return response;
    }

    public List<AreaCheckResponse> getUserAreas(User owner) {
        return areaRepository.findAllByOwnerId(owner.getId());
    }


    private boolean checkForHit(double x, double y, double r, Collection<AreaFunction> areas) {
        if (areas.isEmpty()) {
            throw new IllegalArgumentException("Area collection must not be empty");
        }

        return areas.stream().anyMatch(area -> area.inArea(x, y, r));
    }
}
