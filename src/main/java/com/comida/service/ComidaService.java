package com.comida.service;

import com.comida.dto.Request.ComidaForm;
import com.comida.dto.Response.ComidaDto;
import com.comida.exceptions.ComidaAlreadyExistsException;
import com.comida.exceptions.ComidaInsertException;
import com.comida.exceptions.ComidaNotFoundException;
import com.comida.model.ComidaModel;
import com.comida.repository.ComidaRepository;
import com.comida.utils.ComidaMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ComidaService {
    private ComidaRepository comidaRepository;
    private ComidaMapper comidaMapper;

    public ComidaService(ComidaRepository comidaRepository, ComidaMapper comidaMapper) {
        this.comidaRepository = comidaRepository;
        this.comidaMapper = comidaMapper;
    }

    public ComidaModel getComidaModelByName(String comidaName) {
        return comidaRepository.findByNameContainingIgnoreCase(comidaName)
                .orElseThrow(() -> new ComidaNotFoundException(
                        String.format("A comida '%s' não foi encontrada", comidaName)
                ));
    }

    public ComidaDto getComidaByName(String comidaName) {
        ComidaModel comidaModel = getComidaModelByName(comidaName);
        return comidaMapper.modelToDto(comidaModel);
    }

    public Set<ComidaDto> getAllComidas(){
        Set<ComidaModel> comidaModels = comidaRepository.findByIsActiveTrue();
        if (comidaModels.isEmpty()){
            throw new ComidaNotFoundException(
                    String.format("Comidas não encontradas")
            );
        }
        return comidaMapper.setDtoToSetModel(comidaModels);
    }

    public ComidaDto createComida(ComidaForm comidaForm) {
        if (comidaRepository.findByNameContainingIgnoreCase(comidaForm.getName()).isPresent()) {
            throw new ComidaAlreadyExistsException(
                    String.format("A comida '%s' ja existe", comidaForm.getName())
            );
        }
        try {
            ComidaModel newComidaModel = comidaMapper.formToModel(comidaForm);
            comidaRepository.save(newComidaModel);
            return comidaMapper.modelToDto(newComidaModel);
        } catch (DataIntegrityViolationException err) {
            throw new ComidaInsertException(
                    String.format("Falha ao cadastrar a comida '%s'. Tente novamente ", comidaForm.getName())
            );
        }
    }


}
