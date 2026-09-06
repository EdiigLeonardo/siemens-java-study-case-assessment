import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CreateTicketRequest, Ticket } from '../models/ticket.model';

const API = '/api/tickets';

@Injectable({ providedIn: 'root' })
export class TicketService {
  constructor(private http: HttpClient) {}

  list(): Observable<{ content: Ticket[] }> {
    return this.http.get<{ content: Ticket[] }>(API);
  }

  getById(id: string): Observable<Ticket> {
    return this.http.get<Ticket>(`${API}/${id}`);
  }

  create(req: CreateTicketRequest): Observable<Ticket> {
    return this.http.post<Ticket>(API, req);
  }

  // BUG: devia ser PATCH/PUT (semantica REST); backend tambem usa POST aqui.
  updateStatus(id: string, status: string): Observable<Ticket> {
    return this.http.post<Ticket>(`${API}/${id}/status`, { status });
  }

  assign(id: string, assignee: string): Observable<Ticket> {
    return this.http.patch<Ticket>(`${API}/${id}/assign`, { assignee });
  }
}
