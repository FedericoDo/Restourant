package com.example.KATON.model;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

public class Prezzario {
    private Map<String, Double> table;

    public Prezzario() throws IOException {
        table=new LinkedHashMap<>();
        Path currentDirectoryPath = FileSystems.getDefault().getPath("");
        String currentDirectoryName = currentDirectoryPath.toAbsolutePath().toString();
        BufferedReader reader = new BufferedReader(new FileReader(currentDirectoryName+"/src/main/resources/database/prezzario"));
        String currentLine = reader.readLine();
        while (currentLine != null) {
            String[] parts = currentLine.split("-");
            table.put(parts[0],Double.parseDouble(parts[1]));
            currentLine = reader.readLine();
        }
    }

    public Map<String, Double> getTable() {
        return table;
    }

    public double priceOf(String name){
        return table.get(name);
    }
}
