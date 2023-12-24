package com.drnavalhabarbershop.resourceserver.web.controller;

import com.drnavalhabarbershop.resourceserver.domain.Chair;
import com.drnavalhabarbershop.resourceserver.mapper.ChairMapper;
import com.drnavalhabarbershop.resourceserver.service.ChairService;
import com.drnavalhabarbershop.resourceserver.web.dto.ChairRequest;
import com.drnavalhabarbershop.resourceserver.web.dto.ChairResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chairs")
@Validated
@CrossOrigin("*")
public class ChairController {

    @Autowired
    private ChairService chairService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Chair createChair(@Valid @RequestBody ChairRequest request) {
        return chairService.save(request);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Chair findChairById(@Valid @PathVariable String id) {
        return chairService.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<Chair> findChairs() {

//        List<Chair> chairs = chairService.findAll();
//        List<ChairResponse> chairResponses = new ArrayList<>();
//
//        for(int i = 0; i < chairs.size(); i++){
//            ChairResponse chairResponse = ChairMapper.toChairResponse(chairs.get(i));
//            chairResponses.add(chairResponse);
//        }
        return chairService.findAll();
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Chair updateChair(
            @Valid @PathVariable String id,
            @Valid @RequestBody ChairRequest request) {

        return chairService.update(id, request);
    }

    @DeleteMapping(value = "/delete/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteChair(@Valid @PathVariable String id) {
        chairService.delete(id);
    }
}
