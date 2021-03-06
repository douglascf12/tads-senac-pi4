package com.projetopi.tlgne.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length=2000)
    @NotNull
    private String nome;
    @Column(length=2000)
    @NotNull
    private String descricao;
    @NotNull
    private float preco;
    @NotNull
    private int quantidadeEstoque;
    @NotNull
    private String categoria;
    @NotNull
    private int qtdEstrelas;
    @NotNull
    private int status;

    @Transient
    @JsonIgnore
    private List<String> caminhoImagem = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "produto")
    private List<Imagem> imagens = new ArrayList<>();

    public Produto() {
    }

    public Produto(Long id, String nome, String descricao, float preco, int quantidadeEstoque, String categoria, int qtdEstrelas, int status, List<String>  caminhoImagem) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.categoria = categoria;
        this.qtdEstrelas = qtdEstrelas;
        this.status = status;
        this.caminhoImagem = caminhoImagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.lang.String getNome() {
        return nome;
    }

    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }

    public java.lang.String getDescricao() {
        return descricao;
    }

    public void setDescricao(java.lang.String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public java.lang.String getCategoria() {
        return categoria;
    }

    public void setCategoria(java.lang.String categoria) {
        this.categoria = categoria;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getQtdEstrelas() {
        return qtdEstrelas;
    }

    public void setQtdEstrelas(int qtdEstrelas) {
        this.qtdEstrelas = qtdEstrelas;
    }

    public List<String>  getCaminhoImagem() {
        return caminhoImagem;
    }

    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem.add(caminhoImagem);
    }

}
