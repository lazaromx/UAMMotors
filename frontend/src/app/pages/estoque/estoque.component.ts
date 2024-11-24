import { Component } from '@angular/core';
import { Veiculo } from '../../models/Veiculo';
import { VeiculoService } from '../../services/veiculo.service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-estoque',
  standalone: true,
  imports: [NgFor],
  templateUrl: './estoque.component.html',
  styleUrl: './estoque.component.scss'
})
export class EstoqueComponent {
  veiculos: Veiculo[] = [];

  constructor(private service: VeiculoService){

  }

  exibirVeiculos(): void{
    this.service.exibir().subscribe(retorno => this.veiculos = retorno);
  }

  ngOnInit(){
    this.exibirVeiculos();
  }
}
