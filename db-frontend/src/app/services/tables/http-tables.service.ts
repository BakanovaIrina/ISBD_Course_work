import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

import { TablesService, TABLE_REQUEST_SERVICE} from '../tables/tables-service';
import {ActionResponse} from "../../model/table/action";
import {LOGIN_SERVICE, LoginService} from "../login/login";
import {AddressResponse} from "../../model/table/address";
import {LetterResponse} from "../../model/table/letter";
import {ChildResponse} from "../../model/table/child";
import {DeliveryResponse} from "../../model/table/delivery";
import {ElfResponse} from "../../model/table/elf";
import {ElfProdResponse} from "../../model/table/elf_production";
import {ElfStatusResponse} from "../../model/table/elf_status";
import {GiftResponse} from "../../model/table/gift";
import {GiftStatusResponse} from "../../model/table/gift_status";
import {ProductionResponse} from "../../model/table/production";
import {StorageResponse} from "../../model/table/storage";
import {Point} from "../../model/area/point";
import {AddLetter} from "../../model/table/addLetter";
import {TransferToProd} from "../../model/table/transferToProd";
import {AssignElfResponse} from "../../model/table/assignElf";

@Injectable({
    providedIn: 'root'
})
export class HttpTablesService implements TablesService {

    constructor(
        private httpClient: HttpClient,
        @Inject(LOGIN_SERVICE) private loginService: LoginService
    ) {
        this.getAction()
        this.getAddress()
        this.getLetters()
    }

    readonly actions = new BehaviorSubject<ActionResponse[]>([])
    readonly addresses = new BehaviorSubject<AddressResponse[]>([])
    readonly letters = new BehaviorSubject<LetterResponse[]>([])
    readonly children = new BehaviorSubject<ChildResponse[]>([])
    readonly deliveries = new BehaviorSubject<DeliveryResponse[]>([])
    readonly elves = new BehaviorSubject<ElfResponse[]>([])
    readonly elves_prod = new BehaviorSubject<ElfProdResponse[]>([])
    readonly elves_status = new BehaviorSubject<ElfStatusResponse[]>([])
    readonly gifts = new BehaviorSubject<GiftResponse[]>([])
    readonly gift_statuses = new BehaviorSubject<GiftStatusResponse[]>([])
    readonly productions = new BehaviorSubject<ProductionResponse[]>([])

    readonly storages = new BehaviorSubject<StorageResponse[]>([])

    private getHeaders(): HttpHeaders {
        let headers = new HttpHeaders()

        headers = headers.set("Authorization", localStorage.getItem('user') + ":" + localStorage.getItem('password'))
        headers.append('Accept', 'application/json');
        headers.append('Content-Type', 'application/json');

        return headers;
    }


    async getAction(){
        const headers = this.getHeaders();

        this.httpClient.get<ActionResponse[]>('http://localhost:8080/api/santa/actions', { headers })
            .subscribe((resp) => this.actions.next(resp))

    }

    async getAddress(){
        const headers = this.getHeaders();
        this.httpClient.get<AddressResponse[]>('http://localhost:8080/api/santa/address', { headers })
            .subscribe((resp) => this.addresses.next(resp))
    }

    async getLetters(){
        const headers = this.getHeaders();
        this.httpClient.get<LetterResponse[]>('http://localhost:8080/api/santa/letters', { headers })
            .subscribe((resp) => this.letters.next(resp))
    }

    async getChildren(){
        const headers = this.getHeaders();
        this.httpClient.get<ChildResponse[]>('http://localhost:8080/api/santa/children', { headers })
            .subscribe((resp) => this.children.next(resp))
    }

    async getDeliveries(){
        const headers = this.getHeaders();
        this.httpClient.get<DeliveryResponse[]>('http://localhost:8080/api/shared/deliveries', { headers })
            .subscribe((resp) => this.deliveries.next(resp))
    }

    async getElves(){
        const headers = this.getHeaders();
        this.httpClient.get<ElfResponse[]>('http://localhost:8080/api/shared/elves', { headers })
            .subscribe((resp) => this.elves.next(resp))
    }

    async getElvesStatus(){
        const headers = this.getHeaders();
        this.httpClient.get<ElfStatusResponse[]>('http://localhost:8080/api/shared/elf_status', { headers })
            .subscribe((resp) => this.elves_status.next(resp))
    }

    async getElvesProd(){
        const headers = this.getHeaders();
        this.httpClient.get<ElfProdResponse[]>('http://localhost:8080/api/shared/elf_production', { headers })
            .subscribe((resp) => this.elves_prod.next(resp))
    }

    async getGifts(){
        const headers = this.getHeaders();
        this.httpClient.get<GiftResponse[]>('http://localhost:8080/api/shared/gifts', { headers })
            .subscribe((resp) => this.gifts.next(resp))
    }

    async getGiftStatus(){
        const headers = this.getHeaders();
        this.httpClient.get<GiftStatusResponse[]>('http://localhost:8080/api/shared/gift_status', { headers })
            .subscribe((resp) => this.gift_statuses.next(resp))
    }

    async getStorage(){
        const headers = this.getHeaders();
        this.httpClient.get<StorageResponse[]>('http://localhost:8080/api/shared/storages', { headers })
            .subscribe((resp) => this.storages.next(resp))
    }

    async getProduction(){
        const headers = this.getHeaders();
        this.httpClient.get<ProductionResponse[]>('http://localhost:8080/api/shared/productions', { headers })
            .subscribe((resp) => this.productions.next(resp))
    }

    addLetter(letter: AddLetter): boolean {

        const requestBody = { ...letter};

        this.httpClient.post('http://localhost:8080/api/santa/letter', requestBody,
            {headers: this.getHeaders()})
            .subscribe(
                {
                    next: (response: any) => {
                        this.getLetters();
                        this.getAddress();
                        this.getAction();
                        this.getChildren();
                        this.getGifts();
                        this.getGiftStatus();
                    },
                    error: (response: any) => {
                        alert("Ошибка")
                    }
                }
            )

        return true
    }

    addToProd(transferToProd: TransferToProd): boolean {


        const requestBody = { ...transferToProd};

        this.httpClient.post('http://localhost:8080/api/santa/production/' + transferToProd.giftId, requestBody,
            {headers: this.getHeaders()})
            .subscribe(
                {
                    next: (response: any) => {
                        this.getGiftStatus();
                        this.getProduction();
                    },
                    error: (response: any) => {
                        alert("Ошибка")
                    }
                }
            )

        return true
    }

    assignElf(assignElfResponse: AssignElfResponse): boolean {
        
        const requestBody = { ...assignElfResponse};

        this.httpClient.post('http://localhost:8080/api/santa/assign_elf', requestBody,
            {headers: this.getHeaders()})
            .subscribe(
                {
                    next: (response: any) => {
                        this.getElvesProd();
                        this.getElvesStatus();
                        this.getProduction();
                    },
                    error: (response: any) => {
                        alert("Ошибка")
                    }
                }
            )

        return true
    }

    moveGifts(): boolean {

        this.httpClient.post('http://localhost:8080/api/santa/gifts/deliver', {},
            {headers: this.getHeaders()})
            .subscribe(
                {
                    next: (response: any) => {
                        this.getGiftStatus();
                        this.getGifts();
                        this.getDeliveries();
                    },
                    error: (response: any) => {
                        alert("Ошибка")
                    }
                }
            )

        return true
    }

    completeProd(transferToProd: TransferToProd): boolean {


        const requestBody = { ...transferToProd};

        this.httpClient.post('http://localhost:8080/api/elf/productions/' + transferToProd.giftId + "/complete", requestBody,
            {headers: this.getHeaders()})
            .subscribe(
                {
                    next: (response: any) => {
                        this.getGiftStatus();
                        this.getProduction();
                    },
                    error: (response: any) => {
                        alert("Ошибка")
                    }
                }
            )

        return true
    }

}
