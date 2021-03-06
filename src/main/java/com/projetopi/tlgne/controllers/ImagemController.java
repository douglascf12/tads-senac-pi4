package com.projetopi.tlgne.controllers;

import com.projetopi.tlgne.entities.Imagem;

import java.io.IOException;
import java.util.List;

import com.projetopi.tlgne.entities.Produto;
import com.projetopi.tlgne.services.ImagemService;
import com.projetopi.tlgne.services.ProdutoService;
import javassist.NotFoundException;
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
    public Produto saveProdutoComImagem (@RequestPart List<MultipartFile> file,@PathVariable(value = "id") long id,
                                         @RequestHeader("favorita") long favoritaPosicao) throws IOException, NotFoundException {
       Produto produtoRecuperado = produtoService.findById(id);
        return imagemService.saveImagemdeProduto(produtoRecuperado, file, favoritaPosicao);
    }

    @GetMapping("/produto/{id}")
    public List<Imagem> imagemPorIdProduto(@PathVariable (value = "id") long id) throws IOException {
        return imagemService.findAllImagensProduto(id);
    }

    @PutMapping("/editarFavorita")
    public void editarFavorita(@RequestHeader("idImagem") long idImagem, @RequestHeader("idProduto") long idProduto) {
       imagemService.editarFavorita(idImagem,idProduto);
    }


    @DeleteMapping("/{id}")
    public void deleteImagem(@PathVariable(value = "id") long id) {
      imagemService.delete(id);
    }

}


