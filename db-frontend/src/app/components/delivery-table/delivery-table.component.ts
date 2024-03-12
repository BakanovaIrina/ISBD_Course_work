import { Component, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { AreaResponse } from '../../model/area/response';
import { TablesService, TABLE_REQUEST_SERVICE } from '../../services/tables/tables-service';
import {ActionResponse} from "../../model/table/action";
import {AreaService} from "../../services/area/area-request.service";
import {AddressResponse} from "../../model/table/address";
import {DeliveryResponse} from "../../model/table/delivery";
``
@Component({
	selector: 'app-delivery-table',
	templateUrl: './delivery-table.component.html',
	styleUrls: ['./delivery-table.component.css']
})
export class DeliveryTableComponent {

	responses: Observable<DeliveryResponse[]>

	constructor(
		@Inject(TABLE_REQUEST_SERVICE) public tableService: TablesService
	) {
		tableService.getDeliveries()
	}

	ngAfterViewInit() {
	}
}
