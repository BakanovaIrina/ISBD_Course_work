import { Component, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { AreaResponse } from '../../model/area/response';
import { TablesService, TABLE_REQUEST_SERVICE } from '../../services/tables/tables-service';
import {ActionResponse} from "../../model/table/action";
import {AreaService} from "../../services/area/area-request.service";
import {ElfResponse} from "../../model/table/elf";
``
@Component({
	selector: 'app-elf-prod-table',
	templateUrl: './elf-prod-table.component.html',
	styleUrls: ['./elf-prod-table.component.css']
})
export class ElfProdTableComponent {

	responses: Observable<ElfResponse[]>

	constructor(
		@Inject(TABLE_REQUEST_SERVICE) public tableService: TablesService
	) {
		tableService.getElvesProd()
	}

	ngAfterViewInit() {
	}
}
