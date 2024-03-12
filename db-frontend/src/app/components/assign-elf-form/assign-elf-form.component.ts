import {Component, Inject} from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { AreaService, AREA_REQUEST_SERVICE } from '../../services/area/area-request.service';
import {TABLE_REQUEST_SERVICE, TablesService} from "../../services/tables/tables-service";


@Component({
	selector: 'assign-elf-form',
	templateUrl: './assign-elf-form.component.html',
	styleUrls: ['./assign-elf-form.component.css']
})
export class AssignElfFormComponent {

	public idElf: number;
	public idProd: number;

	constructor(
		@Inject(TABLE_REQUEST_SERVICE)
		private tableService: TablesService
	) {
		tableService.getElvesProd();
		tableService.getElvesStatus();
		tableService.getProduction();
	}

	checkLetter(): void {
		const assignElf = {
			idElf: this.idElf,
			idProduction: this.idProd
		};

		const res = this.tableService.assignElf(assignElf);
	}


}
