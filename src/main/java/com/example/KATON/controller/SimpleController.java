package com.example.KATON.controller;

import com.example.KATON.config.SecurityConfig;
import com.example.KATON.model.*;
import com.example.KATON.repository.CameriereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.*;


@Controller
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;
    @Autowired
    private CameriereRepository cameriereRepository;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private SecurityConfig securityConfig;

    private final Prezzario prezzario = new Prezzario();

    private final String[] reparti={"primi","secondi","dolci"};

    private final String[] primi={"primo del giorno","pasta alla don bosco"};
    private final String[] secondi={"carne"};
    private final String[] dolci={"dolce"};
    private final Map<String, String[]> piatti=new HashMap<>();

    public SimpleController() throws IOException {
    }


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
        List<StringWrapper> camerieri = new ArrayList<>();
        for(Cameriere c:cameriereRepository.findAll()){
            int i =0;
            boolean found = false;
            for(String r: reparti){
                if (c.getNome().equalsIgnoreCase(r)) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                for (Ordine o : c.getOrdini()) {
                    if(!o.isCompletato()){
                        i++;
                    }
                }
                camerieri.add(new StringWrapper(c.getNome() + "-" + i));
            }
        }
        model.addAttribute("camerieri", camerieri);
        model.addAttribute("piatti",(prezzario.getTable().keySet()));
        return "aggiungi2";
    }
    @GetMapping("/registra")
    public String registraPage(){
        return "registra";
    }

    @MessageMapping("/cambia")
    public void Cambia(@Payload Map<String,String> allParams){
        Cameriere cameriere = cameriereRepository.getCameriereByNome(allParams.get("cameriere"));
        for(Ordine o:cameriere.getOrdini()){
            if(o.getNomeTavolo().equals(allParams.get("tavolo"))){
                o.setCompletato(!o.isCompletato());
                int i =0;
                boolean found = false;
                for(String r: reparti){
                    if (cameriere.getNome().equalsIgnoreCase(r)) {
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    for (Ordine or : cameriere.getOrdini()) {
                        if(!or.isCompletato()){
                            i++;
                        }
                    }
                    StringWrapper res = new StringWrapper(cameriere.getNome() + "-" + i);
                    simpMessagingTemplate.convertAndSendToUser("cassa","/specific", res);
                }
                cameriereRepository.save(cameriere);
                break;
            }
        }
    }
    @MessageMapping("/private")
    public void SendToUser(@Payload Map<String,String> allParams){
        Ordine ordine = new Ordine();
        Cameriere cameriere = cameriereRepository.getCameriereByNome(allParams.get("cameriere"));
        ordine.setNomeTavolo(allParams.get("nomeTav"));
        ordine.setPersone(Integer.parseInt(allParams.get("persTav")));
        ordine.setNumeroTavolo(Integer.parseInt(allParams.get("numTav")));
        for(String g: prezzario.getTable().keySet()){
            if(!(allParams.get(g.toLowerCase()).isEmpty())||!(allParams.get(g.toLowerCase()).isEmpty())) {
                ordine.getPiatti().add(new Piatto(g,Integer.parseInt(allParams.get(g.toLowerCase())),prezzario.priceOf(g),allParams.get("note "+g.toLowerCase())));
            }
        }
        ordine.setCameriere(cameriere);
        cameriere.getOrdini().add(ordine);
        cameriereRepository.save(cameriere);

        Ordine1 res = new Ordine1(ordine);
        simpMessagingTemplate.convertAndSendToUser(cameriere.getNome(),"/specific", res);
        double tot=0;
        for (Piatto p:res.getPiatti()){
            tot+=p.getPrezzo()*p.getQuantity();
        }
        tot+=res.getPersone()*1.5;
        if((allParams.get("sconto_netto")!=null)&&(!allParams.get("sconto_netto").isEmpty())) {
            tot-=Double.parseDouble(allParams.get("sconto_netto"));
        }
        if((allParams.get("sconto_perc")!=null)&&(!allParams.get("sconto_perc").isEmpty())){
            tot-=(tot*Double.parseDouble(allParams.get("sconto_perc"))/100);
        }
        DecimalFormat df = new DecimalFormat("#.##");
        StringWrapper message = new StringWrapper("totale: "+df.format(tot)+"€");
        simpMessagingTemplate.convertAndSendToUser("cassa", "/specific", message);
        //TODO: MODIFICA QUANDO AVRAI MENU

        piatti.put("primi",primi);
        piatti.put("secondi",secondi);
        piatti.put("dolci",dolci);

        for(String d:piatti.keySet()) {
            Ordine ordine1 = new Ordine();
            for(String c:piatti.get(d)) {
                if((allParams.get(c)!=null) && (!(allParams.get(c.toLowerCase()).isEmpty()))&& (!(allParams.get(c.toLowerCase()).isEmpty()))) {
                    ordine1.getPiatti().add(new Piatto(c, Integer.parseInt(allParams.get(c)), prezzario.priceOf(c), allParams.get("note "+c.toLowerCase())));
                    Cameriere cameriere1 = cameriereRepository.getCameriereByNome(d);
                    ordine1.setNomeTavolo(allParams.get("nomeTav"));
                    ordine1.setNumeroTavolo(Integer.parseInt(allParams.get("numTav")));
                    ordine1.setCameriere(cameriere1);
                    ordine1.setServitore(allParams.get("cameriere"));
                    cameriere1.getOrdini().add(ordine1);
                    cameriereRepository.save(cameriere1);
                }
            }
            if(!ordine1.getPiatti().isEmpty())
                simpMessagingTemplate.convertAndSendToUser(d, "/specific", new Ordine1(ordine1));
        }
    }

    @MessageMapping("/registra")
    public void Registra(@Payload Map<String,String> allParams) throws IOException {
        if(securityConfig.getUserDetailsService().userExists(allParams.get("username"))){
            StringWrapper message = new StringWrapper("registrazione non avvenuta per "+allParams.get("username")+", nome già in uso");
            simpMessagingTemplate.convertAndSendToUser("cassa", "/specific", message);
        }else{
            securityConfig.getUserDetailsService().createUser(User.withDefaultPasswordEncoder()
                    .username(allParams.get("username"))
                    .password(allParams.get("password"))
                    .roles("USER")
                    .build());
            Path currentDirectoryPath = FileSystems.getDefault().getPath("");
            String currentDirectoryName = currentDirectoryPath.toAbsolutePath().toString();
            BufferedWriter writer = new BufferedWriter(new FileWriter(currentDirectoryName+"/src/main/resources/static/database/sicurezza", true));
            PrintWriter out = new PrintWriter(writer);
            out.append(allParams.get("username")+"-"+allParams.get("password")+"\n");
            writer.close();
            out.close();
            StringWrapper message = new StringWrapper("registrazione avvenuta con successo per "+allParams.get("username"));
            simpMessagingTemplate.convertAndSendToUser("cassa", "/specific", message);
        }
    }
    @MessageMapping("/print")
    public void PrintToUser(){

        boolean found;
        List<Ordine1> ordini = new ArrayList<>();
        for(String c : cameriereRepository.getAllNames()){
            for(Ordine o: cameriereRepository.getCameriereByNome(c).getOrdini()){
                found = false;
                for (String r: reparti){
                    if (o.getCameriere().getNome().equalsIgnoreCase(r)) {
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    ordini.add(new Ordine1(o));
                }
            }
        }
        simpMessagingTemplate.convertAndSendToUser("cassa","/specific", ordini);
    }

    @PostMapping("/dati")
    String register1(@RequestParam("nome") String name,Model model){
        for(String c: reparti){
            if(c.equals(name)){
                if(cameriereRepository.getCameriereByNome(name) == null) {
                    cameriereRepository.save(new Cameriere(name, new ArrayList<>()));
                }
                model.addAttribute("dati1", cameriereRepository.getCameriereByNome(name));
                return "postazione";
            }
        }
        if(cameriereRepository.getCameriereByNome(name) == null)
            cameriereRepository.save(new Cameriere(name,new ArrayList<>()));
        model.addAttribute("dati", cameriereRepository.getCameriereByNome(name));
        return "cameriere";
    }

}