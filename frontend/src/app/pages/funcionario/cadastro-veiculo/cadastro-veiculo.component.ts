import { Component } from '@angular/core';
import { Veiculo } from '../../../models/Veiculo';
import { FormBuilder, FormControl, FormGroup, FormsModule, Validators, ReactiveFormsModule } from '@angular/forms';
import { core } from '@angular/compiler';
import { VeiculoService } from '../../../services/veiculo.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cadastro-veiculo',
  standalone: true,
  imports: [FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './cadastro-veiculo.component.html',
  styleUrl: './cadastro-veiculo.component.scss'
})
export class CadastroVeiculoComponent {
  veiculoForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private veiculoService: VeiculoService
  ){

  }

  private resetForm(){
    this.veiculoForm = this.formBuilder.group({
      marca: new FormControl('', [Validators.required]),
      modelo: new FormControl('', [Validators.required]),
      ano: new FormControl('', [Validators.required]),
      cor: new FormControl("", [Validators.required]),
      preco: new FormControl('', [Validators.required]),
      imagem: new FormControl('')
    })
  }

  ngOnInit(): void {
    this.resetForm();
  }
  cadastrarVeiculo(values: Veiculo){
    this.veiculoService.cadastrar(values).subscribe(
      retorno => {
        console.log("Retorno do servidor:", retorno);
        alert("Veiculo Cadastrado")
        this.resetForm();
      }
    )
  }
}
