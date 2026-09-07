package com.siemens.kb.controller;
import com.siemens.kb.model.Article;
import com.siemens.kb.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/articles")
public class ArticleController {
  private final ArticleService service;
  public ArticleController(ArticleService service) { this.service = service; }

  // BUG: se o cliente pedir sort=popularity (campo que nao existe na
  // entidade), o Spring Data lanca PropertyReferenceException em runtime -
  // nao ha validacao/whitelist dos campos de sort aceites.
  @GetMapping
  public Page<Article> search(@RequestParam(required = false) String category,
                               @RequestParam(required = false) String keyword,
                               Pageable pageable) {
    return service.search(category, keyword, pageable);
  }
}
