import { ResolveFn } from '@angular/router';
import { inject } from '@angular/core';
import { ExpenseService } from '../../core/services/expense.service';

// BUG: sem catchError. Se o pedido falhar, o Observable emite erro e a
// navegacao fica presa (o router nunca ativa a rota nem mostra nada) -
// utilizador fica a olhar para um spinner infinito.
export const expenseListResolver: ResolveFn<any[]> = () => inject(ExpenseService).list();
