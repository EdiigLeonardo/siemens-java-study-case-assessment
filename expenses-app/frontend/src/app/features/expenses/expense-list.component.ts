import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DomSanitizer, SafeHtml } from '@angular/platform-browser';
import { ExpenseService } from '../../core/services/expense.service';

@Component({
  selector: 'app-expense-list', standalone: true, imports: [CommonModule],
  templateUrl: './expense-list.component.html',
})
export class ExpenseListComponent {
  expenses: any[] = [];
  constructor(private expenseService: ExpenseService, private sanitizer: DomSanitizer) {
    this.expenseService.list().subscribe(e => (this.expenses = e));
  }

  // BUG: bypassSecurityTrustHtml na descricao (texto livre do utilizador)
  // -> XSS. Qualquer despesa com <img src=x onerror=alert(1)> executa JS.
  trustDescription(desc: string): SafeHtml { return this.sanitizer.bypassSecurityTrustHtml(desc); }
}
