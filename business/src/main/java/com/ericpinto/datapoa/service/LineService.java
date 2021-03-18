package com.ericpinto.datapoa.service;

import com.ericpinto.datapoa.client.LineClient;
import com.ericpinto.datapoa.model.Line;
import com.ericpinto.datapoa.repository.LineRepository;
import com.ericpinto.datapoa.service.exceptions.ObjectNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LineService {

    private final LineRepository lineRepository;
    private final LineClient lineClient;
    private final ObjectMapper objectMapper;

    public List<Line> getAllLines() throws JsonProcessingException {
        String json = lineClient.getAllLines();
        var line =  Arrays.asList(objectMapper.readValue(json, Line[].class));
        return lineRepository.saveAll(line);
    }


    public Line getLineById(String id) {
        return lineRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Linha não encontrada"));
    }

    public Line createLine(Line line) {
        return lineRepository.insert(line);
    }

    public void delete(String id) {
        getLineById(id);
        lineRepository.deleteById(id);
    }

    public Line update(String id, Line line) {
        var objUser = getLineById(id);
        objUser.setId(line.getId());
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
