package com.siemens.incidents.repository;
import com.siemens.incidents.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IncidentRepository extends JpaRepository<Incident, Long> {}
