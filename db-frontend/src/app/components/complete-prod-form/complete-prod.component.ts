import {Component, Inject} from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { AreaService, AREA_REQUEST_SERVICE } from '../../services/area/area-request.service';
import {TABLE_REQUEST_SERVICE, TablesService} from "../../services/tables/tables-service";


@Component({
	selector: 'complete-prod-form',
	templateUrl: './complete-prod.component.html',
	styleUrls: ['./complete-prod.component.css']
})
export class CompleteProdComponent {

	public giftId: number;

	constructor(
		@Inject(TABLE_REQUEST_SERVICE)
		private tableService: TablesService
	) {
		tableService.getGiftStatus();
		tableService.getProduction();
	}

	checkLetter(): void {
		const tr = {
			giftId: this.giftId
		};

		const res = this.tableService.completeProd(tr);
	}

}
