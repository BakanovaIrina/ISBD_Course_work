import { Component, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { AreaResponse } from '../../model/area/response';
import { TablesService, TABLE_REQUEST_SERVICE } from '../../services/tables/tables-service';
import {ActionResponse} from "../../model/table/action";
import {AreaService} from "../../services/area/area-request.service";
import {ElfResponse} from "../../model/table/elf";
import {GiftStatusResponse} from "../../model/table/gift_status";
``
@Component({
	selector: 'app-gift-status-table',
	templateUrl: './gift-status-table.component.html',
	styleUrls: ['./gift-status-table.component.css']
})
export class GiftStatusTableComponent {

	responses: Observable<GiftStatusResponse[]>

	constructor(
		@Inject(TABLE_REQUEST_SERVICE) public tableService: TablesService
	) {
		tableService.getGiftStatus()
	}

	ngAfterViewInit() {
	}
}
