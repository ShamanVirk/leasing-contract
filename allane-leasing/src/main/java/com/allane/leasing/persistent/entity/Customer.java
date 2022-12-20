package com.allane.leasing.persistent.entity;
 
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity
@Table(name = "customer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank
    @Column(name = "first_name")
    private String firstName;
 
    @NotBlank
    @Column(name = "last_name")
    private String lastName;
 
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    private Date birthday;
}
