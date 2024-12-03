import { Component, CUSTOM_ELEMENTS_SCHEMA, ElementRef, OnInit, Renderer2, viewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators, FormBuilder} from '@angular/forms';
import { Router } from '@angular/router';
import Swiper from 'swiper';


import {  Cliente, Usuario} from '../../models/Cliente'
import {  Funcionario} from '../../models/Funcionario'
import {  ClienteService } from '../../services/cliente.service';
import { FuncionarioService } from '../../services/funcionario.service';
import { catchError } from 'rxjs';
import { AutorizacaoService } from '../../services/autorizacao.service';
import { HttpErrorResponse } from '@angular/common/http';

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
  loginForm!: FormGroup;
  funcionarioForm!: FormGroup;

  private addHide = false;

  public validationMessages = {
    'nome': [
      { type: 'required', message: 'Nome é Obrigatório.' }
    ],
    'sobrenome': [
      { type: 'required', message: 'Sobrenome é Obrigatório.' }
    ],
    'email': [
      { type: 'required', message: 'Email é Obrigatório.' },
      { type: 'email', message: 'Email inválido.' },
      { type: 'exists', message: 'Email ja cadastrado.' },
    ],
    'senha': [
      { type: 'required', message: 'Senha é Obrigatório.' },
      { type: 'minlength', message: 'A senha deve ter pelo menos 8 caracteres.' },
    ],
    'confirmarSenha': [
      { type: 'required', message: 'Confirmar senha é Obrigatório.' },
    ],
    'telefone': [
      { type: 'required', message: 'Obrigatório.' }
    ],
    'cpf': [
      { type: 'required', message: 'Cpf é Obrigatório.' },
      { type: 'minlength', message: 'Cpf invalido.' },

    ]
  }

  constructor(
    private clienteService:ClienteService,
    private funcionarioService: FuncionarioService,
    private formBuilder: FormBuilder,
    private router: Router,
    private renderer: Renderer2,
    private elementRef: ElementRef,
    private autorizacaoService: AutorizacaoService,
  ){ }
  ngOnInit(): void{
    this.resetForm();
  }

  private resetForm(){
    this.loginForm = this.formBuilder.group({
      email: new FormControl('', [Validators.required]),
      senha: new FormControl('', [Validators.required])
    })

    this.cadastroForm = this.formBuilder.group({
      nome: new FormControl('', [Validators.required]),
      sobrenome: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      senha: new FormControl('',[Validators.required, Validators.minLength(8)]),
      confirmarSenha: new FormControl('',[Validators.required]),
      telefone: new FormControl(''),
      cpf: new FormControl('', [Validators.required, Validators.minLength(11)])
    })

    this.funcionarioForm = this.formBuilder.group({
      usuario: new FormControl('', [Validators.required]),
      senha: new FormControl('', [Validators.required])
    })
  }

  hasInputError(fieldName: string, validType: any) {
    const field = this.cadastroForm.get(fieldName)!;
    return field.hasError(validType) && (field.dirty || field.touched);
  }


  validarCpf(cpf: string) {
    
  }

  cadastro(values: Cliente){
    
    this.clienteService.cadastrar(values).subscribe(
      retorno => {
        console.log('Retorno do servidor:', retorno);
        this.autorizacaoService.autorizar();
        this.router.navigate(['estoque']);
        this.resetForm();
      }, 
      (error) => {
        console.log("Erro: ", error)
        if(error.status === 409 && error.error.includes("Email já cadastrado")){
          alert("Este e-mail já está em uso. Por favor, escolha outro.");
          return;
        }
        
        if(values.senha !== values.confirmarSenha){
          alert('Senhas diferentes');
        }
        if(error.status === 409 && error.error.includes("Cpf já cadastrado")){
          alert("Este cpf já está em uso.");
          return;
        }
      }
    );
  }

  obterDescricaoLogin(){
    if(this.autorizacaoService.obterLoginStatus()){
      return "Estou autorizado"
    }
    else{
      return "Não estou autorizado"
    }
  }

  loginCliente(values: Cliente){

    this.clienteService.Login(values.email, values.senha).subscribe(  
      (retorno) => {
        this.clienteService.cliente = retorno;
        
        console.log('Retorno do servidor:', retorno)
        this.autorizacaoService.autorizar();
        this.router.navigate(['estoque'])
      },
      (error) => {
        console.log("Erro: ", error)
          alert(error.error);  
      }
    )
  }
  
  loginFuncionario(values: Funcionario){
    this.funcionarioService.login(values.usuario, values.senha).subscribe(
      (retorno) => {
        console.log('Retorno do servidor:', retorno);
        this.autorizacaoService.autorizar();
        this.router.navigate(['funcionario']);
      },
      (error) =>{
        console.log("Error: ", error);
        if(error.status == 404){
          alert(error.error);
        }
      }
    )
  }

  mostrarAcessoFuncionario(){
    const formFunc = this.elementRef.nativeElement.querySelector("#loginFunc");
    const formCliente = this.elementRef.nativeElement.querySelector("swiper-container");
    console.log(formFunc);

    if(this.addHide){
      this.renderer.removeClass(formFunc, 'hide');
      this.renderer.addClass(formCliente, 'hide');
    }
    this.addHide = !this.addHide;

  }

  mostrarAcessoCliente(){
    const formFunc = this.elementRef.nativeElement.querySelector("#loginFunc");
    const formCliente = this.elementRef.nativeElement.querySelector("swiper-container");
    console.log(formFunc);

    if(this.addHide){
      this.renderer.addClass(formFunc, 'hide');
      this.renderer.removeClass(formCliente, 'hide');
    }
    this.addHide = !this.addHide;

  }
}
