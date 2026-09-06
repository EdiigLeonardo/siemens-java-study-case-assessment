import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { TicketService } from '../../../core/services/ticket.service';
import { Ticket } from '../../../core/models/ticket.model';

@Component({
  selector: 'app-ticket-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './ticket-list.component.html',
})
// BUG (OnInit sem OnDestroy): subscreve mas nunca cancela -> memory leak.
// BUG: subscribe sem error callback -> falha da API passa em silencio.
// BUG: *ngFor no template nao usa trackBy -> re-render desnecessario.
export class TicketListComponent implements OnInit {
  tickets: Ticket[] = [];

  constructor(private ticketService: TicketService) {}

  ngOnInit(): void {
    this.ticketService.list().subscribe(res => {
      this.tickets = res.content;
    });
  }
}
