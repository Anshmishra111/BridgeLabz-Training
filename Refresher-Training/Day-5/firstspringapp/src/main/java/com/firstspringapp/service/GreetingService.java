package com.firstspringapp.service;

import com.firstspringapp.model.Greeting;
import com.firstspringapp.repository.GreetingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreetingService {

    private final GreetingRepository greetingRepository;

    public GreetingService(GreetingRepository greetingRepository) {
        this.greetingRepository = greetingRepository;
    }

    public Greeting createGreeting(String message) {

        Greeting greeting = new Greeting(message);

        return greetingRepository.save(greeting);
    }

    public List<Greeting> getAllGreetings() {

        return greetingRepository.findAll();
    }

    public Greeting getGreetingById(Long id) {

        return greetingRepository.findById(id)
                .orElse(null);
    }

    public void deleteGreeting(Long id) {

        greetingRepository.deleteById(id);
    }
}