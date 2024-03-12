import {Inject, Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import { LoginService, LOGIN_SERVICE } from "./login";

@Injectable({
    providedIn: 'root'
})
export class IsLoggedIn implements CanActivate {

    private isLoggedIn = false

    constructor(
        @Inject(LOGIN_SERVICE) private loginService: LoginService,
        private router: Router
    ) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
        if (this.loginService.isLoggedIn()) {
            return true;
        } else {
            this.router.navigate(['/login'])
            return false
        }
    }

}
