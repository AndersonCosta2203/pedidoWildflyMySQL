package br.com.pedido.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cidade")
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    @Column
    private String nome;

    /*
        @ManyToOne = Muitos Estado(s) para uma cidade, cada cidade tem um Estado
        @JoinColumn = Para criar uma coluna estado_id, que se refere ao Estado
     */
    @ManyToOne()
    @JoinColumn(name = "estado_id")
    private Estado estado;

    public Cidade() { }

    public Cidade(String nome) {
        this.nome = nome;
    }

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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "\ncodigo=" + codigo +
                ",\nnome='" + nome + '\'' +
                ",\nestado=" + estado +
                '}';
    }
}
