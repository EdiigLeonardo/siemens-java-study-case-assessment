import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({ providedIn: 'root' })
export class ArticleService {
  constructor(private http: HttpClient) {}
  search(keyword: string, page: number) {
    return this.http.get<any>(`/api/articles?keyword=${keyword}&page=${page}`);
  }
}
