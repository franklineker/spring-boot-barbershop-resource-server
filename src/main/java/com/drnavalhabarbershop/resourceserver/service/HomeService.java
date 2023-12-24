package com.drnavalhabarbershop.resourceserver.service;

import com.drnavalhabarbershop.resourceserver.domain.Home;
import com.drnavalhabarbershop.resourceserver.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HomeService {
    @Autowired
    private HomeRepository homeRepository;

    public Home find() {
        Home home = new Home();
        if(homeRepository.findAll().size() > 0)  {
            home = homeRepository.findAll().get(0);
        }
        return home;
    }

    public Home save(Home request) {
        Home home = homeRepository.findAll().size() != 0 ? homeRepository.findAll().get(0) : new Home();
        String welcomeMessage = request.getWelcomeMessage() != null ? request.getWelcomeMessage() : home.getWelcomeMessage();
        String aboutBarbershop = request.getAboutBarbershop() != null ? request.getAboutBarbershop() : home.getAboutBarbershop();
        Map address = request.getAddress() != null ? request.getAddress() : home.getAddress();
        home.setWelcomeMessage(welcomeMessage);
        home.setAboutBarbershop(aboutBarbershop);
        home.setAddress(address);

        return homeRepository.save(home);
    }
}
