package com.comida.dto.Response;

import com.comida.enums.ComidaSabor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComidaDto {
    private String name;
    private Double kcals;
    private ComidaSabor comidaSabor;
    private String descricaoPreparo;
}
