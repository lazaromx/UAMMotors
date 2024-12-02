import { Component, input, output } from '@angular/core';
import { CurrencyPipe } from '@angular/common';

import { Veiculo } from '../../../models/Veiculo';

@Component({
  selector: 'app-veiculo',
  standalone: true,
  imports: [CurrencyPipe],
  templateUrl: './veiculo.component.html',
  styleUrl: './veiculo.component.scss',
})
export class VeiculoComponent {
  veiculo = input.required<Veiculo>();
  confirmar = output<number>();

  constructor() {}

  onConfirmar() {
    this.confirmar.emit(this.veiculo().id!);
  }
}
