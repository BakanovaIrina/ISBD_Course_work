import { Component } from '@angular/core';

@Component({
    selector: 'santa-func-menu',
    templateUrl: './santa-func-menu.component.html',
    styleUrls: ['./santa-func-menu.component.css']
})
export class SantaFuncMenuComponent {
    public letter_hidden: boolean = true;
    public production_hidden: boolean = true;
    public delivery_hidden: boolean = true;
    public elf_hidden: boolean = true;

    tables: any[] = [
        { label: 'Добавить новое письмо', command: () => {this.offAll();
                this.letter_hidden = false} },
        { label: 'Перенести подарок в производство', command: () => {this.offAll();
                                            this.production_hidden = false} },
        { label: 'Назначить эльфа на производства' , command: () => { this.offAll();
                this.elf_hidden = false }},
        { label: 'Переместить подарки в сани' , command: () => { this.offAll();
                this.delivery_hidden = false }}
    ];

    offAll(){
        this.letter_hidden = true;
        this.production_hidden = true;
        this.delivery_hidden = true;
        this.elf_hidden = true;
    }
}

