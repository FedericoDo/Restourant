package com.example.KATON.command;


import com.example.KATON.model.Cameriere;
import com.example.KATON.model.Ordine;
import com.example.KATON.model.Piatto;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class ResocontoCommand extends BaseCommand{

        public void execute(double total) throws IOException {
            Path currentDirectoryPath = FileSystems.getDefault().getPath("");
            String currentDirectoryName = currentDirectoryPath.toAbsolutePath().toString();
            BufferedWriter writer = new BufferedWriter(new FileWriter(currentDirectoryName+"/src/main/resources/static/database/resoconto"));
            PrintWriter out = new PrintWriter(writer);
            out.print("");
            Map<String,Integer> resocontoMap = new HashMap<>();
            for (String s: prezzario.getTable().keySet()){
                resocontoMap.put(s,0);
            }
            int coperti = 0;
            for(Cameriere cameriere:cameriereRepository.findAll()){
                if(Arrays.stream(reparti).noneMatch(s -> s.equals(cameriere.getNome()))) {
                    for (Ordine ordine : cameriere.getOrdini()) {
                        coperti += ordine.getPersone();
                        for (Piatto p : ordine.getPiatti()) {
                            resocontoMap.put(p.getNome(), resocontoMap.get(p.getNome()) + p.getQuantity());
                        }
                    }
                }
            }
            String res ="RAPPORTO GIORNALIERO \n";
            for(String s:resocontoMap.keySet()){
                res+=s+": "+resocontoMap.get(s)+"\n";
            }
            res+="coperti: "+coperti+"\n";
            res+="incasso: "+total+"€";
            out.print(res);
            out.close();
            writer.close();
        }
}
