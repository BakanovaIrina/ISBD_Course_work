import { Component, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { AreaResponse } from '../../model/area/response';
import { TablesService, TABLE_REQUEST_SERVICE } from '../../services/tables/tables-service';
import {ActionResponse} from "../../model/table/action";
import {AreaService} from "../../services/area/area-request.service";
import {ElfResponse} from "../../model/table/elf";
import {ProductionResponse} from "../../model/table/production";
import {StorageResponse} from "../../model/table/storage";
``
@Component({
	selector: 'app-storage-table',
	templateUrl: './storage-table.component.html',
	styleUrls: ['./storage-table.component.css']
})
export class StorageTableComponent {

	responses: Observable<StorageResponse[]>

	constructor(
		@Inject(TABLE_REQUEST_SERVICE) public tableService: TablesService
	) {
		tableService.getStorage()
	}

	ngAfterViewInit() {
	}
}
