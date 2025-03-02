package com.viniciusx.proj_imc_on.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Imc {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imc_seq")
    @SequenceGenerator(name = "imc_seq", sequenceName = "imc_id_seq")
    private Long id;
    private String nome;
    private double peso;
    private double altura;
    private String sexo;
    private double valorImc;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHoraRegistro;

    @PrePersist
    protected void prePersist() {
        this.dataHoraRegistro = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    }

    public Imc(Long id) {
        this.id = id;
    }

}
