import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Veiculo } from '../models/Veiculo';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VeiculoService {
  private url: string = environment.apiUrl + '/veiculos';
  constructor(private http: HttpClient) { }

  exibir(): Observable<Veiculo[]>{
    return this.http.get<Veiculo[]>(this.url);
  }

}
