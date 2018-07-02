package br.com.pedido.service;

import br.com.pedido.domain.Cidade;
import br.com.pedido.domain.Cliente;
import br.com.pedido.domain.Endereco;
import br.com.pedido.dto.ClienteNewDTO;
import br.com.pedido.repository.CidadeDAO;

import javax.inject.Inject;

public class ClienteService {

    @Inject
    private CidadeDAO cidadeDAO;


    public Cliente fromClienteNewDTO(ClienteNewDTO obj) {

        Cliente cliente = new Cliente(obj.getNome(), obj.getEmail(), obj.getCpfOuCnpj(), obj.getTipo());

        Cidade cidade = cidadeDAO.findById(Cidade.class, obj.getEndereco().getCidade());

        Endereco endereco = new Endereco(obj.getEndereco().getLogradouro(), obj.getEndereco().getNumero(), obj.getEndereco().getComplemento(),
                obj.getEndereco().getBairro(), obj.getEndereco().getCep(), cidade, cliente);

        cliente.getEnderecos().add(endereco);

        return cliente;
    }
}
