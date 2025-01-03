import { Component } from '@angular/core';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/components/footer/footer.component';
import { Router, RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-funcionario',
  standalone: true,
  imports: [HeaderComponent, FooterComponent, RouterOutlet, RouterLink],
  templateUrl: './funcionario.component.html',
  styleUrl: './funcionario.component.scss'
})
export class FuncionarioComponent { 
  mostrarEstoque: boolean = true;
  mostrarForm: boolean = false;

  constructor(private router: Router){

  }

  toggleEstoque(){
    this.mostrarEstoque = !this.mostrarEstoque
  }

  toggleForm(){
    this.mostrarForm = !this.mostrarForm;
  }


}
