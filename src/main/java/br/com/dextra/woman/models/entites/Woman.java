package br.com.dextra.woman.models.entites;

import br.com.dextra.woman.models.dtos.WomanDTO;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "mulher")
public class Woman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Campo nome deve ser preenchido")
    private String name;

    @Deprecated
    public Woman() {

    }

    public Woman(String name) {
        this.name = name;
    }

    public Woman(WomanDTO womanDTO) {
        this.name = womanDTO.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}