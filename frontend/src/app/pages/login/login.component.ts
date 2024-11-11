import { Component, CUSTOM_ELEMENTS_SCHEMA, ElementRef, viewChild } from '@angular/core';
import Swiper from 'swiper';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LoginComponent {
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

  cliente = {
    nome: '',
    sobrenome: '',
    email: '',
    senha:'',
    telefone:'',
    cpf: ''
  };

  repetirSenha: string = '';
  senhaMismatch: boolean = false;

  verificarSenha(){
    this.senhaMismatch = this.cliente.senha !=this.repetirSenha;
  }

  onSubmit() {
    console.log('Formulário enviado:', this.cliente);
    alert('Formulário enviado com sucesso!');
  }
}
