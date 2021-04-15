package com.ericpinto.datapoa.service;

import com.ericpinto.datapoa.model.BusLine;
import com.ericpinto.datapoa.repository.BusLineRepository;
import com.ericpinto.datapoa.service.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BusLineService {

    private final BusLineRepository busLineRepository;

    public Page<BusLine> findAll(Pageable pageable) {
        return busLineRepository.findAll(pageable);
    }

    public List<BusLine> getLineByName(String name) {
        return busLineRepository.findByNameContainingIgnoreCase(name);
    }

    public BusLine createNewBusLine(BusLine busLine) {
        var find = busLineRepository.findByLine(busLine.getLine());

        Optional<BusLine> optionalBusLine = Optional.ofNullable(find);

        if (optionalBusLine.isPresent()) {
            return update(find.getId(), busLine);
        }

        return busLineRepository.insert(busLine);
    }

    public BusLine update(String id, BusLine busLine) {
        var obj = findById(id);

        obj.setLine(busLine.getLine());
        obj.setCode(busLine.getCode());
        obj.setName(busLine.getName());
        obj.setCoordenates(busLine.getCoordenates());

        return busLineRepository.save(obj);
    }

    public BusLine findById(String id) {
        return busLineRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Linha de onibus não encontrada"));
    }

    public void delete(String id) {
        findById(id);
        busLineRepository.deleteById(id);
    }

}
