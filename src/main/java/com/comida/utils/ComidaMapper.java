package com.comida.utils;

import com.comida.dto.Request.ComidaForm;
import com.comida.dto.Response.ComidaDto;
import com.comida.model.ComidaModel;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ComidaMapper {

    public ComidaModel formToModel(ComidaForm comidaForm){
        if (comidaForm == null){
            return null;
        }
        ComidaModel comidaModel = new ComidaModel();
        comidaModel.setName(comidaForm.getName());
        comidaModel.setKcals(comidaForm.getKcals());
        comidaModel.setSabor(comidaForm.getComidaSabor());
        comidaModel.setDescricaoPreparo(comidaForm.getDescricaoPreparo());
        comidaModel.setActive(true);

        return comidaModel;
    }

    public ComidaDto modelToDto(ComidaModel comidaModel){
        if (comidaModel == null){
            return null;
        }
        ComidaDto comidaDto = new ComidaDto();

        comidaDto.setName(comidaModel.getName());
        comidaDto.setComidaSabor(comidaModel.getSabor());
        comidaDto.setKcals(comidaModel.getKcals());
        comidaDto.setDescricaoPreparo(comidaModel.getDescricaoPreparo());
        return comidaDto;
    }

    public ComidaModel updateFormToModel (ComidaForm comidaForm, ComidaModel comidaModel){
        comidaModel.setName(comidaForm.getName());
        comidaModel.setSabor(comidaForm.getComidaSabor());
        comidaModel.setKcals(comidaForm.getKcals());
        comidaModel.setDescricaoPreparo(comidaForm.getDescricaoPreparo());
        return comidaModel;
    }

    public Set<ComidaDto> setDtoToSetModel (Set<ComidaModel> comidaModels){
        if (comidaModels == null || comidaModels.isEmpty()){
            return Collections.emptySet();
        }
        return comidaModels.stream()
                .map(this::modelToDto)
                .collect(Collectors.toSet());
    }

    public ComidaModel patchFormToModel(ComidaForm comidaForm, ComidaModel comidaModel){
        if (comidaForm.getName()!=null && !comidaForm.getName().isBlank()){
            comidaModel.setName(comidaForm.getName());
        }
        if (comidaForm.getComidaSabor() != null) {
            comidaModel.setSabor(comidaForm.getComidaSabor());
        }
        if (comidaForm.getKcals()!=null && !comidaForm.getKcals().isNaN()){
        comidaModel.setKcals(comidaForm.getKcals());
        }
        if (comidaForm.getDescricaoPreparo()!= null && !comidaForm.getDescricaoPreparo().isBlank()){
            comidaModel.setDescricaoPreparo(comidaForm.getDescricaoPreparo());
        }
        return comidaModel;
    }
}
