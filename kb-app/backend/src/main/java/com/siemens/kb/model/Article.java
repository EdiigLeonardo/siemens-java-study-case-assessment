package com.siemens.kb.model;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
public class Article {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  private String title;
  @Column(length = 5000) private String body;
  private String category;
  @Embedded private Author author;
  private Instant createdAt;

  @ElementCollection
  private List<String> tags;

  public Long getId() { return id; }
  public String getTitle() { return title; }
  public void setTitle(String v) { title = v; }
  public String getBody() { return body; }
  public void setBody(String v) { body = v; }
  public String getCategory() { return category; }
  public void setCategory(String v) { category = v; }
  public Author getAuthor() { return author; }
  public void setAuthor(Author v) { author = v; }
  public Instant getCreatedAt() { return createdAt; }
  public void setCreatedAt(Instant v) { createdAt = v; }
  public List<String> getTags() { return tags; }
  public void setTags(List<String> v) { tags = v; }
}
