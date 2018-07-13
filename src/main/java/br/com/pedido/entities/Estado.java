package br.com.pedido.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "estado")
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column
    private String nome;

    /*
       @JsonIgnore
       @OneToMany = Um Estado tem (0 ou várias) Cidade(s) ligadas a ele
       mappedBy = nome do campo na Entidade Cidade
       targetEntity = A classe de entidade que é o destino da associação
     */
    @JsonIgnore
    @OneToMany(mappedBy = "estado", targetEntity = Cidade.class)
    private List<Cidade> cidades = new ArrayList<>();

    public Estado(String nome) {
        this.nome = nome;
    }

    public Estado() { }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Estado {" +
                "\ncodigo=" + codigo +
                ",\nnome='" + nome + '\'' +
                '}';
    }
}
