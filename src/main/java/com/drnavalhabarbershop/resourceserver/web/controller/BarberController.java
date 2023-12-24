package com.drnavalhabarbershop.resourceserver.web.controller;

import com.drnavalhabarbershop.resourceserver.domain.Barber;
import com.drnavalhabarbershop.resourceserver.domain.Client;
import com.drnavalhabarbershop.resourceserver.domain.RatingEntry;
import com.drnavalhabarbershop.resourceserver.mapper.BarberMapper;
import com.drnavalhabarbershop.resourceserver.service.BarberService;
import com.drnavalhabarbershop.resourceserver.service.ClientService;
import com.drnavalhabarbershop.resourceserver.web.dto.BarberRequest;
import com.drnavalhabarbershop.resourceserver.web.dto.BarberResponse;
import com.drnavalhabarbershop.resourceserver.web.dto.RateBarberRequest;
import jakarta.validation.Valid;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/barbers")
@Validated
@CrossOrigin("*")
public class BarberController {

    @Autowired
    private BarberService barberService;
    @Autowired
    private ClientService clientService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Barber createBarber(@Valid @RequestBody BarberRequest request) throws IOException {
        return barberService.save(request);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Barber findBarberById(@Valid @PathVariable String id){

        Barber barber = barberService.findById(id);

        return barber;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Barber> findBarbers(){
        return barberService.findAll();
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Barber updateBarber(@Valid @PathVariable String id,
                                @Valid @RequestBody BarberRequest request) throws Exception {
        return barberService.update(id, request);
    }

    @PutMapping(value = "/rating/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Barber rateBarber(@Valid @PathVariable String id,
                                @Valid @RequestBody RateBarberRequest request) throws Exception {
        Client client = clientService.findClientById(request.getClientId());
        RatingEntry ratingEntry = new RatingEntry(client, request.getRating());

        return barberService.rateBarber(id, ratingEntry);
    }
    @PutMapping(value = "/updatePicture/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Barber updateBarberPicture(@Valid @PathVariable String id,
                                      @Valid @RequestParam(value = "file", required = false) Optional<MultipartFile> file) throws Exception {

        BarberRequest barberRequest = BarberMapper.toBarberRequest(barberService.findById(id));
        if(file.isPresent()){
            barberRequest.setProfilePicture(new Binary(BsonBinarySubType.BINARY,file.get().getBytes()));
        }
        System.out.println(barberRequest);

        return barberService.update(id, barberRequest);

    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBarber(@Valid @PathVariable String id) {
        barberService.delete(id);
    }
}
