package com.example.KATON.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StringWrapper {
    @Setter
    @Getter
    @Id
    @GeneratedValue
    private Long id;

    private String valore;

    public StringWrapper(String value) {
        this.valore = value;
    }

    public StringWrapper() {
        super();
    }
}
