package com.example.KATON.command;


import com.example.KATON.model.StringWrapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Map;

@Component
public class NewUserCommand extends BaseCommand{

    public void execute(Map<String,String> allParams) throws IOException {
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
}
