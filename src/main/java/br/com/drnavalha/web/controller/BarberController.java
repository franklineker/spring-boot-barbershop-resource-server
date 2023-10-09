package br.com.drnavalha.web.controller;

import br.com.drnavalha.domain.Barber;
import br.com.drnavalha.mapper.BarberMapper;
import br.com.drnavalha.service.BarberService;
import br.com.drnavalha.web.dto.BarberRequest;
import br.com.drnavalha.web.dto.BarberResponse;
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

@RestController
@RequestMapping("/barbers")
@Validated
@CrossOrigin("*")
public class BarberController {

    @Autowired
    private BarberService barberService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Barber createBarber(@Valid @RequestBody BarberRequest request) {

        return barberService.save(request);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public BarberResponse findBarberById(@Valid @PathVariable String id){

        Barber barber = barberService.findById(id);
        //barber.setProfilePicture(Base64.getEncoder().encodeToString(barber.getProfilePicture().getData()));

        return BarberMapper.toBarberResponse(barber);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Barber> findBarbers(){

        return barberService.findAll();
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Barber updateBarber(@Valid @PathVariable String id,
                                @Valid @RequestBody BarberRequest request) {
        return barberService.update(id, request);
    }
    @PutMapping(value = "/updatePicture/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Barber updateBarberPicture(@Valid @PathVariable String id,
                                      @Valid @RequestParam("file") MultipartFile file) throws IOException {

        BarberRequest barberRequest = BarberMapper.toBarberRequest(barberService.findById(id));
        barberRequest.setProfilePicture(new Binary(BsonBinarySubType.BINARY,file.getBytes()));
        System.out.println(barberRequest);

        return barberService.update(id, barberRequest);

    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteBarber(@Valid @PathVariable String id) {
        barberService.delete(id);
    }
}
