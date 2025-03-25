import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Inject, Injectable} from '@angular/core';
import { Buffer } from 'buffer';
import { BehaviorSubject } from 'rxjs';
import { User } from "../../model/user/user";
import { LoginService } from './login';
import {tap} from "rxjs/operators";
import {RegisterMessage} from "../../model/user/register-message";
import {Router} from "@angular/router";
import {data} from "autoprefixer";


@Injectable({
	providedIn: 'root'
})
export class HttpLoginService implements LoginService {

	readonly user = new BehaviorSubject<User | undefined>(undefined);

	constructor(
		private http: HttpClient,
		private router: Router
	) {
	}

	private isLogged = false;

	login(user: User): void {
		this.http.post<RegisterMessage>('http://localhost:8080/api/auth/login', user, {headers: this.getHeaders(user)})
			.subscribe({
				next: (data) => {
					//localStorage.setItem('password', (<RegisterMessage>data).message);
					localStorage.setItem('password', user.password);
					localStorage.setItem('user', user.username);
					this.isLogged = true;

					if(localStorage.getItem('user') == 'santa'){
						this.router.navigate(['santa']).then(r => {})
					}
					else {
						this.router.navigate(['elf']).then(r => {})
					}


					//this.router.navigate(['main']).then(r => {})
				},
				error: (data) => {
					alert("Пользователь не прошел авторизацию!")
				}
			})
	}

		register(user: User): void {

			const userWithRole = {
				username: user.username,
				password: user.password,
				role: 'ELF'
			};

			this.http.post<RegisterMessage>('http://localhost:8080/api/auth/register', userWithRole, {headers: this.getHeaders(user)})
				.subscribe(
					{
					next: (data) => {
						//localStorage.setItem('password', (<RegisterMessage>data).message);
						localStorage.setItem('password', user.password);
						localStorage.setItem('user', user.username);
						this.isLogged = true;
						this.router.navigate(['main']).then(r => {})
					},
					error: (data) => {
						alert("Выберете другой пароль и логин")
					}
				})
	}

	logOut(): void {
		this.isLogged = false;
		this.user.next(undefined)
	}

	isLoggedIn() {
		return this.isLogged;
	}

	private getHeaders(user: User): HttpHeaders {

		let headers = new HttpHeaders()

		headers = headers.set("Authorization", user.username + ":" + user.password)
		headers.append('Accept', 'application/json');
		headers.append('Content-Type', 'application/json');

		return headers;
	}

}
