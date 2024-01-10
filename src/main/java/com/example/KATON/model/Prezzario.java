package com.example.KATON.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Prezzario {
    private Map<String, Double> table;

    public Prezzario(){
        table=new LinkedHashMap<>();
        table.put("pasta",10.4);
        table.put("carne",13.4);
        table.put("dolce",5.4);
    }

    public Map<String, Double> getTable() {
        return table;
    }

    public double priceOf(String name){
        return table.get(name);
    }
}
