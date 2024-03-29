import { Component, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { AreaResponse } from '../../model/area/response';
import { AreaService, AREA_REQUEST_SERVICE } from '../../services/area/area-request.service';
``
@Component({
	selector: 'app-area-table',
	templateUrl: './area-table.component.html',
	styleUrls: ['./area-table.component.css']
})
export class AreaTableComponent {

	responses: Observable<AreaResponse[]>

	constructor(
		@Inject(AREA_REQUEST_SERVICE) public areaService: AreaService
	) { }

	ngAfterViewInit() {
	}
}
