package com.siemens.kb.service;
import com.siemens.kb.model.Article;
import org.springframework.data.jpa.domain.Specification;

public class ArticleSpecs {
  // BUG: se "category" vier null, gera "WHERE category = NULL", que nunca
  // e verdade em SQL -> a pesquisa "sem filtro" devolve sempre ZERO resultados.
  // Devia devolver null/Specification.where(null) quando o filtro nao existe.
  public static Specification<Article> hasCategory(String category) {
    return (root, query, cb) -> cb.equal(root.get("category"), category);
  }

  // BUG: LIKE com wildcard no INICIO ("%termo%") nunca usa indice normal -
  // full scan, degrada com o crescimento da tabela.
  public static Specification<Article> keyword(String term) {
    return (root, query, cb) -> cb.like(cb.lower(root.get("title")), "%" + term.toLowerCase() + "%");
  }

  // BUG: junta fetch de colecao ("tags") com Specification + Pageable ->
  // Hibernate lanca "firstResult/maxResults specified with collection
  // fetch; applying in memory" (ou paginacao feita em memoria, lenta e errada).
  public static Specification<Article> withTagsFetched() {
    return (root, query, cb) -> { root.fetch("tags"); return cb.conjunction(); };
  }
}
