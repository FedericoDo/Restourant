package com.example.KATON.command;

import com.example.KATON.config.SecurityConfig;
import com.example.KATON.model.Prezzario;
import com.example.KATON.repository.CameriereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class BaseCommand {
    public final String[] reparti={"antipasti","primi","secondi","dolci"};
    @Autowired
    public CameriereRepository cameriereRepository;
    @Autowired
    public SimpMessagingTemplate simpMessagingTemplate;

    public final Prezzario prezzario = new Prezzario();

    @Autowired
    public SecurityConfig securityConfig;

}
