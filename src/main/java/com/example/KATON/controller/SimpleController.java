package com.example.KATON.controller;

import ch.qos.logback.core.model.ModelUtil;
import com.example.KATON.model.*;
import com.example.KATON.repository.CameriereRepository;
import com.example.KATON.repository.DatiRepository;
import com.example.KATON.repository.OrdineRepository;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;
    @Autowired
    private DatiRepository datiRepository;
    @Autowired
    private CameriereRepository cameriereRepository;
    @Autowired
    private OrdineRepository ordineRepository;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private Model privatemodel;

    private Prezzario prezzario = new Prezzario();



    @GetMapping("/")
    public String homePage(Model model) {
      //  model.addAttribute("allDatiList", datiRepository.findAll());
        return "home2";
    }

    @GetMapping("/prova")
    public void register(@RequestParam String user){
        System.out.println(user);
    }
//    @GetMapping("/aggiungi")
//    public String newOrder(Model model){
//        model.addAttribute("comanda", new Ordine1());
//        model.addAttribute("camerieri", cameriereRepository.getAllNames());
//        return "aggiungi";
//    }
//    @PostMapping("/aggiungi")
//    String Ordinazione(@ModelAttribute("comanda")Ordine1 comanda, Model model){
//        Cameriere cameriere = cameriereRepository.getCameriereByNome(comanda.getCameriere());
//        Ordine order = new Ordine();
//        order.setCameriere(cameriere);
//        order.setNumeroTavolo(comanda.getNumeroTavolo());
//        order.setPiatti(new ArrayList<>());
//        order.setPersone(comanda.getPersone());
//        order.setNomeTavolo(comanda.getNomeTavolo());
//        cameriere.getOrdini().add(order);
//        cameriereRepository.save(cameriere);
//        ordineRepository.save(order);
//        model.addAttribute("comanda", new Ordine1());
//        model.addAttribute("camerieri", cameriereRepository.getAllNames());
//
//        return "aggiungi";
//    }
//    @MessageMapping("/private")
//    public void sendToSpecificUser(@Payload Ordine1 comanda,Model model) {
//        Cameriere cameriere = cameriereRepository.getCameriereByNome(comanda.getCameriere());
//        Ordine order = new Ordine();
//        order.setCameriere(cameriere);
//        order.setNumeroTavolo(comanda.getNumeroTavolo());
//        order.setPiatti(new ArrayList<>());
//        order.getPiatti().add(new Piatto("pasta",comanda.getPasta()));
//        order.getPiatti().add(new Piatto("carne",comanda.getCarne()));
//        order.setPersone(comanda.getPersone());
//        order.setNomeTavolo(comanda.getNomeTavolo());
//        cameriere.getOrdini().add(order);
//        cameriereRepository.save(cameriere);
//        ordineRepository.save(order);
//        model.addAttribute("comanda", new Ordine1());
//        model.addAttribute("camerieri", cameriereRepository.getAllNames());
//        simpMessagingTemplate.convertAndSendToUser(comanda.getCameriere(), "/specific", message);
//    }

    @GetMapping("/aggiungi2")
    public String newOrder2(Model model){
        model.addAttribute("camerieri", cameriereRepository.getAllNames());
        model.addAttribute("piatti",(prezzario.getTable().keySet()));
        privatemodel = model;
        return "aggiungi2";
    }
    @MessageMapping("/private")
    public void SendToUser(@Payload Map<String,String> allParams){
        Ordine ordine = new Ordine();
        Cameriere cameriere = cameriereRepository.getCameriereByNome(allParams.get("cameriere"));
        ordine.setNomeTavolo(allParams.get("nomeTav"));
        ordine.setPersone(Integer.parseInt(allParams.get("persTav")));
        ordine.setNumeroTavolo(Integer.parseInt(allParams.get("numTav")));
        for(String g: prezzario.getTable().keySet()){
            if(!(allParams.get(g.toString().toLowerCase()).equals(""))||!(allParams.get(g.toString().toLowerCase()).isEmpty())) {
                ordine.getPiatti().add(new Piatto(g.toString(),Integer.parseInt(allParams.get(g.toString().toLowerCase())),prezzario.priceOf(g)));
            }
        }
        ordine.setCameriere(cameriere);
        cameriere.getOrdini().add(ordine);
        cameriereRepository.save(cameriere);
        //ordineRepository.save(ordine);
        for(Ordine i : cameriere.getOrdini()) {
            System.out.println(i.getNomeTavolo());
        }

            Ordine1 res = new Ordine1(ordine);
        simpMessagingTemplate.convertAndSendToUser(cameriere.getNome(),"/specific", res);
        double tot=0;
        for (Piatto p:res.getPiatti()){
            tot+=p.getPrezzo()*p.getQuantity();
        }
        DecimalFormat df = new DecimalFormat("#.##");
        StringWrapper message =new StringWrapper("totale: "+df.format(tot).toString()+"€");
        simpMessagingTemplate.convertAndSendToUser("cassa", "/specific", message);
    }
    @MessageMapping("/print")
    public void PrintToUser(@Payload String request){
        System.out.println("here1");
        List<Ordine1> ordini = new ArrayList<Ordine1>();
        for(String c : cameriereRepository.getAllNames()){
            for(Ordine o: cameriereRepository.getCameriereByNome(c).getOrdini()){
                ordini.add(new Ordine1(o));
            }
        }
        simpMessagingTemplate.convertAndSendToUser("cassa","/specific", ordini);
    }
//    @PostMapping("/aggiungi2/{id}")
//    String Ordinazione3(@PathVariable final String id,@RequestParam String nome, Model model){
//        service.notifyUser2(id, nome);
//        System.out.println(id);
//        return "aggiungi2";
//    }
   /* @GetMapping("/dati")
    String two(@PathVariable String dato,Model model){
        model.addAttribute("dato", datiRepository.findByUsername(dato));
        privatemodel = model;
        return "dato";
    }*/
    @PostMapping("/dati")
    String register1(@RequestParam("nome") String name,Model model){
        System.out.println("----------registrazione-------"+name);
        if(cameriereRepository.getCameriereByNome(name) == null)
            cameriereRepository.save(new Cameriere(name,new ArrayList<Ordine>()));
        model.addAttribute("dati", cameriereRepository.getCameriereByNome(name));
        privatemodel = model;
        return "dato2";
    }
   /* @GetMapping("/dati/{id}")
    String one(@PathVariable Long id,Model model) {
        model.addAttribute("dato", datiRepository.getById(id));
        privatemodel = model;
        return "dato";
    }*/

    @PutMapping("/dati/{id}")
    Dati replaceDato(@RequestBody Dati newDati, @PathVariable Long id) {

//        return datiRepository.findById(id)
//                .map(dato -> {
//                    dato.setUsername(newDati.getUsername());
//                    dato.setPassword(newDati.getPassword());
//                    dato.setPage(newDati.getPage());
//                    return datiRepository.save(dato);
//                })
//                .orElseGet(() -> {
//                    newDati.setId(id);
//                    return datiRepository.save(newDati);
//                });
        return null;
    }

    @DeleteMapping("/dati/{id}")
    void deleteDato(@PathVariable Long id) {
        datiRepository.deleteById(id);
    }

}