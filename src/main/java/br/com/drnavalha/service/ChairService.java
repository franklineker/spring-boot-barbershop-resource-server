package br.com.drnavalha.service;

import br.com.drnavalha.domain.Chair;
import br.com.drnavalha.mapper.ChairMapper;
import br.com.drnavalha.repository.ChairRepository;
import br.com.drnavalha.web.dto.ChairRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChairService {

    @Autowired
    private ChairRepository chairRepository;

    public Chair save(ChairRequest request) {
        return chairRepository.save(ChairMapper.toChair(request));
    }

    public Chair findById(String id) {

        checkChairExists(id);

        return chairRepository.findById(id).get();
    }

    public List<Chair> findAll() {
        return chairRepository.findAll();
    }

    public Chair update(String id, ChairRequest request) {

        checkChairExists(id);

        Chair chair = ChairMapper.toChair(request);
        chair.setId(id);

        return chairRepository.save(chair);
    }

    public void delete(String id) {

        checkChairExists(id);

        chairRepository.deleteById(id);
    }

    public void checkChairExists(String id) {
        if(chairRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Chair not found.");
        }
    }

}
