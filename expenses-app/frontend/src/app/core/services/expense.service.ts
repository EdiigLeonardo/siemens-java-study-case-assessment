import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, retry } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ExpenseService {
  constructor(private http: HttpClient) {}

  list(): Observable<any[]> { return this.http.get<any[]>('/api/expenses'); }

  // BUG: retry() num POST (nao idempotente). Um timeout apos o servidor ja
  // ter gravado a despesa faz o RxJS reenviar o MESMO pedido -> duplicados.
  create(email: string, description: string, amount: number): Observable<any> {
    return this.http.post('/api/expenses', { email, description, amount }).pipe(retry(3));
  }

  // BUG: sem { responseType: 'blob' } - a resposta binaria (PDF) e
  // interpretada como JSON e o download fica corrompido.
  downloadReport(): Observable<any> {
    return this.http.get('/api/expenses/report');
  }
}
