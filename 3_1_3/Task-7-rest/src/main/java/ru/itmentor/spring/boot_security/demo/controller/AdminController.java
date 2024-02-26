package ru.itmentor.spring.boot_security.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.dto.UserDto;
import ru.itmentor.spring.boot_security.demo.models.User;
import ru.itmentor.spring.boot_security.demo.service.AdminService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> users() {
        List<UserDto> userDtoList = adminService.getAllUsers().stream().map(AdminController::convertToUserDto).toList();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> show(@PathVariable("id") int id) {
        UserDto userDto = convertToUserDto(adminService.getById(id));
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> create(@RequestBody @Valid UserDto userDto,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>("Введены неверные значение", HttpStatus.BAD_REQUEST);

        User user = convertToUser(userDto);
        adminService.save(user);
        return new ResponseEntity<>("Пользователь успешно добавлен!", HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody @Valid UserDto userDto,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>("Введены неверные значение", HttpStatus.BAD_REQUEST);

        User user = convertToUser(userDto);
        adminService.update(id, user);
        return new ResponseEntity<>("Данные пользователя успешно обновлены!", HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        adminService.delete(id);
        return new ResponseEntity<>("Пользователь успешно удален!", HttpStatus.OK);
    }

    private static User convertToUser(UserDto userDto){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDto, User.class);
    }

    private static UserDto convertToUserDto(User user){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDto.class);
    }
}
