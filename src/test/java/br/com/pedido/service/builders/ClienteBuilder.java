package br.com.pedido.service.builders;

import br.com.pedido.entities.Cliente;

public class ClienteBuilder {

	public static Cliente umCliente() {
		Cliente cliente = new Cliente();
		cliente.setId(1);
		return cliente;
	}
}
