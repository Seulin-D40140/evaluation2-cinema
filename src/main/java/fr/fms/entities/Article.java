package fr.fms.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
//import javax.validation.constraints.DecimalMin;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;

//import org.springframework.boot.context.properties.bind.DefaultValue;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Article implements Serializable {	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
//	@NotNull
//	@Size(min=5,max=20)
	private String brand;
//	@NotNull
//	@Size(min=5,max=50)
	private String description;
//	@DecimalMin("50")
	private double price;

	private int quantity=1;
	
	@ManyToOne
	private Category category;	
	
}
