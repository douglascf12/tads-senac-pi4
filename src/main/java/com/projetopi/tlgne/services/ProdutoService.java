package com.projetopi.tlgne.services;

import com.projetopi.tlgne.entities.Imagem;
import com.projetopi.tlgne.entities.Produto;
import com.projetopi.tlgne.repositories.ProdutoRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ImagemService imagemService;

    public ProdutoService() {
    }

    public ProdutoService(ProdutoRepository produtoRepository, ImagemService imagemService) {
        this.produtoRepository = produtoRepository;
        this.imagemService = imagemService;
    }

    public List<Produto> findCategoria(String categoria, String habilitado) {
        try {
            if (habilitado.equals("true")) {
                return produtoRepository.findAllCategoria(categoria, "1");
                
            } else {
                
                return produtoRepository.findAllCategoria(categoria);
            }
        } catch (Exception e) {
            System.out.println("Erro ao Buscar Categoria do Produto");

        }
        return null;
    }

    public List<Produto> findAll(String habilitado) {

        if (habilitado.equals("true")) {
            return produtoRepository.findAllProdutoHabilitado("1");

        } else if (habilitado.equals("false")) {
            return produtoRepository.findAll();

        }
        return null;
    }

    public List<Produto> findAllSemelanca(String semelhanca, String habilitado) {
        try {
            if (habilitado.equals("true")) {
                return produtoRepository.findAllSemelhanca(semelhanca, "1");

            } else {

                return produtoRepository.findAllSemelhanca(semelhanca);
            }
        } catch (Exception e) {
            System.out.println("Erro ao Buscar Produto por semelhança");

        }
        return null;
    }


    public Produto findById(long id) {
        return produtoRepository.findById(id);
    }

    public Produto saveProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deleteById(long id) {

        List<Imagem> imgs = imagemService.findAllProduto(id);//verificando se existe imagens associadas ao produto
        if (imgs.isEmpty()) {
            produtoRepository.deleteById(id);

        } else {
            imagemService.deleteAll(imgs);
            produtoRepository.deleteById(id);
        }
    }

    public Produto saveUpdateProduto(Produto produto) throws NotFoundException {

        if (produtoRepository.existsById(produto.getId())) {
            return produtoRepository.save(produto);
        }
        throw new NotFoundException("Produto não cadastrado");
    }

}
