package com.example.KATON.command;

import com.example.KATON.model.Cameriere;
import com.example.KATON.model.Ordine;
import com.example.KATON.model.StringWrapper;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Component
public class GoToCassaCommand extends BaseCommand{

    public String execute(Model model) {
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
}
