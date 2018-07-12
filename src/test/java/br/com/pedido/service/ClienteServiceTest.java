package br.com.pedido.service;

import br.com.pedido.domain.Cidade;
import br.com.pedido.domain.Cliente;
import br.com.pedido.dto.ClienteNewDTO;
import br.com.pedido.repository.CidadeDAO;
import br.com.pedido.repository.DAOGenerico;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @InjectMocks
    private ClienteNewDTO clienteNewDTO;

    private Cidade cidade;

    @Mock
    private CidadeDAO cidadeDAO;

    @Before
    public void setup(){
        cidade = new Cidade();
        cidade.setCodigo(1);
    }

    @Test
    public void converterEmClienteAPartirDeUmDTO() {
        Mockito.when(cidadeDAO.findById(Cidade.class, 1)).thenReturn(cidade);

        Cliente cliente = clienteService.fromClienteNewDTO(clienteNewDTO);

        Assert.assertEquals(cliente.getId(), Integer.valueOf("1"));
    }
}