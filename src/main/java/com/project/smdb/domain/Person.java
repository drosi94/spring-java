package com.project.smdb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@MappedSuperclass
public class Person implements Serializable {

    private static final long serialVersionUID = 2162575753674609041L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name="first_name", nullable = false)
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @Column(name="last_name", nullable = false)
    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Formula("CONCAT_WS(' ', first_name, last_name)")
    @JsonIgnore
    private String fullName;

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}