import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Point } from '../../model/area/point';
import { AreaResponse } from '../../model/area/response';
import { LoginService, LOGIN_SERVICE } from '../login/login';
import { AreaService } from './area-request.service';
import {Buffer} from "buffer";

@Injectable({
	providedIn: 'root'
})
export class HttpAreaService implements AreaService {

	constructor(
		private httpClient: HttpClient,
		@Inject(LOGIN_SERVICE) private loginService: LoginService
	) {
		this.getAreas()
	}

	readonly responses = new BehaviorSubject<AreaResponse[]>([])

	readonly r = new BehaviorSubject<number>(0)

	setR(r: number): void {
		this.r.next(r)
	}

	private getHeaders(): HttpHeaders {
		let headers = new HttpHeaders()

		headers = headers.set("Authorization", localStorage.getItem('user') + ":" + localStorage.getItem('password'))
		headers.append('Accept', 'application/json');
		headers.append('Content-Type', 'application/json');

		return headers
	}

	checkPoint(point: Point): boolean {

		const requestBody = { ...point};

		this.httpClient.post('http://localhost:8080/api/area/checkPoint', requestBody,
			{headers: this.getHeaders()})
			.subscribe(
				{
					next: (response: any) => {
						this.getAreas();
					},
					error: (response: any) => {
						alert("Ошибка")
					}
				}
			)

		return true
	}

	async getAreas() {
		/*
		this.httpClient.get<AreaResponse[]>('http://localhost:8080/api/area', {headers: this.getHeaders() })
			.subscribe((resp) => {
				//this.responses = resp;
				alert("getAreas")
				//alert(resp[0].x)
				this.responses.next(resp)
				alert(<AreaResponse>resp[0])
			})

		 */

		const headers = this.getHeaders();

		this.httpClient.get<AreaResponse[]>('http://localhost:8080/api/area/areas', { headers })
			.subscribe((resp) => this.responses.next(resp))

	}



}
