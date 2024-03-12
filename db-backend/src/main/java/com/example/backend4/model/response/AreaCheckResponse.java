package com.example.backend4.model.response;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "areas")
public class AreaCheckResponse {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	private double x;
	private double y;
	private double r;
	private boolean inArea;
	private long completionTime;
	private long ownerId;

	public AreaCheckResponse() {}

	public AreaCheckResponse(double x, double y, double r, boolean result,
							 long completionTime, long userId) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.inArea = result;
		this.completionTime = completionTime;
		ownerId = userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, x, y, r, inArea, completionTime, ownerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AreaCheckResponse other = (AreaCheckResponse) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(r) != Double.doubleToLongBits(other.r))
			return false;
		if (inArea != other.inArea)
			return false;
		if (completionTime != other.completionTime)
			return false;
		if(ownerId != other.ownerId)
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

	public boolean isInArea() {
		return inArea;
	}

	public void setInArea(boolean inArea) {
		this.inArea = inArea;
	}

	public long getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(long completionTime) {
		this.completionTime = completionTime;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	@Override
	public String toString() {
		return "AreaCheckResponse{" +
				"id=" + id +
				", x=" + x +
				", y=" + y +
				", r=" + r +
				", inArea=" + inArea +
				", completionTime=" + completionTime +
				", ownerId=" + ownerId +
				'}';
	}
}
