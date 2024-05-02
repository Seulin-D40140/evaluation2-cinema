package fr.fms.entities;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class City implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String postalCode;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private Collection<Cinema> cinema;

    public City(String name , String postalCode)
    {
        this.name = name;
        this.postalCode = postalCode;
    }
}
