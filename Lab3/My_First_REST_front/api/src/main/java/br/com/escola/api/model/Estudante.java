package br.com.escola.api.model;

import java.util.UUID;

public class Estudante {
    private String id;
    private String name;

	public Estudante(){

	}
    // Construtor, getters e setters
    public Estudante(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
