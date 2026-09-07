import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { ArticleService } from '../../core/services/article.service';

@Component({
  selector: 'app-article-search', standalone: true, imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './article-search.component.html',
})
export class ArticleSearchComponent {
  keyword = new FormControl('');
  page = 0;
  articles: any[] = [];

  constructor(private articleService: ArticleService) {
    // BUG: sem debounceTime/distinctUntilChanged - CADA tecla dispara um
    // pedido HTTP (e tecla repetida/apagar-e-repor dispara pedidos identicos).
    this.keyword.valueChanges.subscribe(v => this.runSearch(v ?? ''));
  }

  // BUG: ao mudar o filtro, "page" nao volta a 0 - se estavas na pagina 5
  // e a nova pesquisa so tem 1 pagina, ficas numa pagina vazia sem aviso.
  runSearch(keyword: string): void {
    this.articleService.search(keyword, this.page).subscribe(r => (this.articles = r.content));
    // BUG: o estado da pesquisa (keyword/page) nunca vai para a URL
    // (router queryParams) - dar refresh ou partilhar o link perde o filtro.
  }
}
