import { Component } from '@angular/core';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/components/footer/footer.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-funcionario',
  standalone: true,
  imports: [HeaderComponent, FooterComponent],
  templateUrl: './funcionario.component.html',
  styleUrl: './funcionario.component.scss'
})
export class FuncionarioComponent { 

  constructor(private router: Router){
    
  }
}
