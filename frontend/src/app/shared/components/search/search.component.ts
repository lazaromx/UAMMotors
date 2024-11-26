import { Component } from '@angular/core';
import { NgModule } from '@angular/core';
import { BrowserModule  } from '@angular/platform-browser';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [BrowserModule, ],
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss'
})
export class SearchComponent {
  search: any;
}

