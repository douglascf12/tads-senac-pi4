package com.projetopi.tlgne.controllers;


import com.projetopi.tlgne.entities.Funcionario;
import com.projetopi.tlgne.entities.Usuario;
import com.projetopi.tlgne.services.FuncionarioService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("")
    public List<Funcionario> findAll(){
        return funcionarioService.findAll();
    }

    @GetMapping("/{nomeFuncionario}")
    public Funcionario findBy(@PathVariable(value = "nomeFuncionario") String nomeFuncionario){
        return funcionarioService.findByNomeFuncionario(nomeFuncionario);
    }

    @GetMapping("/find/{id}")
    public Funcionario findById(@PathVariable(value = "id") long id){
        return funcionarioService.findById(id);
    }

    @GetMapping("/cpf")
    public Funcionario verificarCpfJaCadastrado(@RequestHeader("cpf") String cpf){
        return funcionarioService.verificarCpfJaCadastrado(cpf);
    }

    @CrossOrigin
    @PostMapping(value = "")
    public Funcionario saveFuncionario(@RequestBody Funcionario funcionario) {
        return funcionarioService.saveFuncionario(funcionario);
    }
    @CrossOrigin
    @PutMapping("")
    public Funcionario updateFuncionario(@RequestBody Funcionario funcionario) throws NotFoundException {
        return funcionarioService.saveUpdateFuncionario(funcionario);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void deleteFuncionario(@PathVariable(value = "id") long id) {
        funcionarioService.deleteById(id);
    }
}
