import { Component } from '@angular/core';
import { SearchComponent } from '../search/search.component';
import { RouterOutlet } from '@angular/router';
import { Routes } from '@angular/router';


@Component({
  selector: 'app-header',
  standalone: true,
  imports: [SearchComponent, RouterOutlet],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {

}
