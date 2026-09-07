package com.siemens.kb.service;
import com.siemens.kb.model.Article;
import com.siemens.kb.repository.ArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
  private final ArticleRepository repository;
  public ArticleService(ArticleRepository repository) { this.repository = repository; }

  public Page<Article> search(String category, String keyword, Pageable pageable) {
    Specification<Article> spec = ArticleSpecs.hasCategory(category)
        .and(ArticleSpecs.keyword(keyword))
        .and(ArticleSpecs.withTagsFetched());
    return repository.findAll(spec, pageable);
  }

  // BUG (Optional mal usado): Optional como PARAMETRO de metodo - anti-padrao
  // (Optional e para valores de retorno, nao para argumentos). Obriga quem
  // chama a envolver tudo em Optional.of(...)/Optional.empty() sem necessidade.
  public List<Article> findByCategoryLoose(Optional<String> category) {
    return category.map(c -> repository.findAll((root, q, cb) -> cb.equal(root.get("category"), c)))
        .orElseGet(repository::findAll);
  }
}
