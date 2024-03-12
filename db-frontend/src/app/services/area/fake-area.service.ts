import {Injectable} from "@angular/core";
import {AreaService} from "./area-request.service";
import {AreaResponse} from "../../model/area/response";
import {BehaviorSubject} from "rxjs";
import {Point} from "../../model/area/point";

@Injectable({
    providedIn: 'root'
})
export class FakeAreaService implements AreaService {

    private responsesArray: AreaResponse[]
    readonly responses: BehaviorSubject<AreaResponse[]>

    private rVal: number
    readonly r: BehaviorSubject<number>

    constructor() {
        this.responsesArray = []
        this.rVal = 0

        for (let i = 0; i < 5; i++) {
            this.responsesArray.push(this.createRandomResponse())
        }

        this.responses = new BehaviorSubject(this.responsesArray)
        this.r = new BehaviorSubject(this.rVal)
    }

    setR(r: number): void {
        this.r.next(r)
        this.rVal = r
    }

    checkPoint(point: Point): boolean {
        if (this.rVal <= 0) {
            return false
        }

        const resp = this.createRandomResponse()

        this.responsesArray.push({
            x: point.x,
            y: point.y,
            r: this.rVal,
            inArea: resp.inArea,
            completionTime: resp.completionTime,
        })

        this.responses.next(this.responsesArray)
        return true
    }

    private createRandomResponse(): AreaResponse {
        return {
            x: Math.random() * 8 - 4,
            y: Math.random() * 10 - 5,
            r: Math.round(Math.random() * 4) + 1,
            inArea: Math.random() <= 0.5,
            completionTime: Math.random() * Math.pow(10, 9),
        }
    }

    getAreas(): void {
    }
}
