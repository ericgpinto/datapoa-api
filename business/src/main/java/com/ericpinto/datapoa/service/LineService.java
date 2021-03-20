package com.ericpinto.datapoa.service;

import com.ericpinto.datapoa.client.util.LineClientUtils;
import com.ericpinto.datapoa.model.Line;
import com.ericpinto.datapoa.repository.LineRepository;
import com.ericpinto.datapoa.service.exceptions.ObjectNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LineService {

    private final LineRepository lineRepository;
    private final LineClientUtils lineClientUtils;

    public List<Line> getAllLines() throws JsonProcessingException {
        var lines = lineClientUtils.getAllLines();
        if (lineRepository.count() == 0)
            return lineRepository.saveAll(lines);
        else
            return lines;
    }

    public Line getLineById(String id) {
        return lineRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Linha não encontrada"));
    }

    public Line createLine(Line line) {
        Optional<Line> optionalLine = lineRepository.findById(line.getId());
        if (optionalLine.isPresent())
            return update(line.getId(), line);
        else
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
