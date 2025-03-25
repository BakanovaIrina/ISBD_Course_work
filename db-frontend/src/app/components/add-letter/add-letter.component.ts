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
	public country: string;
	public region: string;
	public city: string;
	public street: string;
	public house: string;
	public room: number;
	public giftName: string;
	public actions: string;
	public descriptions: string;
	public truth: boolean;
	public approval: boolean;
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
			country: this.country,
			region: this.region,
			city: this.city,
			street: this.street,
			house: this.house,
			room: this.room,
			giftName: this.giftName,
			actions: this.actions,
			descriptions: this.descriptions,
			truth: this.truth,
			approval: this.approval,
			positivities: this.positivities
		};

		const res = this.tableService.addLetter(letter);
	}


}
