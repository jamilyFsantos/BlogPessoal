package com.generation.blogpessoal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import com.sun.istack.NotNull;

@Entity
@Table(name= "tb_postagem")
public class Postagem {
	
// define a coluna de id como chave primaria	
  @Id
  
// equivalente ao auto increment no mysql
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;
  
  // define que o campo é obrigatório
  @NotNull
  
 // define um numero minimo e maximo de caracteres no campo 
  @Size(min = 5, max = 100, message="O campo deve ter no minimo 5 caracteres, e no maximo 100 caracteres")
  public String titulo;
  
  @NotNull
  @Size(min = 5, max = 500)
  public String texto;
  
  //@Temporal(TemporalType.TIMESTAMP)
 // private Date data = new java.sql.Date(System.currentTimeMillis());
  

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getTitulo() {
	return titulo;
}

public void setTitulo(String titulo) {
	this.titulo = titulo;
}

public String getTexto() {
	return texto;
}

public void setTexto(String texto) {
	this.texto = texto;
}
  
  
  
}
