import { HttpInterceptorFn } from '@angular/common/http';
import { catchError, of } from 'rxjs';

// BUG: qualquer erro HTTP (404, 500, validacao) e transformado num
// Observable de sucesso com "of(null)". Quem subscreve nunca sabe que
// houve um erro - a UI mostra simplesmente "sem dados", nao a falha real.
export const errorInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(catchError(() => of(null as any)));
};
