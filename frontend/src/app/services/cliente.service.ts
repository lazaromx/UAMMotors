import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {Cliente} from '../models/Cliente'
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private url: string = environment.apiUrl + '/clientes';

  constructor(private http: HttpClient) {
    console.log("environment.production", environment.production)
  }

  selecionar(): Observable<Cliente[]>{
    return this.http.get<Cliente[]>(this.url);
  }

  cadastrar(obj: Cliente): Observable<Cliente>{
    return this.http.post<Cliente>(this.url, obj)
  }

  Login(email: string, senha: string): Observable<Cliente>{
    const loginData = {email, senha};
    return this.http.post<Cliente>(this.url + '/login', loginData);
  }
}
