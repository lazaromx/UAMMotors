import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { pipe } from 'rxjs';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [FormsModule, ],
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss'
})
export class SearchComponent {
  search: any;
}

