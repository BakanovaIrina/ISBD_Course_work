import { Component, Inject } from '@angular/core';
import {map, Observable} from 'rxjs';
import { AreaResponse } from '../../model/area/response';
import { TablesService, TABLE_REQUEST_SERVICE } from '../../services/tables/tables-service';
import {ActionResponse} from "../../model/table/action";
import {AreaService} from "../../services/area/area-request.service";
import {ChildResponse} from "../../model/table/child";
``
@Component({
	selector: 'app-child-table',
	templateUrl: './child-table.component.html',
	styleUrls: ['./child-table.component.css']
})
export class ChildTableComponent {

	responses: Observable<ChildResponse[]>

	constructor(
		@Inject(TABLE_REQUEST_SERVICE) public tableService: TablesService
	) {
		tableService.getChildren()
		this.tableService.children.pipe(
			map(children => children.map(letter => letter.name)), // получить массив имен объектов
		).subscribe(letter => {
			//alert(letter)
		});
	}

	ngAfterViewInit() {
	}
}
