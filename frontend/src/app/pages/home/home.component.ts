import { Component } from '@angular/core';
// import { HeaderComponent } from '../../shared/components/header/header.component';
// import { FooterComponent } from '../../shared/components/footer/footer.component';
// import { SearchComponent } from '../../shared/components/search/search.component';
import { BannerComponent } from '../../shared/components/banner/banner.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [BannerComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

}
