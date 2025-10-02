package com.comida.dto.Request;

import com.comida.enums.ComidaSabor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComidaForm {
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 100, message = "Name must be between {min} and {max} characters")
    private String name;

    @NotNull(message = "Name cannot be null")
    @Min(value = 0, message = "Kcals cannot be negative")
    private Double kcals;

    @NotBlank(message = "Sabor da comida cannot be blank")
    private ComidaSabor comidaSabor;

    @NotBlank
    @Size(min = 100, max = 500, message = "Descrição do preparo must be between {min} and {max} characters")
    private String descricaoPreparo;

}
