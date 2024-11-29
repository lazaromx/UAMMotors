import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { SearchComponent } from '../search/search.component';
import { RouterOutlet } from '@angular/router';
import { Routes } from '@angular/router';


@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  @Input() btnLogin: string = '';
}
