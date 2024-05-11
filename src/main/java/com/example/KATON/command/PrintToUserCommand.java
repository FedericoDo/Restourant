package com.example.KATON.command;

import com.example.KATON.model.Ordine;
import com.example.KATON.model.Ordine1;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PrintToUserCommand extends BaseCommand{

    public void execute() {

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
}
