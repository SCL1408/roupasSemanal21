package app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entity.Funcionario;
import app.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController {
	@Autowired
	public FuncionarioService funcionarioService;

	@GetMapping("/findAll")
	public ResponseEntity<List<Funcionario>> findAll() {
		try {
			return new ResponseEntity<List<Funcionario>>(this.funcionarioService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Funcionario> findById(@PathVariable long id) {
		try {
			return new ResponseEntity<Funcionario>(this.funcionarioService.findById(id), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Funcionario funcionario) {
		try {
			this.funcionarioService.save(funcionario);
			return new ResponseEntity<String>("O funcionario " + funcionario.getNome() + " foi cadastrado com sucesso", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Houve o erro: " + e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/updade/{id}")
	public ResponseEntity<String> update(@PathVariable long id, @RequestBody Funcionario funcionario){
		try {
			this.funcionarioService.update(id, funcionario);
			return new ResponseEntity<String>("O funcionario " + funcionario.getNome() + " foi atualizado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Houve o erro: " + e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id){
		try {
			return new ResponseEntity<String>("O funcionario " + this.funcionarioService.delete(id) + " foi deletado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Houve o erro: " + e, HttpStatus.BAD_REQUEST);
		}
	}
}
