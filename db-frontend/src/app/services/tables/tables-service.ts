import { InjectionToken } from "@angular/core";
import {BehaviorSubject, Observable} from "rxjs";
import { Point } from '../../model/area/point';
import { AreaResponse } from "../../model/area/response";
import {ActionResponse} from "../../model/table/action";
import {AddressResponse} from "../../model/table/address";
import {LetterResponse} from "../../model/table/letter";
import {ChildResponse} from "../../model/table/child";
import {DeliveryResponse} from "../../model/table/delivery";
import {ElfResponse} from "../../model/table/elf";
import {ElfStatusResponse} from "../../model/table/elf_status";
import {ElfProdResponse} from "../../model/table/elf_production";
import {GiftResponse} from "../../model/table/gift";
import {GiftStatusResponse} from "../../model/table/gift_status";
import {StorageResponse} from "../../model/table/storage";
import {ProductionResponse} from "../../model/table/production";
import {AddLetter} from "../../model/table/addLetter";
import {TransferToProd} from "../../model/table/transferToProd";
import {AssignElfResponse} from "../../model/table/assignElf";

export const TABLE_REQUEST_SERVICE = new InjectionToken<TablesService>('app.tables-request-service');

export interface TablesService {

    readonly actions: Observable<ActionResponse[]>
    readonly addresses: Observable<AddressResponse[]>
    readonly letters: Observable<LetterResponse[]>
    readonly children :Observable<ChildResponse[]>
    readonly deliveries :Observable<DeliveryResponse[]>
    readonly elves :Observable<ElfResponse[]>
    readonly elves_prod :Observable<ElfProdResponse[]>
    readonly elves_status :Observable<ElfStatusResponse[]>
    readonly gifts :Observable<GiftResponse[]>
    readonly gift_statuses :Observable<GiftStatusResponse[]>
    readonly productions :Observable<ProductionResponse[]>
    readonly storages :Observable<StorageResponse[]>
    
    getAction():void;
    getAddress():void;
    getLetters(): void;
    getChildren(): void;
    getDeliveries(): void;
    getElves(): void;
    getElvesStatus(): void;
    getElvesProd(): void;
    getGifts(): void;
    getGiftStatus(): void;
    getStorage(): void;
    getProduction(): void;

    addLetter(letter: AddLetter):boolean;
    addToProd(transferToProd: TransferToProd):boolean
    assignElf(assignElfResponse: AssignElfResponse): boolean
    moveGifts(): boolean
    completeProd(transferToProd: TransferToProd):boolean
}
