package com.projetopi.tlgne.entities;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    @Column(unique = true)
    @NotNull
    private String cpf;
    @NotNull
    private String cargo;
    @NotNull
    private Date dataNascimento;
    @NotNull
    private String telefone;
    @OneToOne()
    @JoinColumn(name = "id_usuario", referencedColumnName = "usuario_id")
    private Usuario usuario;
    @OneToOne()
    @JoinColumn(name = "id_endereco", referencedColumnName = "id")
    private EnderecoFuncionario endereco;


}
