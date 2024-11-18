import { Component, CUSTOM_ELEMENTS_SCHEMA, ElementRef, OnInit, viewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators, FormBuilder} from '@angular/forms';
import Swiper from 'swiper';

import {Cliente, Usuario} from '../../models/Cliente'
import { ClienteService } from '../../services/cliente.service';
import { catchError } from 'rxjs';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LoginComponent implements OnInit {
  swiperElement = viewChild<ElementRef>('swiper');

  public showBackSlide = false;
  public slideOptions = {
    initialSlide: 0,
    speed: 150
  };
  
  async onSlideChange(event: any) {
    const slides: Swiper = event.target;
    this.showBackSlide = !slides.isBeginning;
  }

  get slide(): Swiper {
    return this.swiperElement()?.nativeElement.swiper;
  }

  cadastroForm!: FormGroup;
  public validationMessages = {
    'nome': [
      { type: 'required', message: 'Nome é Obrigatório.' }
    ],
    'sobrenome': [
      { type: 'required', message: 'Sobrenome é Obrigatório.' }
    ],
    'email': [
      { type: 'required', message: 'Email é Obrigatório.' },
      { type: 'email', message: 'Email inválido.' }
    ],
    'senha': [
      { type: 'required', message: 'Senha é Obrigatório.' },
      { type: 'minlength', message: 'A senha deve ter pelo menos 8 caracteres.' }
    ],
    'confirmarSenha': [
      { type: 'required', message: 'Confirmar senha é Obrigatório.' },
    ],
    'telefone': [
      { type: 'required', message: 'Obrigatório.' }
    ],
    'cpf': [
      { type: 'required', message: 'Obrigatório.' }
    ]
  }

  constructor(
    private clienteService:ClienteService,
    private formBuilder: FormBuilder
  ){
    
  }
  ngOnInit(): void{
    // this.cadastroForm = new FormGroup({
    //   nome: new FormControl('', [Validators.required]),
    //   sobrenome: new FormControl('', [Validators.required]),
    //   email: new FormControl('', [Validators.required, Validators.email]),
    //   senha: new FormControl('',[Validators.required]),
    //   telefone: new FormControl(''),
    //   cpf: new FormControl('', [Validators.required])
    // });
    this.resetForm();
    console.log("login~onInit()");
  }

  private resetForm(){
    this.cadastroForm = this.formBuilder.group({
      nome: new FormControl('', [Validators.required]),
      sobrenome: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      senha: new FormControl('',[Validators.required, Validators.minLength(8)]),
      confirmarSenha: new FormControl('',[Validators.required]),
      telefone: new FormControl(''),
      cpf: new FormControl('', [Validators.required])
    })
  }

  hasInputError(fieldName: string, validType: any) {
    const field = this.cadastroForm.get(fieldName)!;
    return field.hasError(validType) && (field.dirty || field.touched);
  }


  validarCpf(cpf: string) {
    
  }

  onLogin() {
    alert('Formulário enviado com sucesso!');
  }

  async submit(values: Cliente){
    if(values.senha !== values.confirmarSenha){
      alert('Senhas diferentes');
      return;
    }
    console.log('enviado:', values);
      this.clienteService.cadastrar(values).pipe(catchError(error => {
        console.log(error);
        return [];
      }))
  }
}
