import { Component, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { AreaResponse } from '../../model/area/response';
import { TablesService, TABLE_REQUEST_SERVICE } from '../../services/tables/tables-service';
import {ActionResponse} from "../../model/table/action";
import {AreaService} from "../../services/area/area-request.service";
``
@Component({
	selector: 'app-action-table',
	templateUrl: './action-table.component.html',
	styleUrls: ['./action-table.component.css']
})
export class ActionTableComponent {

	responses: Observable<ActionResponse[]>

	constructor(
		@Inject(TABLE_REQUEST_SERVICE) public tableService: TablesService
	) {
		tableService.getAction()
	}

	ngAfterViewInit() {
	}
}
