package com.exemplo.agenda.controller;

import com.exemplo.agenda.model.Contato;
import com.exemplo.agenda.model.Endereco;
import com.exemplo.agenda.repository.EnderecoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    //Lista todos os contatos
    @GetMapping
    public List<Endereco> listar() {

        return enderecoRepository.findAll();

    }

    //Buscar contato por id
    @GetMapping("/{id}")
    public Optional<Endereco> listarPorId(@PathVariable Long id){

        return enderecoRepository.findById(id);
    }

    // Deletar endereço por id
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {

        enderecoRepository.deleteById(id);

    }

    // Cadastrar endereço
    @PostMapping
    public Endereco cadastrar(@RequestBody Endereco endereco) {

        return enderecoRepository.save(endereco);

    }
    // Atualizar endereco
    @PutMapping("/{id}")
    public Endereco atualizar(@RequestBody Endereco endereco, @PathVariable Long id) {

        Endereco enderecoAtualizado = enderecoRepository.findById(id).get();

        if(enderecoAtualizado != null){

            BeanUtils.copyProperties(endereco, enderecoAtualizado, "id");
            enderecoRepository.save(enderecoAtualizado);
        }

        return enderecoAtualizado;
    }

}
