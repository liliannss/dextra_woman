package br.com.dextra.woman.models.dtos;

import br.com.dextra.woman.models.entites.Woman;

import javax.validation.constraints.NotEmpty;

public class WomanDTO {

    @NotEmpty(message = "Campo nome deve ser preenchido")
    private String name;

    @Deprecated
    public WomanDTO() {

    }

    public WomanDTO(Woman woman) {
        this.name = woman.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}