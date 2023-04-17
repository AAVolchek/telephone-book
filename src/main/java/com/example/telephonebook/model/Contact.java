package com.example.telephonebook.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Contacts")
public class Contact {

    @Id
    @SequenceGenerator(
            name = "contact_sequence",
            sequenceName = "contact_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contact_sequence"
    )
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(nullable = false,name ="name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Email
    @Column
    private String email;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "social_profile")
    private String socialProfile;

    public Contact(String firstName, String lastName, String phoneNumber, String email, LocalDate birthday, String socialProfile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthday = birthday;
        this.socialProfile = socialProfile;
    }
}
