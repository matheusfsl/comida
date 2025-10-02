package com.comida.repository;

import com.comida.model.ComidaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ComidaRepository extends JpaRepository<ComidaModel, UUID> {
    Optional<ComidaModel> findByNameContainingIgnoreCase(String name);
    Set<ComidaModel> findByIsActiveTrue();
}
