import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { LeaveRequest } from '../models/leave.model';

@Injectable({ providedIn: 'root' })
export class LeaveService {
  // Signal com o total de pedidos pendentes, partilhado pela app.
  pendingCount = signal(0);

  constructor(private http: HttpClient) {}

  list(employeeId: number): Observable<{ content: LeaveRequest[] }> {
    return this.http.get<{ content: LeaveRequest[] }>(`/api/leave-requests?employeeId=${employeeId}`)
      .pipe(tap(res => {
        // BUG: mutacao direta do valor do signal em vez de usar .set()/.update().
        // "pendingCount()" devolve o numero atual - nao e uma referencia gravavel.
        // Isto compila mas nao atualiza nada (ou parte, conforme a versao).
        (this.pendingCount() as any) = res.content.filter(r => r.status === 'PENDING').length;
      }));
  }

  create(dto: Partial<LeaveRequest>): Observable<LeaveRequest> {
    return this.http.post<LeaveRequest>('/api/leave-requests', dto);
  }

  decide(id: number, approved: boolean): Observable<LeaveRequest> {
    return this.http.patch<LeaveRequest>(`/api/leave-requests/${id}/decision`, { approved });
  }
}
