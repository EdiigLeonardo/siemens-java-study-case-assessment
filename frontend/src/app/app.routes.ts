import { Routes } from '@angular/router';
import { TicketListComponent } from './features/tickets/ticket-list/ticket-list.component';
import { TicketDetailComponent } from './features/tickets/ticket-detail/ticket-detail.component';
import { TicketFormComponent } from './features/tickets/ticket-form/ticket-form.component';
import { authGuard } from './core/services/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'tickets', pathMatch: 'full' },
  { path: 'tickets', component: TicketListComponent, canActivate: [authGuard] },
  { path: 'tickets/new', component: TicketFormComponent, canActivate: [authGuard] },
  { path: 'tickets/:id', component: TicketDetailComponent, canActivate: [authGuard] },
];
