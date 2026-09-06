import { Routes } from '@angular/router';
import { LeaveListComponent } from './features/leave/leave-list/leave-list.component';
import { LeaveFormComponent } from './features/leave/leave-form/leave-form.component';
import { LeaveApprovalComponent } from './features/leave/leave-approval/leave-approval.component';
import { unsavedChangesGuard } from './core/guards/unsaved-changes.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'leave', pathMatch: 'full' },
  { path: 'leave', component: LeaveListComponent },
  // BUG: guard nao aplicado aqui (falta canDeactivate) - sair do form com
  // alteracoes por gravar nao avisa o utilizador.
  { path: 'leave/new', component: LeaveFormComponent },
  { path: 'leave/approvals', component: LeaveApprovalComponent },
];
