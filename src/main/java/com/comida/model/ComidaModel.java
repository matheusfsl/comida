package com.comida.model;

import com.comida.enums.ComidaSabor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_comida")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComidaModel {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "kcals", nullable = false)
    private Double kcals;

    @Column(name = "sabor", nullable = false)
    @Enumerated(EnumType.STRING)
    private ComidaSabor sabor;

    @Column(name = "descricao", nullable = false)
    private String descricaoPreparo;


    @Column(name = "isActive",nullable = false)
    private boolean isActive;
}
