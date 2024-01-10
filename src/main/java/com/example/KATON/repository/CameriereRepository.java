package com.example.KATON.repository;


import com.example.KATON.model.Cameriere;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;


public interface CameriereRepository extends CrudRepository<Cameriere, Long> {
   Cameriere getCameriereByNome(String name);
   default List<String> getAllNames(){
      List<String> nomi = new ArrayList<>();
      this.findAll().forEach(cameriere -> {
         nomi.add(cameriere.getNome());
      });
      return nomi;
   }
}
