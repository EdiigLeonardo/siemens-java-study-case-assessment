import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Subject, switchMap } from 'rxjs';
import { LeaveService } from '../../../core/services/leave.service';

@Component({
  selector: 'app-leave-approval',
  standalone: true,
  imports: [CommonModule],
  template: `<button (click)="approve(1)">Aprovar #1</button><button (click)="approve(2)">Aprovar #2</button>`,
})
export class LeaveApprovalComponent {
  private decide$ = new Subject<number>();

  constructor(private leaveService: LeaveService) {
    // BUG: switchMap cancela o pedido anterior sempre que chega um novo id.
    // Se o manager clicar em "aprovar #1" e logo a seguir "aprovar #2",
    // o pedido HTTP de aprovar #1 e CANCELADO antes de terminar - devia
    // ser mergeMap (processar os dois, em paralelo, sem cancelar nada).
    this.decide$.pipe(
      switchMap(id => this.leaveService.decide(id, true))
    ).subscribe();
  }

  approve(id: number): void { this.decide$.next(id); }
}
