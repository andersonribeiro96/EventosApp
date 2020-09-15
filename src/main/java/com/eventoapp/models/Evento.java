package com.eventoapp.models;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Evento implements Serializable {

    private static final long serialVersionIUD = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @NotEmpty
    @NotBlank
    @NotNull
    private String nome;

    @NotEmpty
    @NotBlank
    @NotNull
    private String local;

    @NotEmpty
    @NotBlank
    @NotNull
    private String data;

    @NotEmpty
    @NotBlank
    @NotNull
    private String horario;

    @OneToMany( mappedBy="evento", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Convidado> convidados;
}
