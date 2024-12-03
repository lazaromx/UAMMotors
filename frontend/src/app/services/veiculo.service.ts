import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Veiculo } from '../models/Veiculo';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { ClienteService } from './cliente.service';

@Injectable({
  providedIn: 'root'
})
export class VeiculoService {
  private url: string = environment.apiUrl + '/veiculos';
  constructor(
    private http: HttpClient,
    private clienteService: ClienteService
  ) { }

  exibir(): Observable<Veiculo[]>{
    return this.http.get<Veiculo[]>(this.url);
  }

  exibirPorId(id: number): Observable<Veiculo>{
    return this.http.get<Veiculo>(this.url + '/' + id);
  }

  cadastrar(obj: Veiculo): Observable<Veiculo[]>{
    return this.http.post<Veiculo[]>(this.url, obj);
  }

  editarStatus(id: number, obj: Veiculo): Observable<Veiculo>{
    const reserva = {idVeiculo: obj.id, idCliente: this.clienteService.cliente.id!};
    return this.http.put<Veiculo>(this.url, reserva);
  }

}
