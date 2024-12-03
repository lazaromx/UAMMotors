import { Routes } from '@angular/router';
import { MenuComponent } from './pages/menu.component';
import { autorizadoGuard } from './guard/autorizado.guard';
import { FuncionarioComponent } from './pages/funcionario/funcionario.component';

export const routes: Routes = [
    { path: '', redirectTo: '', pathMatch: 'full' },
    {
        path: 'login', 
        loadComponent: () => import('./pages/login/login.component').then(m => m.LoginComponent),
        // canActivate: [AuthGuard]
    },
    {
        path: '',
        component: MenuComponent,
        children: [
            { path: '', loadComponent: () => import('./pages/home/home.component').then(m => m.HomeComponent) },
            { path: 'estoque', loadComponent: () => import('./pages/estoque/estoque.component').then(m => m.EstoqueComponent) },
            
        ]
    },

    {path: 'funcionario', 
        component: FuncionarioComponent,
        children: [
            {path: '', loadComponent: () => import('./pages/funcionario/estoque-func/estoque-func.component').then(m => m.EstoqueFuncComponent)},
            {path: 'cadastro-veiculo', loadComponent: () => import('./pages/funcionario/cadastro-veiculo/cadastro-veiculo.component').then(m => m.CadastroVeiculoComponent)},
        ],
        canActivate: [autorizadoGuard]
    },

    {path: 'detalhes/:id', loadComponent: () => import('./pages/info-veiculo/info-veiculo.component').then(m => m.InfoVeiculoComponent), 
        canActivate: [autorizadoGuard] },
    
    { path: '**', redirectTo: '', pathMatch: 'full' },
];
