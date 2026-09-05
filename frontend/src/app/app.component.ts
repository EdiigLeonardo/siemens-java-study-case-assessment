import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  template: `
    <header class="topbar">
      <h1>Self-Service Portal</h1>
    </header>
    <main>
      <router-outlet />
    </main>
  `,
  styles: [`
    .topbar { padding: 1rem; background: #009999; color: white; }
    main { padding: 1.5rem; }
  `]
})
export class AppComponent {}
