import { Component, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { AreaResponse } from '../../model/area/response';
import { TablesService, TABLE_REQUEST_SERVICE } from '../../services/tables/tables-service';
import {ActionResponse} from "../../model/table/action";
import {AreaService} from "../../services/area/area-request.service";
import {ElfResponse} from "../../model/table/elf";
``
@Component({
	selector: 'app-elf-status-table',
	templateUrl: './elf-status-table.component.html',
	styleUrls: ['./elf-status-table.component.css']
})
export class ElfStatusTableComponent {

	responses: Observable<ElfResponse[]>

	constructor(
		@Inject(TABLE_REQUEST_SERVICE) public tableService: TablesService
	) {
		tableService.getElvesStatus()
	}

	ngAfterViewInit() {
	}
}
