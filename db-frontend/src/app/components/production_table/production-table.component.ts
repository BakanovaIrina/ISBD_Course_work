import { Component, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { AreaResponse } from '../../model/area/response';
import { TablesService, TABLE_REQUEST_SERVICE } from '../../services/tables/tables-service';
import {ActionResponse} from "../../model/table/action";
import {AreaService} from "../../services/area/area-request.service";
import {ElfResponse} from "../../model/table/elf";
import {ProductionResponse} from "../../model/table/production";
``
@Component({
	selector: 'app-production-table',
	templateUrl: './production-table.component.html',
	styleUrls: ['./production-table.component.css']
})
export class ProductionTableComponent {

	responses: Observable<ProductionResponse[]>

	constructor(
		@Inject(TABLE_REQUEST_SERVICE) public tableService: TablesService
	) {
		tableService.getProduction()
	}

	ngAfterViewInit() {
	}
}
