import { Component } from '@angular/core';

@Component({
    selector: 'santa-menu',
    templateUrl: './santa-menu.component.html',
    styleUrls: ['./santa-menu.component.css']
})
export class SantaMenuComponent {
    public action_hidden: boolean = true;
    public address_hidden: boolean = true;
    public letter_hidden: boolean = true;
    public child_hidden: boolean = true;
    public production_hidden: boolean = true;
    public delivery_hidden: boolean = true;
    public elf_hidden: boolean = true;
    public gift_hidden: boolean = true;
    public storage_hidden: boolean = true;
    public elf_production_hidden: boolean = true;
    public elf_status_hidden: boolean = true;
    public gift_status_hidden: boolean = true;

    tables: any[] = [
        { label: 'Letter', command: () => {this.offAll();
                this.letter_hidden = false} },
        { label: 'Address', command: () => {this.offAll();
                                            this.address_hidden = false} },
        { label: 'Action' , command: () => {this.offAll();
                this.action_hidden = false}},
        { label: 'Child' , command: () => {this.offAll();
                this.child_hidden = false}},
        { label: 'Gift' , command: () => { this.offAll();
                this.gift_hidden = false }},
        { label: 'Gift_status' , command: () => { this.offAll();
                this.gift_status_hidden = false }},
        { label: 'Elf' , command: () => { this.offAll();
                this.elf_hidden = false }},
        { label: 'Elf_production' , command: () => { this.offAll();
                this.elf_production_hidden = false }},
        { label: 'Elf_status' , command: () => { this.offAll();
                this.elf_status_hidden = false }},
        { label: 'Production' , command: () => { this.offAll();
                this.production_hidden = false }},
        { label: 'Delivery' , command: () => { this.offAll();
                this.delivery_hidden = false }},
        { label: 'Storage' , command: () => { this.offAll();
                this.storage_hidden = false }}
    ];

    offAll(){
        this.action_hidden = true;
        this.address_hidden = true;
        this.letter_hidden = true;
        this.child_hidden = true;
        this.production_hidden = true;
        this.delivery_hidden = true;
        this.elf_hidden = true;
        this.gift_hidden = true;
        this.storage_hidden = true;
        this.elf_production_hidden = true;
        this.elf_status_hidden = true;
        this.gift_status_hidden = true;
    }
}

