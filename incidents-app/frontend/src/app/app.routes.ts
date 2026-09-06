import { Routes } from '@angular/router';
import { IncidentListComponent } from './features/incidents/incident-list.component';
// BUG: falta rota wildcard '**' -> URL invalido fica em branco, sem redirect/404.
export const routes: Routes = [
  { path: '', component: IncidentListComponent },
];
