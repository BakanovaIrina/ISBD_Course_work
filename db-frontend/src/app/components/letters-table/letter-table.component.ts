import { Component, Inject } from '@angular/core';
import {count, map, Observable} from 'rxjs';
import { AreaResponse } from '../../model/area/response';
import { TablesService, TABLE_REQUEST_SERVICE } from '../../services/tables/tables-service';
import {ActionResponse} from "../../model/table/action";
import {AreaService} from "../../services/area/area-request.service";
import {AddressResponse} from "../../model/table/address";
import {LetterResponse} from "../../model/table/letter";
``
@Component({
	selector: 'app-letter-table',
	templateUrl: './letter-table.component.html',
	styleUrls: ['./letter-table.component.css']
})
export class LetterTableComponent {

	responses: Observable<LetterResponse[]>

	constructor(
		@Inject(TABLE_REQUEST_SERVICE) public tableService: TablesService
	) {
		tableService.getLetters()
		/*
		this.tableService.letters.pipe(
			map(letters => letters.map(letter => letter.idChild)), // получить массив имен объектов
		).subscribe(letter => {
			alert(letter)
		});

		 */
	}

	ngAfterViewInit() {
	}
}
