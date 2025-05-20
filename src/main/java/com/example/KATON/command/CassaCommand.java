package com.example.KATON.command;

import com.example.KATON.model.*;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.*;

@Component
public class CassaCommand extends BaseCommand{

    private final Prezzario prezzario = new Prezzario();
    private final Map<String, String[]> piatti=new HashMap<>();
    private final String[] antipasti={"antipasto di campomaggiore","tris di bruschette","tris di suppli","acqua 1Lt","acqua 0.5Lt","vino 0.5Lt","vino 1Lt","vino in bottiglia","bibita","birra piccola","birra grande", "piatti senza glutine"};
    private final String[] primi={"primo del giorno","ciriole alla ternana"};
    private final String[] secondi={"grigliata mista","braciola","tris di salsicce","verdura cotta","patate fritte","pizza 1 ingrediente","pizza 2 ingredienti", "pizza bianca", "agnello", "vitella"};
    private final String[] dolci={"pizzola con nutella","pizzola dolce/salata"};

    public double execute(Map<String,String> allParams) {
        Ordine ordine = new Ordine();
        Cameriere cameriere;
        if(allParams.get("cameriere")==null || allParams.get("cameriere").isEmpty()) {
            if (mock){
                mock = false;
                cameriereRepository.save(new Cameriere("cassa", new ArrayList<>()));
            }
            cameriere = cameriereRepository.getCameriereByNome("cassa");
        }else {
            cameriere = cameriereRepository.getCameriereByNome(allParams.get("cameriere"));
        }
        ordine.setNomeTavolo(allParams.get("nomeTav"));
        ordine.setPersone(Integer.parseInt(allParams.get("persTav")));
        ordine.setNumeroTavolo(Integer.parseInt(allParams.get("numTav")));
        for(String g: prezzario.getTable().keySet()){
            if((allParams.get(g)!=null) && !(allParams.get(g).isEmpty())) {
                System.out.println(g);
                ordine.getPiatti().add(new Piatto(g,Integer.parseInt(allParams.get(g)),prezzario.priceOf(g),allParams.get("note "+g)));
            }
        }
        Collections.reverse(ordine.getPiatti());
        StringWrapper trovati = new StringWrapper("0");
        cameriereRepository.findAll().forEach(c ->{
            if(Arrays.stream(reparti).noneMatch(s->s.equals(c.getNome()))){
                c.getOrdini().stream().filter(o -> o.getNomeTavolo().split("-")[0].equals(ordine.getNomeTavolo())).forEach(o -> trovati.setValore(String.valueOf(Integer.parseInt(trovati.getValore())+1)));
            }
        });
        ordine.setCameriere(cameriere);
        if(!trovati.getValore().equals("0")) {
            ordine.setNomeTavolo(ordine.getNomeTavolo() + "-" + trovati.getValore());
        }
        cameriere.getOrdini().add(ordine);
        cameriereRepository.save(cameriere);

        Ordine1 res = new Ordine1(ordine);
        if(allParams.get("cameriere")!=null)
            simpMessagingTemplate.convertAndSendToUser(cameriere.getNome(),"/specific", res);
        double tot=ordine.getTotale();
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

        piatti.put("antipasti",antipasti);
        piatti.put("primi",primi);
        piatti.put("secondi",secondi);
        piatti.put("dolci",dolci);
        if(allParams.get("cameriere")!=null && !allParams.get("cameriere").isEmpty()) {
            for (String d : piatti.keySet()) {
                Ordine ordine1 = new Ordine();
                for (String c : piatti.get(d)) {
                    if ((allParams.get(c) != null) && (!(allParams.get(c).isEmpty()))) {
                        ordine1.getPiatti().add(new Piatto(c, Integer.parseInt(allParams.get(c)), prezzario.priceOf(c), allParams.get("note " + c)));
                        Cameriere cameriere1 = cameriereRepository.getCameriereByNome(d);
                        ordine1.setNomeTavolo(ordine.getNomeTavolo());
                        ordine1.setNumeroTavolo(Integer.parseInt(allParams.get("numTav")));
                        ordine1.setCameriere(cameriere1);
                        ordine1.setServitore(allParams.get("cameriere"));
                        cameriere1.getOrdini().add(ordine1);
                        cameriereRepository.save(cameriere1);
                    }
                }
                if (!ordine1.getPiatti().isEmpty()) {
                    simpMessagingTemplate.convertAndSendToUser(d, "/specific", new Ordine1(ordine1));
                }
            }
        }
        return tot;
    }
}
