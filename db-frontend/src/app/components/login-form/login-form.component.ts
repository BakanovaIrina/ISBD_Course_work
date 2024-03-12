import {Component, Inject, OnInit, ViewChild} from '@angular/core';

import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService, LOGIN_SERVICE } from '../../services/login/login';

@Component({
	selector: 'app-login-form',
	templateUrl: './login-form.component.html',
	styleUrls: ['./login-form.component.css'],
})
export class LoginFormComponent implements OnInit {

	public loginForm: FormGroup;

	constructor(
		@Inject(LOGIN_SERVICE) private loginService: LoginService,
		private fb: FormBuilder,
		private router: Router
	) { }

	ngOnInit(): void {
		this.loginForm = this.fb.group({
			login: new FormControl('', [
				Validators.required,
				Validators.minLength(5),
				Validators.maxLength(20)
			]),
			password: new FormControl('', [
				Validators.required,
				Validators.minLength(5),
				Validators.maxLength(20)
			]),
			updateOn: 'blur'
		})
	}

	sendLoginRequest() {
		const user = { username: this.loginForm.get('login')?.value, password: this.loginForm.get('password')?.value }
		this.loginService.login(user)
	}

	sendRegisterRequest() {
		const user = { username: this.loginForm.get('login')?.value, password: this.loginForm.get('password')?.value }
		this.loginService.register(user)
	}

}
