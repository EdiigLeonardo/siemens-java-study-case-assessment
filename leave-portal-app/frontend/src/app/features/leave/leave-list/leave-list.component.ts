import { Component, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule, AsyncPipe } from '@angular/common';
import { LeaveService } from '../../../core/services/leave.service';
import { Observable } from 'rxjs';
import { LeaveRequest } from '../../../core/models/leave.model';

@Component({
  selector: 'app-leave-list',
  standalone: true,
  imports: [CommonModule, AsyncPipe],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './leave-list.component.html',
})
export class LeaveListComponent {
  leaves$: Observable<{ content: LeaveRequest[] }>;

  constructor(private leaveService: LeaveService) {
    this.leaves$ = this.leaveService.list(1);
  }

  // BUG: metodo chamado no template com (click) para "atualizar" a lista
  // faz outro subscribe manual ao MESMO observable que ja e usado com
  // "| async" no template -> duas subscricoes, dois pedidos HTTP para a
  // mesma acao (o observable de HttpClient e cold, cada subscribe = 1 pedido).
  refresh(): void {
    this.leaves$.subscribe(res => console.log('refreshed', res.content.length));
  }
}
