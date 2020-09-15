package com.eventoapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
public class Convidado {

    @Id
    @NotEmpty
    @NotBlank
    @NotNull
    private String rg;

    @Column
    @NotEmpty
    @NotBlank
    @NotNull
    private String nomeConvidado;

    @ManyToOne
    private Evento evento;


}
