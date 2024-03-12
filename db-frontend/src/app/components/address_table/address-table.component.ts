import { Component, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { AreaResponse } from '../../model/area/response';
import { TablesService, TABLE_REQUEST_SERVICE } from '../../services/tables/tables-service';
import {ActionResponse} from "../../model/table/action";
import {AreaService} from "../../services/area/area-request.service";
import {AddressResponse} from "../../model/table/address";
``
@Component({
	selector: 'app-address-table',
	templateUrl: './address-table.component.html',
	styleUrls: ['./address-table.component.css']
})
export class AddressTableComponent {

	responses: Observable<AddressResponse[]>

	constructor(
		@Inject(TABLE_REQUEST_SERVICE) public tableService: TablesService
	) {
		tableService.getAddress()
	}

	ngAfterViewInit() {
	}
}
