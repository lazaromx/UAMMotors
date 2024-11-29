import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Veiculo } from '../../../models/Veiculo';
import { VeiculoService } from '../../../services/veiculo.service';
import { RouterLink } from '@angular/router';
import { environment } from '../../../../environments/environment';


@Component({
  selector: 'app-estoquec',
  standalone: true,
  imports: [FormsModule, SearchComponent, RouterLink],
  templateUrl: './estoquec.component.html',
  styleUrl: './estoquec.component.scss'
})
export class EstoquecComponent {
  veiculos: Veiculo[] = [];
  search: any;

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
}import { SearchComponent } from '../search/search.component';

