package com.drnavalhabarbershop.resourceserver.web.controller;

import com.drnavalhabarbershop.resourceserver.domain.Home;
import com.drnavalhabarbershop.resourceserver.repository.HomeRepository;
import com.drnavalhabarbershop.resourceserver.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @GetMapping
    public Home getHomeAttr(){
        return homeService.find();
    }
    @PostMapping("/save")
    private Home updateHomeAttr(@RequestBody Home request){
        return homeService.save(request);
    }
}
