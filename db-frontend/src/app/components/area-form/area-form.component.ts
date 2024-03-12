import {Component, Inject} from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { AreaService, AREA_REQUEST_SERVICE } from '../../services/area/area-request.service';


@Component({
	selector: 'app-area-form',
	templateUrl: './area-form.component.html',
	styleUrls: ['./area-form.component.css']
})
export class AreaFormComponent {

	public xList: number[] = [-5, -4, -3, -2, -1, 0, 1, 2, 3];
	public xValue: number;

	public yValue: number = 0;

	public rList: number[] = [-5, -4, -3, -2, -1, 0, 1, 2, 3];
	public rValue: number = 0;

	constructor(
		@Inject(AREA_REQUEST_SERVICE)
		private areaService: AreaService
	) {
		areaService.getAreas();
	}

	checkPoint(): void {
		if(this.rValue < 0){
			alert("Вы ввели отрицательный радиус! Измените свой выбор")
			return;
		}
		if (this.yValue >= 3 || this.yValue <= -3){
			alert("Значение y допускается только (-3;3)!")
			return;
		}
		const res = this.areaService.checkPoint({
			x: this.xValue,
			y: this.yValue,
			r: this.rValue
		})
	}

}
