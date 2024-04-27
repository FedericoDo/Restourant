package com.example.KATON.model;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class Prezzario {
    private final Map<String, Double> table;

    public Prezzario() {
        table=new LinkedHashMap<>();
        Path currentDirectoryPath = FileSystems.getDefault().getPath("");
        String currentDirectoryName = currentDirectoryPath.toAbsolutePath().toString();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(currentDirectoryName + "/src/main/resources/static/database/prezzario"));
            String currentLine = reader.readLine();
            while (currentLine != null) {
                String[] parts = currentLine.split("-");
                table.put(parts[0], Double.parseDouble(parts[1]));
                currentLine = reader.readLine();
            }
        }catch (Exception e) {
            System.out.println("File not found, description: " + e);
        }
    }

    public double priceOf(String name){
        return table.get(name);
    }
}
