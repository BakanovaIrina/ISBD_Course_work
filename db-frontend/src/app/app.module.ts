import { HttpClientModule } from "@angular/common/http";
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ButtonModule } from 'primeng/button';
import {Checkbox, CheckboxModule} from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';
import { MessageModule } from 'primeng/message';
import { MessagesModule } from 'primeng/messages';
import { MultiSelectModule } from 'primeng/multiselect';
import { ListboxModule } from "primeng/listbox";
import { PasswordModule } from 'primeng/password';
import { SliderModule } from 'primeng/slider';
import {InputNumberModule} from "primeng/inputnumber";
import { SidebarModule } from 'primeng/sidebar';
import { MenubarModule } from 'primeng/menubar';
import { TabMenuModule } from 'primeng/tabmenu';

import { AreaCanvasComponent } from './components/area-canvas/area-canvas.component';
import { AreaFormComponent } from './components/area-form/area-form.component';
import { HeaderComponent } from './components/header/header.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { MainPageComponent } from './components/main-page/main-page.component';
import { StartPageComponent } from './components/start-page/start-page.component';
import { SantaPageComponent } from "./components/santa-page/santa-page.component";

import { AreaTableComponent } from './components/area-table/area-table.component';
import { NotFoundPageComponent } from './components/not-found-page/not-found-page.component';
import { LogoutComponent } from './components/logout/logout.component';
import  {SantaMenuComponent} from "./components/santa-menu/santa-menu.component";

import { AREA_REQUEST_SERVICE } from './services/area/area-request.service';
import { LOGIN_SERVICE } from './services/login/login';
import {HttpAreaService} from "./services/area/http-area-request.service";
import {HttpLoginService} from "./services/login/http-login.service";
import {ElfPageComponent} from "./components/elf-page/elf-page.component";
import {HttpTablesService} from "./services/tables/http-tables.service";
import {ActionTableComponent} from "./components/action-table/action-table.component";
import {TABLE_REQUEST_SERVICE} from "./services/tables/tables-service";
import {AddressTableComponent} from "./components/address_table/address-table.component";
import {LetterTableComponent} from "./components/letters-table/letter-table.component";
import {ChildTableComponent} from "./components/child-table/child-table.component";
import {DeliveryTableComponent} from "./components/delivery-table/delivery-table.component";
import {ElfTableComponent} from "./components/elf_table/elf-table.component";
import {ElfProdTableComponent} from "./components/elf-prod-table/elf-prod-table.component";
import {ElfStatusTableComponent} from "./components/elf_status_table/elf-status-table.component";
import {GiftTableComponent} from "./components/gift-table/gift-table.component";
import {GiftStatusTableComponent} from "./components/gift-status-table/gift-status-table.component";
import {ProductionTableComponent} from "./components/production_table/production-table.component";
import {StorageResponse} from "./model/table/storage";
import {StorageTableComponent} from "./components/storage/storage-table.component";
import {ElfMenuComponent} from "./components/elf-menu/elf-menu.component";
import {SantaFuncMenuComponent} from "./components/santa-func-menu/santa-func-menu.component";
import {AddLetterComponent} from "./components/add-letter/add-letter.component";
import {TriStateCheckboxModule} from "primeng/tristatecheckbox";
import {TransferToProductionComponent} from "./components/transfer-to-production/transfer-to-production.component";
import {AssignElfFormComponent} from "./components/assign-elf-form/assign-elf-form.component";
import {MoveFormComponent} from "./components/move-form/move-form.component";
import {CompleteProdComponent} from "./components/complete-prod-form/complete-prod.component";

@NgModule({
    declarations: [
        AppComponent,
        StartPageComponent,
        MainPageComponent,
        HeaderComponent,
        LoginFormComponent,
        AreaFormComponent,
        AreaCanvasComponent,
        AreaTableComponent,
		ActionTableComponent,
		AddressTableComponent,
		LetterTableComponent,
		ChildTableComponent,
		DeliveryTableComponent,
		ElfTableComponent,
		ElfProdTableComponent,
		ElfStatusTableComponent,
		GiftTableComponent,
		GiftStatusTableComponent,
		ProductionTableComponent,
		StorageTableComponent,
		ElfMenuComponent,
        NotFoundPageComponent,
        LogoutComponent,
		SantaPageComponent,
		ElfPageComponent,
		SantaMenuComponent,
		SantaFuncMenuComponent,
		AddLetterComponent,
		TransferToProductionComponent,
		AssignElfFormComponent,
		MoveFormComponent,
		CompleteProdComponent
    ],
	imports: [
		BrowserModule,
		AppRoutingModule,
		BrowserAnimationsModule,
		FormsModule,
		ReactiveFormsModule,
		SliderModule,
		ButtonModule,
		InputTextModule,
		PasswordModule,
		MultiSelectModule,
		ListboxModule,
		MessagesModule,
		MessageModule,
		HttpClientModule,
		CheckboxModule,
		InputNumberModule,
		SidebarModule,
		MenubarModule,
		TabMenuModule,
		CheckboxModule,
		TriStateCheckboxModule
	],
	providers: [
		{ provide: LOGIN_SERVICE, useClass: HttpLoginService},
		{ provide: AREA_REQUEST_SERVICE, useClass: HttpAreaService},
		{ provide: TABLE_REQUEST_SERVICE, useClass: HttpTablesService}
	],
	bootstrap: [AppComponent]
})
export class AppModule { }
