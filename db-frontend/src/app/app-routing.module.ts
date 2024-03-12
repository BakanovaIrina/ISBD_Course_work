import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainPageComponent } from './components/main-page/main-page.component';
import { NotFoundPageComponent } from './components/not-found-page/not-found-page.component';
import { StartPageComponent } from './components/start-page/start-page.component';
import {IsLoggedIn} from "./services/login/is-logged-in";
import {SantaPageComponent} from "./components/santa-page/santa-page.component";
import {ElfPageComponent} from "./components/elf-page/elf-page.component";

const routes: Routes = [
	{ path: '', component: StartPageComponent },
	{ path: 'main', component: MainPageComponent, canActivate: [IsLoggedIn]},
	{ path: '404', component: NotFoundPageComponent },
	{ path: 'santa', component: SantaPageComponent, canActivate: [IsLoggedIn]},
	{ path: 'elf', component: ElfPageComponent, canActivate: [IsLoggedIn]}

];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule { }
