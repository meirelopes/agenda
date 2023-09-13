package com.exemplo.agenda.controller;

import com.exemplo.agenda.model.Contato;
import com.exemplo.agenda.model.Endereco;
import com.exemplo.agenda.repository.ContatoRepository;
import com.exemplo.agenda.repository.EnderecoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoRepository contatoRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    Logger logger = LoggerFactory.getLogger(ContatoController.class);

    //Lista todos os contatos
    @GetMapping
    public List<Contato> listar() {

        return contatoRepository.findAll();

    }

    //Buscar contato por id
    @GetMapping("/{id}")
    public Optional<Contato> listarPorId(@PathVariable Long id){

        return contatoRepository.findById(id);
    }

    // Deletar contatopor id
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {

        contatoRepository.deleteById(id);

    }

    // Cadastrar contato
    @PostMapping
    public Contato cadastrar(@RequestBody Contato contato) {
        Long idEndereco = contato.getEndereco().getId();
        Endereco endereco = enderecoRepository.findById(idEndereco).get();
        contato.setEndereco(endereco);
        return contatoRepository.save(contato);

    }

    // Atualizar contato
    @PutMapping("/{id}")
    public Contato atualizar(@RequestBody Contato contato, @PathVariable Long id) {

        Contato contatoAtualizado = contatoRepository.findById(id).get();

        if(contatoAtualizado != null){

            BeanUtils.copyProperties(contato, contatoAtualizado, "id");
            contatoRepository.save(contatoAtualizado);
        }

        return contatoAtualizado;
    }

}
