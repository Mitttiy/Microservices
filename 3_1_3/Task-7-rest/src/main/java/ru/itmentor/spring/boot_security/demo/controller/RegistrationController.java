package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.models.User;
import ru.itmentor.spring.boot_security.demo.service.RegistrationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("")
    public ResponseEntity<String> addUser(@RequestBody @Valid User userForm,
                                  Model model, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity<>("Введены неверные значение", HttpStatus.BAD_REQUEST);

        if(!registrationService.save(userForm)){
        return new ResponseEntity<>("Пользователь с таким именем существует!", HttpStatus.CONFLICT);}

        return new ResponseEntity<>("Пользователь успешно зарегистрирован!", HttpStatus.OK);
    }

}
