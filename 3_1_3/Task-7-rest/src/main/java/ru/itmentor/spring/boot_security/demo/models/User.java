package ru.itmentor.spring.boot_security.demo.models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users1")
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Enter the correct value")
    @Size(min = 2, max = 20, message = "Enter the correct value")
    @Column(name = "name")
    private String username;

    @NotEmpty(message = "Enter the correct value")
    @Column(name="password")
    private String password;
    @Min(value = 0, message = "Enter the correct value")
    private int grade;
    @Email(message = "Enter correct email")
    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String name, int grade, String email) {
        this.username = name;
        this.grade = grade;
        this.email = email;
    }
    public User(String username, int grade, String email, String password) {
        this.username = username;
        this.grade = grade;
        this.email = email;
        this.password = password;
    }

    public User(String username, int grade, String email, String password, Set<Role> roles) {
        this.username = username;
        this.grade = grade;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String name) {
        this.username = name;
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

    @Override
    public String toString() {
        return "User: " + id + ", " + username + ", " + grade + ", " + email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    public void setPassword(String password) {
        this.password = password;
    }
     public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}