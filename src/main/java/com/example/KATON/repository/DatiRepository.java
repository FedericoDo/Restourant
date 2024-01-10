package com.example.KATON.repository;

import com.example.KATON.model.Dati;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface DatiRepository extends CrudRepository<Dati, Long> {

Dati findByUsername(String username);
List<Dati> findAll();
Dati getById(Long id);
}