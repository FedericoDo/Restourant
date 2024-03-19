package com.example.KATON.config;


import com.example.KATON.model.Cameriere;
import com.example.KATON.model.Dati;
import com.example.KATON.model.Ordine;
import com.example.KATON.model.Piatto;
import com.example.KATON.repository.CameriereRepository;
import com.example.KATON.repository.DatiRepository;
import com.example.KATON.repository.IdRepository;
import com.example.KATON.repository.OrdineRepository;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;


@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Autowired
    CameriereRepository cameriereRepository;
    @Autowired
    OrdineRepository ordineRepository;
    @Autowired
    DatiRepository datiRepository;

    @Autowired
    IdRepository idRepository;

}