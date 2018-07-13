package br.com.pedido.util.data;

import br.com.pedido.entities.Cidade;

public interface CidadeData extends GenericData<Cidade>{
	
	public Cidade findByNome(String nome);
}
