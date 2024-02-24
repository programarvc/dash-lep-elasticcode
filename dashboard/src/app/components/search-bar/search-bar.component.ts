import { Component, OnChanges, OnInit,Output,EventEmitter, Input} from '@angular/core';

@Component({

  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.sass'],


})


export class SearchBarComponent implements OnInit, OnChanges {
  @Input() phrases: string[] = [];
  @Output() TextoPesquisa = new EventEmitter<string>();

  searchTerm: string = '';

  constructor() {}

  ngOnInit(): void {}
  ngOnChanges(): void {}

  sendMessage(event:any) {
    this.TextoPesquisa.emit(event.target.value)
  }



}
