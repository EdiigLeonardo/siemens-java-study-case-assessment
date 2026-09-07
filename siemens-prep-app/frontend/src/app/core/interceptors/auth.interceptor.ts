import { HttpInterceptorFn } from '@angular/common/http';
import { catchError, throwError } from 'rxjs';

// BUG: nao trata erro 401 (token expirado) - nao faz refresh nem redireciona.
// Um pedido que falhe por token expirado simplesmente falha em silencio
// para quem subscrever sem error callback (ver ticket-list.component.ts).
export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('token');
  const cloned = token
    ? req.clone({ setHeaders: { Authorization: `Bearer ${token}` } })
    : req;

  return next(cloned).pipe(
    catchError((error) => {
      if (error.status === 401) {
        localStorage.removeItem('token');
        window.location.href = '/login';
      }
      return throwError(() => error);
    })
  );
};
