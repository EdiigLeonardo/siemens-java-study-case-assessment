import { Component, OnInit, OnDestroy, ViewChild, ElementRef, ChangeDetectionStrategy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IncidentService } from '../../core/services/incident.service';

@Component({
  selector: 'app-incident-list', standalone: true, imports: [CommonModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './incident-list.component.html',
})
export class IncidentListComponent implements OnInit, OnDestroy {
  incidents: any[] = [];
  @ViewChild('box') box!: ElementRef;
  private timer: any;

  constructor(private incidentService: IncidentService) {}

  ngOnInit(): void {
    this.incidentService.list().subscribe(r => (this.incidents = r));

    // BUG: ViewChild usado em ngOnInit - a view ainda nao existe aqui (undefined).
    console.log(this.box);

    // BUG: setInterval nativo sem clearInterval no ngOnDestroy -> continua a
    // correr depois do componente ser destruido (fora do ciclo do Angular).
    this.timer = setInterval(() => this.incidentService.list().subscribe(r => (this.incidents = r)), 5000);
  }

  // BUG: chamada a funcao no template ({{ total() }}) - corre em TODOS os
  // ciclos de deteccao de mudancas, nao so quando "incidents" muda.
  total(): number { return this.incidents.length; }

  ngOnDestroy(): void {}
}
