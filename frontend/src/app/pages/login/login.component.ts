import { Component, CUSTOM_ELEMENTS_SCHEMA, ElementRef, OnInit, viewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import Swiper from 'swiper';

import {Cliente} from '../../models/Cliente'
import { ClienteService } from '../../services/cliente.service';

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

  public cliente = new Cliente();
  repetirSenha = '';
  senhaMismatch = false;

  // loginForm!: FormGroup

  constructor(private clienteService:ClienteService){
    
  }
  ngOnInit(): void{
    // this.loginForm = new FormGroup({
    //   nome: new FormControl('', [Validators.required]),
    //   sobrenome: new FormControl('', [Validators.required]),
    //   email: new FormControl('', [Validators.required]),
    //   senha: new FormControl('',[Validators.required]),
    //   telefone: new FormControl(''),
    //   cpf: new FormControl('', [Validators.required])
    // });
    console.log("login~onInit()");
  }

  // getNome(){
  //   return this.loginForm.get('nome');
  // }
  // getSobrenome(){
  //   return this.loginForm.get('sobrenome')!;
  // }
  // getEmail(){
  //   return this.loginForm.get('email')!;
  // }
  // getSenha(){
  //   return this.loginForm.get('senha')!;
  // }
  // getCpf(){
  //   return this.loginForm.get('cpf')!;
  // }

  // selecionar():void{
  //   this.servico.selecionar().subscribe(retorno => this.clientes = retorno);
  // }


  // clientes = {
  //   nome: '',
  //   sobrenome: '',
  //   email: '',
  //   senha:'',
  //   telefone:'',
  //   cpf: ''
  // };

  validarCpf(cpf: string) {
    
  }


  verificarSenha(){
    this.senhaMismatch = this.cliente.senha !=this.repetirSenha;
  }

  onLogin() {
    console.log('Formulário enviado:', this.cliente);
    alert('Formulário enviado com sucesso!');
  }

  onCadastrar() {
    this.verificarSenha();
    console.log('Formulário enviado:', this.cliente);
    this.clienteService.cadastrar(this.cliente).subscribe(retorno => {
      console.log('Retorno do servidor:', retorno);
    });
  }

  submit(){
    console.log('enviou formulário')
  }
}
