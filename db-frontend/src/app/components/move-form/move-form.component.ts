import {Component, Inject} from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { AreaService, AREA_REQUEST_SERVICE } from '../../services/area/area-request.service';
import {TABLE_REQUEST_SERVICE, TablesService} from "../../services/tables/tables-service";


@Component({
	selector: 'move-form',
	templateUrl: './move-form.component.html',
	styleUrls: ['./move-form.component.css']
})
export class MoveFormComponent {

	constructor(
		@Inject(TABLE_REQUEST_SERVICE)
		private tableService: TablesService
	) {
		tableService.getGiftStatus();
		tableService.getGifts();
		tableService.getDeliveries();
	}

	checkLetter(): void {

		const res = this.tableService.moveGifts();
	}


}
