package com.example.KATON.command;

import com.example.KATON.model.Cameriere;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.ArrayList;

@Component
public class GoToDatiCommand extends BaseCommand{

    public String execute(String name, Model model) {
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
