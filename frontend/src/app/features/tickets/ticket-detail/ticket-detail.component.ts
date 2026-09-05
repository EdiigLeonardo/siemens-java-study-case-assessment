import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { TicketService } from '../../../core/services/ticket.service';
import { Ticket } from '../../../core/models/ticket.model';

@Component({
  selector: 'app-ticket-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './ticket-detail.component.html',
})
export class TicketDetailComponent implements OnInit {
  ticket?: Ticket;

  constructor(private route: ActivatedRoute, private ticketService: TicketService) {}

  ngOnInit(): void {
    // BUG: le o :id direto do snapshot. Se o utilizador navegar de
    // /tickets/A para /tickets/B sem sair do componente, ngOnInit nao
    // corre outra vez e o ecra fica com o ticket errado (falta ouvir
    // route.paramMap em vez do snapshot).
    const id = this.route.snapshot.paramMap.get('id')!;
    this.ticketService.getById(id).subscribe(t => (this.ticket = t));
  }

  resolve(): void {
    if (!this.ticket) return;
    this.ticketService.updateStatus(this.ticket.id, 'RESOLVED')
      .subscribe(t => (this.ticket = t));
  }
}
