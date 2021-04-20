package com.projetopi.tlgne.controllers;

import com.projetopi.tlgne.entities.CategoriaPorcentagem;
import com.projetopi.tlgne.entities.Produto;
import com.projetopi.tlgne.entities.Venda;
import com.projetopi.tlgne.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping("")
    public Venda saveVenda(@RequestBody Venda venda) {
        return vendaService.saveVenda(venda);
    }

    @GetMapping("")
    public List<Venda> findAll() {
        return vendaService.findAll();
    }
    // here
    /*@GetMapping("/totalVendas")
    public int findAllVenda (
            @RequestHeader("dataInicio") String dataInicio,
            @RequestHeader("dataFim") String dataFim){
        return vendaService.findAllVenda(dataInicio, dataFim);
    }*/

    @GetMapping("/find/{id}")
    public Venda findVendaById(@PathVariable(value = "id") long id) {
        return vendaService.findByVenda(id);
    }

    @GetMapping("/cliente/{id}")
    public Venda findVendaClienteById(@PathVariable(value = "id") long id) {
        return vendaService.findVendaClienteById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteVendaById(@PathVariable(value = "id") long id) {
        vendaService.deleteVendaById(id);
    }

    @GetMapping("/categoriasPorcentagem")
    public List<CategoriaPorcentagem> findVendasCategoriasPorcentagem (
            @RequestHeader("dataInicio") String dataInicio,
            @RequestHeader("dataFim") String dataFim) {
        return vendaService.findVendasCategoriasPorcentagem(dataInicio, dataFim);
    }
    
    @GetMapping("/porDia")
    public List<String> findVendasByDia (
            @RequestHeader("dataInicio") String dataInicio,
            @RequestHeader("dataFim") String dataFim) {
        return vendaService.findVendasByDia(dataInicio, dataFim);
    }

}
