import { Component, OnInit } from '@angular/core';
import { VeiculoService } from '../../services/veiculo.service';
import { Veiculo } from '../../models/Veiculo';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-info-veiculo',
  standalone: true,
  imports: [],
  templateUrl: './info-veiculo.component.html',
  styleUrl: './info-veiculo.component.scss'
})
export class InfoVeiculoComponent implements OnInit{
  veiculo: Veiculo = new Veiculo(); 
  constructor(
    private service: VeiculoService,
    private route: ActivatedRoute
  ) {}  

  
  

  ngOnInit(){
    const veiculoId = Number(this.route.snapshot.paramMap.get('id'));
    this.service.exibirPorId(veiculoId).subscribe(retorno => this.veiculo = retorno);
  }
}
