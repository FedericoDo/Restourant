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

    @Bean
    CommandLineRunner initDatabase() {

        return args -> {
            log.info("Preloading " + datiRepository.save(new Dati("katon", "katon", "katon.jsp")));
            log.info("Preloading " + datiRepository.save(new Dati("odoshishi", "odoshishi","odoshishi.jsp")));
            Ordine prova = new Ordine();
            List<Ordine> ordini = new ArrayList<Ordine>();
            prova.setPersone(3);
            prova.setNomeTavolo("donati");
            prova.setNumeroTavolo(54);
           // prova.getOrdine().add(new Piatto("PASTA", 1));
            ordini.add(prova);
            log.info("Preloading " + cameriereRepository.save(new Cameriere("federico", ordini)));
          //  log.info("Preloading " + ordineRepository.save(prova));
            Cameriere cameriere1 = new Cameriere();
            cameriere1.setNome("federico1");
            cameriere1.setOrdini(new ArrayList<>());
            Ordine ordine1 = new Ordine();
            ordine1.setNumeroTavolo(6);
            ordine1.setPersone(1);
            ordine1.setNomeTavolo("ciao");
//            cameriere1.getOrdini().add(ordine1);

            cameriereRepository.save(cameriere1);
            ordineRepository.save(ordine1);
        };
    }
}