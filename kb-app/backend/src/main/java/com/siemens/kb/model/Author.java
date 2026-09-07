package com.siemens.kb.model;
// BUG: record como @Embeddable - records sao imutaveis e so tem construtor
// all-args; o Hibernate precisa de um construtor vazio + setters para
// reconstruir o objeto embutido. Isto falha a arrancar ou ao fazer merge/update.
import jakarta.persistence.Embeddable;
@Embeddable
public record Author(String name, String email) {}
