import { InjectionToken } from "@angular/core";
import { Observable } from "rxjs";
import { Point } from '../../model/area/point';
import { AreaResponse } from "../../model/area/response";

export const AREA_REQUEST_SERVICE = new InjectionToken<AreaService>('app.area-request-service');

export interface AreaService {

	readonly responses: Observable<AreaResponse[]>

	readonly r: Observable<number>

	setR(r: number): void

	checkPoint(point: Point): boolean
	getAreas():void;
}
