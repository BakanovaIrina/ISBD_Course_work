import {AfterViewInit, Component, ElementRef, Inject, Input, OnChanges, SimpleChanges, ViewChild} from '@angular/core';
import { Point } from '../../model/area/point';
import { AreaResponse } from '../../model/area/response';
import { AreaService, AREA_REQUEST_SERVICE } from '../../services/area/area-request.service';

import type { } from "css-font-loading-module";


@Component({
	selector: 'app-area-canvas',
	templateUrl: './area-canvas.component.html',
	styleUrls: ['./area-canvas.component.css']
})
export class AreaCanvasComponent implements AfterViewInit {

	@ViewChild('canvas', { static: true })
	canvas: ElementRef<HTMLCanvasElement>

	rValue: number = 0;
	public rList: number[] = [0, 1, 2, 3];

	private ctx: CanvasRenderingContext2D
	private width: number
	private height: number
	private pointSize: number = 5
	
	private rValueResponses: AreaResponse[]

	private restrictions: { x: { min: number, max: number }, y: { min: number, max: number } }
	private canvasCenter: { x: number, y: number }

	constructor(
		@Inject(AREA_REQUEST_SERVICE) private areaService: AreaService
	) { }

	ngAfterViewInit() {
		this.ctx = this.canvas.nativeElement.getContext('2d')!

		this.width = this.canvas.nativeElement.width
		this.height = this.width

		this.restrictions = {
			x: {
				min: (this.canvas.nativeElement.offsetWidth - this.canvas.nativeElement.width) * 0.5,
				max: this.canvas.nativeElement.offsetWidth -
					(this.canvas.nativeElement.offsetWidth - this.canvas.nativeElement.width) * 0.5,
			},
			y: {
				min: (this.canvas.nativeElement.offsetHeight - this.canvas.nativeElement.height) * 0.5,
				max: this.canvas.nativeElement.offsetHeight -
					(this.canvas.nativeElement.offsetHeight - this.canvas.nativeElement.height) * 0.5
			}
		}

		this.canvasCenter = {
			x: this.canvas.nativeElement.offsetWidth * 0.5,
			y: this.canvas.nativeElement.offsetHeight * 0.5,
		}

		this.areaService.responses.subscribe((responses) => {
			this.rValueResponses = responses
			this.redraw();
		})

		this.clearCanvas();

		this.areaService.r.subscribe((r) => {
			if (r > 0) {
				this.rValue = r
			}
		})

		this.drawAxis();
		this.drawFigures(this.rValue)
	}

	redraw(){
		this.clearCanvas();
		this.drawFigures(this.rValue);
		this.drawAxis();
		this.drawAllResponses();
	}

	private drawAllResponses() {
		for (let response of this.rValueResponses) {
			let color = response.inArea ? "rgb(37, 255, 182)" : "rgb(216, 83, 87)";

			const point = { x: response.x, y: response.y, r: response.r }

			if(this.rValue == response.r){
				this.drawPoint(point, color)
			}
		}
	}

	private drawPoint(point: Point, color: string) {
		const oldColor = this.ctx.fillStyle

		this.ctx.fillStyle = color
		const coords = this.pointCoordsToCanvasCoords(point);

		this.ctx.beginPath()
		this.ctx.arc(coords.x, coords.y, this.pointSize, 0, Math.PI * 2)
		this.ctx.fill()

		this.ctx.fillStyle = oldColor
	}

	private clearCanvas() {
		this.ctx.clearRect(0, 0, this.width, this.height);
		this.ctx.restore();
	}

	private drawAxis() {
		const step = this.width/12;
		const width = this.width;
		const height = this.height;
		//Сами оси
		this.ctx.beginPath();
		this.ctx.lineWidth = 2;
		this.ctx.strokeStyle = "black";

		this.ctx.moveTo(0, height / 2);
		this.ctx.lineTo(width, height / 2);

		this.ctx.moveTo(width / 2, height);
		this.ctx.lineTo(width / 2, 0);

		this.ctx.moveTo(width / 2, 0);
		this.ctx.lineTo(width / 2 - 5, 15);
		this.ctx.moveTo(width / 2, 0);
		this.ctx.lineTo(width / 2 + 5, 15);

		this.ctx.moveTo(width, height / 2);
		this.ctx.lineTo(width - 15, height / 2 - 5);
		this.ctx.moveTo(width, height / 2);
		this.ctx.lineTo(width - 15, height / 2 + 5);
		this.ctx.stroke();
		this.ctx.closePath();

		//Текст
		this.ctx.fillStyle = "#000000";
		this.ctx.font = '10pt Arial'

		this.ctx.fillText("Y", width / 2 + 5, 10);
		this.ctx.fillText("X", width - 10, height / 2 - 5);

		for (let i = -5; i <= 5; i++) {
			this.ctx.fillText(String(-i), width / 2 + 5, step * (i + 6));
			this.ctx.fillText(String(i),step * (i + 6), height / 2 - 5);
		}

	}

	private drawFigures(r: number){
		r = r * 50;
		const width = this.width;
		const height = this.height;

		this.ctx.fillStyle = '#5D2DF4';
		this.ctx.strokeStyle = '#5D2DF4';
		this.ctx.beginPath();

		//Прямоугольник
		this.ctx.fillRect(width / 2 , height / 2 - r / 4, r / 2, r / 4);

		//Четверть круга
		this.ctx.arc(width / 2, height / 2, r / 4, Math.PI, 3 * Math.PI / 2);
		this.ctx.lineTo(width / 2, height / 2)

		//Треугольник
		this.ctx.moveTo(width / 2  + r / 4, height / 2);
		this.ctx.lineTo(width / 2 , height / 2 + r / 2 );
		this.ctx.lineTo(width / 2, height / 2);
		this.ctx.fill();

		this.ctx.stroke();
	}

	private pointCoordsToCanvasCoords(point: Point): { x: number, y: number} {
		return {
			x: this.canvasCenter.x - this.restrictions.x.min + point.x  * (this.width * 0.08),
			y: this.canvasCenter.y - this.restrictions.y.min - point.y  * (this.height * 0.08)
		}
	}

	private canvasCoordsToPointCoords(x: number, y: number, r: number): Point {
		return {
			x: x / (this.width * 0.08),
			y: y / (this.height * 0.08),
			r: r
		}
	}

	sendReq(event: MouseEvent) {
		const canvasCoords = {
			x: event.offsetX - this.canvasCenter.x,
			y: -(event.offsetY - this.canvasCenter.y)
		}

		if (event.offsetX < this.restrictions.x.min || event.offsetX > this.restrictions.x.max || event.offsetY < this.restrictions.y.min || event.offsetY > this.restrictions.y.max) {
			return
		}


		const point = this.canvasCoordsToPointCoords(canvasCoords.x, canvasCoords.y, this.rValue)

		this.areaService.checkPoint(point)
	}
}
