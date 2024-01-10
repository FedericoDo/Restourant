package com.example.KATON.repository;

import com.example.KATON.model.StringWrapper;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface IdRepository extends CrudRepository<StringWrapper, Long> {

    List<StringWrapper> findAll();
}