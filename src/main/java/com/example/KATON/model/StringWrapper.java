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
    @Id
    @GeneratedValue
    private Long id;

    private String valore;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public StringWrapper(String value) {
        this.valore = value;
    }

    public StringWrapper() {
        super();
    }
}
