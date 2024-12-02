import { Component } from '@angular/core';
import { EstoquecComponent } from '../../../shared/components/estoquec/estoquec.component';
import { VeiculoComponent } from '../../../shared/components/veiculo/veiculo.component';

@Component({
  selector: 'app-estoque-func',
  standalone: true,
  imports: [EstoquecComponent, VeiculoComponent],
  templateUrl: './estoque-func.component.html',
  styleUrl: './estoque-func.component.scss'
})
export class EstoqueFuncComponent {
}
