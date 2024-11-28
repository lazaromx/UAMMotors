import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Funcionario } from '../models/Funcionario';

@Injectable({
  providedIn: 'root'
})
export class FuncionarioService {
  private url: string = environment.apiUrl + '/funcionarios'
  constructor(private http: HttpClient) { }

  login(usuario: string, senha: string): Observable<Funcionario>{
    const loginData = {usuario, senha};
    return this.http.post<Funcionario>(this.url + '/login', loginData);
  }
}
