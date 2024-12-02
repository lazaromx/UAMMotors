import { Component, OnInit } from '@angular/core';
import { Veiculo } from '../../models/Veiculo';
import { VeiculoService } from '../../services/veiculo.service';
import { VeiculoComponent } from "../../shared/components/veiculo/veiculo.component";
import { Router } from '@angular/router';

@Component({
  selector: 'app-estoque',
  standalone: true,
  imports: [VeiculoComponent],
  templateUrl: './estoque.component.html',
  styleUrl: './estoque.component.scss'
})
export class EstoqueComponent implements OnInit {
  veiculos: Veiculo[] = [];

  constructor(private service: VeiculoService, private router: Router){}
  ngOnInit(){
    this.service.exibir().subscribe(retorno => this.veiculos = retorno.sort((a, b) => {
      if (a.status === "estoque" && b.status !== "estoque") {
          return -1; // a vem antes de b
      } else if (a.status !== "estoque" && b.status === "estoque") {
          return 1; // b vem antes de a
      } else {
          return 0; // mantém a ordem original entre itens com o mesmo status
      }
  }));
  }  

  onReservar(veiculoId: number): void{
    this.router.navigate(['detalhes', veiculoId]);
    // this.service.comprar(veiculo).subscribe(retorno => this.veiculos = retorno);
  }
}
