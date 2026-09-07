package com.siemens.kb.repository;
import com.siemens.kb.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {}
