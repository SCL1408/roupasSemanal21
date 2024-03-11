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

import app.entity.Cliente;
import app.service.ClienteService;

public class ClienteController {
	@Autowired
	public ClienteService clienteService;

	@GetMapping("/findAll")
	public ResponseEntity<List<Cliente>> findAll() {
		try {
			return new ResponseEntity<List<Cliente>>(this.clienteService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable long id) {
		try {
			return new ResponseEntity<Cliente>(this.clienteService.findById(id), HttpStatus.FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Cliente cliente) {
		try {
			this.clienteService.save(cliente);
			return new ResponseEntity<String>("O cliente " + cliente.getNome() + " foi cadastrado com sucesso", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Houve o erro: " + e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/updade/{id}")
	public ResponseEntity<String> update(@PathVariable long id, @RequestBody Cliente cliente){
		try {
			this.clienteService.update(id, cliente);
			return new ResponseEntity<String>("O cliente " + cliente.getNome() + " foi atualizado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Houve o erro: " + e, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable long id){
		try {
			return new ResponseEntity<String>("O cliente " + this.clienteService.delete(id) + " foi deletado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Houve o erro: " + e, HttpStatus.BAD_REQUEST);
		}
	}

}
