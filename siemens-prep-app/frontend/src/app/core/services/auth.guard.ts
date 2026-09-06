import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

// BUG: le o token direto do localStorage aqui, sem passar por um AuthService.
// Logica de autenticacao espalhada em vez de centralizada.
export const authGuard: CanActivateFn = () => {
  const token = localStorage.getItem('token');
  if (token) return true;
  inject(Router).navigate(['/login']);
  return false;
};
