import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TicketService } from '../../../core/services/ticket.service';

@Component({
  selector: 'app-ticket-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './ticket-form.component.html',
})

export enum Priority {
  LOW = 'LOW',
  MEDIUM = 'MEDIUM',
  HIGH = 'HIGH',
  URGENT = 'URGENT',
}

// BUG: form reativo sem nenhum Validators (required, email...) -> submete vazio.
export class TicketFormComponent {
  PRIORITY = Priority;

  form = this.fb.group({
    title: ['', Validators.required],
    description: ['', Validators.required],
    priority: [Priority.MEDIUM, Validators.required],
    requesterEmail: ['', [Validators.required, Validators.email]],
  });

  constructor(private fb: FormBuilder, private ticketService: TicketService, private router: Router) {}

  submit(): void {
    this.ticketService.create(this.form.value as any).subscribe(() => {
      this.router.navigate(['/tickets']);
    });
  }
}
