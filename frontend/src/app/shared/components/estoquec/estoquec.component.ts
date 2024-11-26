import { Component } from '@angular/core';
import { Veiculo } from '../../../models/Veiculo';
import { VeiculoService } from '../../../services/veiculo.service';
import { environment } from '../../../../environments/environment';

@Component({
  selector: 'app-estoquec',
  standalone: true,
  imports: [],
  templateUrl: './estoquec.component.html',
  styleUrl: './estoquec.component.scss'
})
export class EstoquecComponent {
  veiculos: Veiculo[] = [];

  constructor(private service: VeiculoService){

  }

  exibirVeiculos(): void{
    this.service.exibir().subscribe(retorno => this.veiculos = retorno);
  }

  formatarPreco(preco: number){
    return new Intl.NumberFormat('pt-Br').format(preco);
  }

  ngOnInit(){
    this.exibirVeiculos();
  }
}
