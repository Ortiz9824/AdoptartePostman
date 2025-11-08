package com.example.proyectoFormativo.Dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class AuthorDto {

    private long Id;

    @NotBlank(message = "{author.firstName.notBlank}")
    @Size(max = 60, message = "{author.firstName.size}")
    // Solo letras, espacios y acentos comunes (ajusta a tu realidad)
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "{author.firstName.pattern}")
    private String firstName;

    @NotBlank(message = "{author.lastName.notBlank}")
    @Size(max = 60, message = "{author.lastName.size}")
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "{author.lastName.pattern}")
    private String lastName;

    @NotNull(message = "{author.birthDate.notNull}")
    @PastOrPresent(message = "{author.birthDate.past}")
    private LocalDate birthDate;

    @NotBlank(message = "{author.nationality.notBlank}")
    @Size(max = 40, message = "{author.nationality.size}")
    // Si usas ISO-3166-1 alfa-2, cámbialo por: "^[A-Z]{2}$"
    private String nationality;

    private boolean IsActive;


    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String setFullName(String fullname){
        return fullname;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }
}
