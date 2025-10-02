package com.comida.controller;

import com.comida.dto.Request.ComidaForm;
import com.comida.dto.Response.ComidaDto;
import com.comida.service.ComidaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/comidas")
public class ComidaController {
    private final ComidaService comidaService;

    public ComidaController(ComidaService comidaService) {
        this.comidaService = comidaService;
    }

    @GetMapping("/{nameComida}")
    public ResponseEntity<ComidaDto> getComidaByName(@PathVariable String nameComida){
        ComidaDto comidaDto =comidaService.getComidaByName(nameComida);
        return ResponseEntity.ok(comidaDto);
    }

    @GetMapping("/all")
    public ResponseEntity<Set<ComidaDto>> getAllComidas(){
        Set<ComidaDto> comidaDtos = comidaService.getAllComidas();
        return ResponseEntity.ok(comidaDtos);
    }

    @PostMapping
    public ResponseEntity<ComidaDto> createComdia(@RequestBody ComidaForm comidaForm){
        ComidaDto comidaDto = comidaService.createComida(comidaForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(comidaDto);
    }

}
