import { HttpHeaders } from "@angular/common/http";
import { InjectionToken } from "@angular/core"
import { Observable } from "rxjs";
import { User } from '../../model/user/user'

export const LOGIN_SERVICE = new InjectionToken<LoginService>('app.login-service');

export interface LoginService {

	readonly user: Observable<User | undefined>;

	login(user: User): void

	register(user: User): void

	logOut(): void

	isLoggedIn() : boolean

}
