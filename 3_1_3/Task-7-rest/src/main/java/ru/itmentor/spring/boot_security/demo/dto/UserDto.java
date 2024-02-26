package ru.itmentor.spring.boot_security.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDto {

    @NotEmpty(message = "Enter the correct value")
    @Size(min = 2, max = 20, message = "Enter the correct value")
    private String username;

    @NotEmpty(message = "Enter the correct value")
    private String password;
    @Min(value = 0, message = "Enter the correct value")
    private int grade;
    @Email(message = "Enter correct email")
    private String email;

    public UserDto(){}

    public UserDto(String username, String password, int grade, String email) {
        this.username = username;
        this.password = password;
        this.grade = grade;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
