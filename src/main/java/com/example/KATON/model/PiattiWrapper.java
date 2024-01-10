package com.example.KATON.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter

public class PiattiWrapper {

    private List<Piatto> piatti;

    public void addPiatto(Piatto p){
        piatti.add(p);
    }
}
