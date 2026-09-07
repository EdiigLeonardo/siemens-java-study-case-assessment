import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { TicketService } from '../../../core/services/ticket.service';
import { Ticket } from '../../../core/models/ticket.model';
import { Subscription, takeUntilDestoyed } from 'rxjs';

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
  private subscription?: Subscription; 

  constructor(private ticketService: TicketService) {}

  trackByFn(index: number, item: Ticket) {
    return item.id;
  }

  ngOnInit(): void {
    this.subscription = this.ticketService.list().pipe(takeUntilDestoyed()).subscribe({
      next: res => {
        this.tickets = res.content;
      },
      error: err => {
        console.error('Erro ao carregar tickets:', err);
      }
    });
  }

  ngOnDestroy(): void {
    this.subscription?.unsubscribe();
  }
}

