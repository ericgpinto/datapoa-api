package com.ericpinto.datapoa.service;

import com.ericpinto.datapoa.client.operations.LineClientOperations;
import com.ericpinto.datapoa.model.Line;
import com.ericpinto.datapoa.repository.LineRepository;
import com.ericpinto.datapoa.service.exceptions.ObjectNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LineService {

    private final LineRepository lineRepository;
    private final LineClientOperations lineClientUtils;

    public List<Line> getAllLines() throws JsonProcessingException {
        var lines = lineClientUtils.mapStringToJson();
        if (lineRepository.count() == 0)
            return lineRepository.saveAll(lines);
        else
            return lines;
    }

    public Page<Line> findAll(Pageable pageable) {
        return lineRepository.findAll(pageable);
    }

    public Line getLineById(String id) {
        return lineRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Linha não encontrada"));
    }

    public Line getIdLine(String idLine){
        return lineRepository.findByIdLine(idLine);
    }

    public Line createLine(Line line) {

        var verifyLine = getIdLine(line.getIdLine());

        if (verifyLine == null){
            return lineRepository.insert(line);
        }
        else {
            line.setIdentifier(verifyLine.getIdentifier());
            return update(verifyLine.getIdentifier(), line);
        }
    }

    public void delete(String id) {
        getLineById(id);
        lineRepository.deleteById(id);
    }

    public Line update(String id, Line line) {
        var objUser = getLineById(id);
        objUser.setIdentifier(line.getIdentifier());
        objUser.setCode(line.getCode());
        objUser.setName(line.getName());

        return lineRepository.save(line);
    }

    public List<Line> findLineByName(String name) {
        var result = lineRepository.findByNameContainingIgnoreCase(name);
        if (result.isEmpty()){
            throw new ObjectNotFoundException("Não foi possivel encontrar uma linha com o nome informado.");
        }
        else
            return result;
    }
}
