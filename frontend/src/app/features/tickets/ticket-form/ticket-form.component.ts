import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { TicketService } from '../../../core/services/ticket.service';

@Component({
  selector: 'app-ticket-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './ticket-form.component.html',
})
// BUG: form reativo sem nenhum Validators (required, email...) -> submete vazio.
export class TicketFormComponent {
  form = this.fb.group({
    title: [''],
    description: [''],
    priority: ['MEDIUM'],
    requesterEmail: [''],
  });

  constructor(private fb: FormBuilder, private ticketService: TicketService, private router: Router) {}

  submit(): void {
    this.ticketService.create(this.form.value as any).subscribe(() => {
      this.router.navigate(['/tickets']);
    });
  }
}
