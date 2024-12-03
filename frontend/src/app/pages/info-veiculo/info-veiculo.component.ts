import { Component, OnInit } from '@angular/core';
import { VeiculoService } from '../../services/veiculo.service';
import { Veiculo } from '../../models/Veiculo';
import { ActivatedRoute, RouterOutlet } from '@angular/router';
import { Observable } from 'rxjs';
import { HeaderComponent } from '../../shared/components/header/header.component';
import { FooterComponent } from '../../shared/components/footer/footer.component';


@Component({
  selector: 'app-info-veiculo',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, FooterComponent],
  templateUrl: './info-veiculo.component.html',
  styleUrl: './info-veiculo.component.scss'
})
export class InfoVeiculoComponent implements OnInit{
  veiculo: Veiculo = new Veiculo(); 
  veiculos: Veiculo[] = [];
  constructor(
    private service: VeiculoService,
    private route: ActivatedRoute
  ) {} 

  ngOnInit(){
    const veiculoId = Number(this.route.snapshot.paramMap.get('id'));
    this.service.exibirPorId(veiculoId).subscribe(retorno => this.veiculo = retorno);
  }

  reservar(){
    this.service.editarStatus(this.veiculo.id, this.veiculo).subscribe(
      (retorno) => {
        // this.veiculo.status = 'vendido';
        console.log("Retorno do servidor:", retorno);
      },
      (error) => {
        console.log("Erro: ", error);
      }
    );
  }
}
