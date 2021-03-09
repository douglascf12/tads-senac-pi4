package com.projetopi.tlgne.controllers;

import com.projetopi.tlgne.entities.Imagem;

import java.io.IOException;
import java.util.List;

import com.projetopi.tlgne.entities.Produto;
import com.projetopi.tlgne.services.ImagemService;
import com.projetopi.tlgne.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/imagens")
public class ImagemController {

    @Autowired
    private ImagemService imagemService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/{id}")
    public Imagem imagemPorId(@PathVariable(value = "id") long id) {
        return imagemService.findById(id);
    }

    @PostMapping(value ="/produto/{id}" , consumes = {"multipart/form-data"})
    public Produto saveProdutoComImagem (@RequestPart List<MultipartFile> file,@PathVariable(value = "id") long id) throws IOException {
       Produto produtoRecuperado = produtoService.findById(id);
        return produtoService.saveProdutoComImagem(produtoRecuperado, file);
    }
//    @GetMapping("/produto/{id}")
//    public List<Imagem> imagemPorIdProduto(@PathVariable(value = "id") long id) {
//        return imagemService.findByIdProduto(id);
//    }
}
