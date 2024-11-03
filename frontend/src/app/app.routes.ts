import { Routes } from '@angular/router';
import { MenuComponent } from './pages/menu.component';

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
            { path: 'estoque', loadComponent: () => import('./pages/home/home.component').then(m => m.HomeComponent) },
        ]
    },
    { path: '**', redirectTo: '', pathMatch: 'full' },
];
