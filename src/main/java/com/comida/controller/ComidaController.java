package com.comida.controller;

import com.comida.dto.Request.ComidaForm;
import com.comida.dto.Response.ComidaDto;
import com.comida.service.ComidaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    @PutMapping("/{comidaName}")
    public ResponseEntity<ComidaDto> updateComida(@PathVariable String comidaName, @RequestBody ComidaForm comidaForm){
        ComidaDto comidaDto = comidaService.updateComida(comidaForm,comidaName);
        return ResponseEntity.ok(comidaDto);
    }

    @PatchMapping("/{comidaName}")
    ResponseEntity<ComidaDto> patchComida(@PathVariable String comidaName, @RequestBody ComidaForm comidaForm){
        ComidaDto comidaDto = comidaService.patchComida(comidaName, comidaForm);
        return ResponseEntity.ok(comidaDto);
    }

    @PostMapping("/lote")
    public ResponseEntity<Set<ComidaDto>> createComida(@RequestBody List<ComidaForm> comidaFormList){
        Set<ComidaDto> comidaDtos = comidaService.createComidasLote(comidaFormList);
        return ResponseEntity.status(HttpStatus.CREATED).body(comidaDtos);
    }

    @DeleteMapping("/{comidaName}")
    public ResponseEntity<Void> deleteComida(@PathVariable String comidaName){
        comidaService.softDeleteComidaByName(comidaName);
        return ResponseEntity.noContent().build();
    }

}
