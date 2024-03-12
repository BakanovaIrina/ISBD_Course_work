import {Component, Inject} from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { AreaService, AREA_REQUEST_SERVICE } from '../../services/area/area-request.service';
import {TABLE_REQUEST_SERVICE, TablesService} from "../../services/tables/tables-service";


@Component({
	selector: 'add-letter-form',
	templateUrl: './add-letter.component.html',
	styleUrls: ['./add-letter.component.css']
})
export class AddLetterComponent {

	public childName: string;
	public childSurname: string;
	public country1: string;
	public region1: string;
	public city1: string;
	public street1: string;
	public house1: string;
	public room1: number;
	public giftName: string;
	public actions: string;
	public descriptions: string;
	public truth1: boolean;
	public approval1: boolean;
	public positivities: boolean;

	constructor(
		@Inject(TABLE_REQUEST_SERVICE)
		private tableService: TablesService
	) {
		tableService.getLetters();
		tableService.getChildren();
		tableService.getAddress();
		tableService.getAction();
		tableService.getGifts();
		tableService.getGiftStatus();
	}

	checkLetter(): void {
		const letter = {
			childName: this.childName,
			childSurname: this.childSurname,
			country1: this.country1,
			region1: this.region1,
			city1: this.city1,
			street1: this.street1,
			house1: this.house1,
			room1: this.room1,
			giftName: this.giftName,
			actions: this.actions,
			descriptions: this.descriptions,
			truth1: this.truth1,
			approval1: this.approval1,
			positivities: this.positivities
		};

		const res = this.tableService.addLetter(letter);
	}


}
