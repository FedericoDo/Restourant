package com.example.KATON.command;

import com.example.KATON.model.Cameriere;
import com.example.KATON.model.Ordine;
import com.example.KATON.model.StringWrapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CambiaStatusCameriereCommand extends BaseCommand{

    public void execute(Map<String,String> allParams) {
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
}
