import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AutorizacaoService {

  autorizado = JSON.parse(localStorage.getItem("conectado") || "false");


  constructor() { }

  autorizar(){
    this.autorizado = true;
    console.log("Autorizar: ",this.autorizado);
    localStorage.setItem("conectado", JSON.stringify(this.autorizado));
  }

  deslogar(){
    this.autorizado = false;
    console.log("Deslogado: ",this.autorizado);
    localStorage.clear();

  }

  obterLoginStatus(){
    // return !!localStorage.getItem("conectado");
    console.log("Autorizado: ",this.autorizado);
    return this.autorizado
  }
}
