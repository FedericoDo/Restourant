package com.example.KATON.controller;

import com.example.KATON.command.*;
import com.example.KATON.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;


@Controller
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private CassaCommand cassaCommand;
    @Autowired
    private NewUserCommand newUserCommand;
    @Autowired
    private CambiaStatusCameriereCommand cambiaStatusCameriereCommand;
    @Autowired
    private GoToDatiCommand goToDatiCommand;
    @Autowired
    private PrintToUserCommand printToUserCommand;
    @Autowired
    GoToCassaCommand goToCassaCommand;
    @Autowired
    ResocontoCommand resocontoCommand;
    private double total =0;

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/home2")
    public String SecondPage() {
        return "home2";
    }

    @GetMapping("/aggiungi2")
    public String newOrder2(Model model){
        return goToCassaCommand.execute(model);
    }
    @GetMapping("/registra")
    public String registraPage(){
        return "registra";
    }

    @MessageMapping("/cambia")
    public void Cambia(@Payload Map<String,String> allParams){
        cambiaStatusCameriereCommand.execute(allParams);
    }
    @MessageMapping("/private")
    public void SendToUser(@Payload Map<String,String> allParams){
        total += cassaCommand.execute(allParams);
    }

    @MessageMapping("/registra")
    public void Registra(@Payload Map<String,String> allParams) throws IOException {
        newUserCommand.execute(allParams);
    }
    @MessageMapping("/total")
    public void totale() throws IOException {
        resocontoCommand.execute(total);
        DecimalFormat df = new DecimalFormat("#.##");
        StringWrapper message = new StringWrapper("totale: "+df.format(total)+"€");
        simpMessagingTemplate.convertAndSendToUser("cassa", "/specific", message);
    }
    @MessageMapping("/print")
    public void PrintToUser(){
        printToUserCommand.execute();
    }

    @PostMapping("/dati")
    public String register1(@RequestParam("nome") String name,Model model){
        return goToDatiCommand.execute(name,model);
    }

}