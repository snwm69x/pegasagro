package com.testcase.pegasagro.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dealer")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Dealer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String representativeName;

    @Builder.Default
    @OneToMany(mappedBy = "dealer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Owner> owners = new ArrayList<>();

}