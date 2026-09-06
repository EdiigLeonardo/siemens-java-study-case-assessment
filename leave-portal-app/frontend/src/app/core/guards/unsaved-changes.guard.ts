import { CanDeactivateFn } from '@angular/router';

export interface HasUnsavedChanges { hasUnsavedChanges(): boolean; }

// Guard existe mas nao esta ligado a nenhuma rota (ver app.routes.ts).
export const unsavedChangesGuard: CanDeactivateFn<HasUnsavedChanges> = (component) => {
  if (component.hasUnsavedChanges()) {
    return confirm('Tens alteracoes por gravar. Sair mesmo assim?');
  }
  return true;
};
