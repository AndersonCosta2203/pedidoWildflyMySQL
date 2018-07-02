package br.com.pedido.resource.impl;

import br.com.pedido.domain.Cliente;
import br.com.pedido.dto.ClienteNewDTO;
import br.com.pedido.repository.ClienteDAO;
import br.com.pedido.resource.IResource;
import br.com.pedido.service.ClienteService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/clientes")
public class ClienteResource implements IResource<Cliente> {

    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    private ClienteService clienteService;

    @Override
    public Response findAll() {
        return Response.ok().entity(clienteDAO.findAll(Cliente.class)).build();
    }

    @Override
    public Response insert(Cliente obj, UriInfo uriInfo) {
        return null;
    }

    @POST
    @Path("/cliente-newdto")
    @Produces({ "application/json" })
    public Response insert(ClienteNewDTO obj, @Context UriInfo uriInfo) {
        Cliente cliente = clienteService.fromClienteNewDTO(obj);

        final Cliente newCliente = clienteDAO.insert(cliente);
        URI uri = uriInfo.getAbsolutePathBuilder().path(newCliente.getId().toString()).build();
        return Response.created(uri).entity(newCliente).build();
    }

    @Override
    public Response findById(Integer id) {
        return Response.ok().entity(clienteDAO.findById(Cliente.class, id)).build();
    }

    @Override
    public Response update(Integer id, Cliente obj) {
        obj.setId(id);
        clienteDAO.update(obj);
        return Response.noContent().build();
    }

    @Override
    public Response delete(Integer id) {
        clienteDAO.delete(Cliente.class, id);
        return Response.noContent().build();
    }
}
