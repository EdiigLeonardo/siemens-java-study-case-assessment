import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder } from '@angular/forms';
import { combineLatest, Subject, startWith } from 'rxjs';
import { BookingService } from '../../core/services/booking.service';

@Component({
  selector: 'app-booking-list', standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './booking-list.component.html',
})
export class BookingListComponent implements OnInit {
  bookings: any[] = [];
  filterText = '';
  search$ = new Subject<string>(); // BUG: Subject sem valor inicial.
  form = this.fb.group({ email: [''] });

  constructor(private fb: FormBuilder, private bookingService: BookingService) {
    // BUG: combineLatest so emite quando TODAS as fontes ja emitiram pelo
    // menos uma vez. "search$" e um Subject puro (sem startWith) -> se o
    // utilizador nunca escrever nada, isto nunca emite.
    combineLatest([this.search$, this.form.valueChanges]).subscribe(([text, form]) => {
      console.log('filtro', text, form);
    });
  }

  ngOnInit(): void { this.bookingService.list().subscribe(b => (this.bookings = b)); }

  // BUG: passa o array por referencia; o filho pode dar .push()/.splice()
  // diretamente nele, mudando o estado do pai sem o pai saber (sem
  // imutabilidade, sem passar por um servico/Output).
  removeFirst(): void { this.bookings.splice(0, 1); }
}
