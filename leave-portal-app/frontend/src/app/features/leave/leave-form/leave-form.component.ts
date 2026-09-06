import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { LeaveService } from '../../../core/services/leave.service';
import { HasUnsavedChanges } from '../../../core/guards/unsaved-changes.guard';

@Component({
  selector: 'app-leave-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './leave-form.component.html',
})
// BUG: nao ha validador cruzado (endDate >= startDate). Cada campo e
// validado sozinho; a combinacao invalida passa despercebida.
// BUG: implementa HasUnsavedChanges mas o guard nunca e chamado (rota sem canDeactivate).
export class LeaveFormComponent implements HasUnsavedChanges {
  submitting = false;

  form = this.fb.group({
    employeeId: [1, Validators.required],
    startDate: ['', Validators.required],
    endDate: ['', Validators.required],
    type: ['VACATION', Validators.required],
    reason: [''],
  });

  constructor(private fb: FormBuilder, private leaveService: LeaveService) {}

  hasUnsavedChanges(): boolean { return this.form.dirty && !this.submitting; }

  // BUG: botao de submeter nao fica disabled durante o pedido -> duplo
  // clique = dois POSTs (dois pedidos de ferias criados).
  submit(): void {
    this.leaveService.create(this.form.value as any).subscribe(() => this.form.reset());
  }
}
