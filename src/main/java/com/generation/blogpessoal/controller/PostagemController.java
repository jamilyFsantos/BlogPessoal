package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

@RestController // define que essa classe vai ser um crontroller

//define por qual RI essa classe será acessada (na barra de pesquisa do Insomnia depois de localhost:8080/, colocar o nome que foi definido ai)
@RequestMapping("/postagens") 

@CrossOrigin(origins="*") //faz com que essa api aceite aquisiçoes de diferentes origens

public class PostagemController {

	//transfere a responsabilidade de construir as consultas no BD  
	@Autowired
	private PostagemRepository repository; // esse repository é como vamos chamar a interface aqui nessa classe

	
	@GetMapping
	public List<Postagem> getAll() {
		return repository.findAll();
	}
	
	//la no insomnia vamos pedir para ver os dados do BD refente ai a id que escolhermos, ai esse metodo vai mostrar pra gente os dados do id que escolhermos
	@GetMapping("/{id}")
	
	//o @PathV... é para esse metodo pegar o valor que vem pela url, então a pessoa usando o insomnia escolhe o id 2, ai vai aparecer os dados desse id
	public ResponseEntity<Postagem> GetById(@PathVariable long id){
		return repository.findById(id) //aqui vai ver o valor  recebido da url e buscar no banco
				.map(resp -> ResponseEntity.ok(resp))//aqui vai ver se o valor recebido bate com um que tenha no banco e ai retorna o dado
				.orElse(ResponseEntity.notFound().build());//aqui é se caso o valor recebido não bate com nenhum no banco , ai retorna um not found
	
	}
	
 	
	//fazer a busca pelo titulo
	@GetMapping("/titulo/{titulo}")//o titulo ta assim para não dar erro e não ter duplicidade com o outro metodo
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	
	//Usando o POST
	@PostMapping
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	//Usando o Put
	@PutMapping
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
	
}