import { CanActivateFn } from '@angular/router';
import { AutorizacaoService } from '../services/autorizacao.service';


export const autorizadoGuard: CanActivateFn = (route, state) => {
  const autorizacaoService = new AutorizacaoService();
  const usuarioLogado = autorizacaoService.obterLoginStatus();
  return usuarioLogado;
};
