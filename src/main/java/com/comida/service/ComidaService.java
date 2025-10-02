package com.comida.service;

import com.comida.dto.Request.ComidaForm;
import com.comida.dto.Response.ComidaDto;
import com.comida.exceptions.ComidaAlreadyExistsException;
import com.comida.exceptions.ComidaInsertException;
import com.comida.exceptions.ComidaNotFoundException;
import com.comida.exceptions.ComidaUpdateException;
import com.comida.model.ComidaModel;
import com.comida.repository.ComidaRepository;
import com.comida.utils.ComidaMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Transactional
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

    @Transactional
    public ComidaDto updateComida (ComidaForm comidaForm, String comidaName){
        if (comidaRepository.findByNameContainingIgnoreCase(comidaName).isEmpty()){
            throw new ComidaNotFoundException(
                    String.format("Comida '%s' não foi encontrada. ",comidaName)
            );
        }
        ComidaModel comidaModel= getComidaModelByName(comidaName);
        try{
            ComidaModel comidaUpdatedModel= comidaMapper.updateFormToModel(comidaForm,comidaModel);
            comidaRepository.save(comidaUpdatedModel);
            return comidaMapper.modelToDto(comidaUpdatedModel);
        }catch (ConstraintViolationException | DataIntegrityViolationException | OptimisticLockingFailureException e){
            throw new ComidaUpdateException(
                    String.format("Falha ao atualizar a comida '%s'. Verifique se os dados estão corretos", comidaName)
            );
        }

    }

    @Transactional
    public ComidaDto patchComida(String comidaName, ComidaForm comidaForm ){
        if (comidaRepository.findByNameContainingIgnoreCase(comidaName).isEmpty()){
            throw new ComidaUpdateException(
                    String.format("Comida '%s' não encontrada",comidaName));
        }
        ComidaModel comidaModel = getComidaModelByName(comidaName);

        try{
            ComidaModel comidaPatchedModel = comidaMapper.patchFormToModel(comidaForm,comidaModel);
            comidaRepository.save(comidaPatchedModel);
            return comidaMapper.modelToDto(comidaPatchedModel);
        }catch (ConstraintViolationException | DataIntegrityViolationException | OptimisticLockingFailureException e){
            throw new ComidaUpdateException(
                    String.format("Falha ao atualizar a comida '%s'. Verifique se os dados estão corretos", comidaName)
            );
        }
    }

    @Transactional
    public Set<ComidaDto> createComidasLote(List<ComidaForm> comidaFormList){
        List<ComidaModel> comidaModels = comidaFormList.stream()
                .filter(form -> comidaRepository.findByNameContainingIgnoreCase(form.getName()).isEmpty())
                .map(comidaMapper::formToModel)
                .peek(model -> model.setActive(true))
                .toList();
        List<ComidaModel> saved = comidaRepository.saveAll(comidaModels);

        return saved.stream()
                .map(comidaMapper::modelToDto)
                .collect(Collectors.toSet());
    }

    @Transactional
    public void softDeleteComidaByName(String comidaName){
        ComidaModel comidaModel = getComidaModelByName(comidaName);
        comidaModel.setActive(false);
        comidaRepository.save(comidaModel);
    }

}
