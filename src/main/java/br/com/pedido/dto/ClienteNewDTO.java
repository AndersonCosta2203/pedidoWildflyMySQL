package br.com.pedido.dto;

import java.io.Serializable;

public class ClienteNewDTO implements Serializable {

    private String nome;
    private String email;
    private String cpfOuCnpj;
    private Integer tipo;
    private EnderecoNewDTO endereco = new EnderecoNewDTO();

    public ClienteNewDTO() {
    }

    public ClienteNewDTO(String nome, String email, String cpfOuCnpj, Integer tipo, EnderecoNewDTO endereco) {
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.tipo = tipo;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public EnderecoNewDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoNewDTO endereco) {
        this.endereco = endereco;
    }
}
