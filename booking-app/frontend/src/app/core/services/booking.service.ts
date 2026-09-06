import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, from } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class BookingService {
  constructor(private http: HttpClient) {}

  list(): Observable<any[]> { return this.http.get<any[]>('/api/bookings'); }

  // BUG: usa fetch() nativo em vez do HttpClient -> nao passa por nenhum
  // interceptor da app (sem auth header, sem tratamento de erro central).
  cancel(id: number): Observable<any> {
    return from(fetch(`/api/bookings/${id}`, { method: 'DELETE' }));
  }
}
