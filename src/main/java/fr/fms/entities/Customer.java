package fr.fms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min=5,max=30)
    private String name;
    
    @NotNull
	@Size(min=5,max=30)
    private String sname;
    
    @NotNull
	@Size(min=5,max=30)
    private String email;
    
    @NotNull
	@Size(min=5,max=50)
    private String address;
    
    @NotNull
	@Size(min=10,max=20)
    private String phone;

}