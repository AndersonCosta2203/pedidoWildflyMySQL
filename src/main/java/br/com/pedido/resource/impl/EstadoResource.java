package br.com.pedido.resource.impl;

import br.com.pedido.domain.Estado;
import br.com.pedido.repository.EstadoDAO;
import br.com.pedido.resource.IResource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("estados")
public class EstadoResource implements IResource<Estado> {

    @Inject
    private EstadoDAO estadoDAO;

    @Override
    public Response findAll() {
        List<Estado> estados = estadoDAO.findAll(Estado.class);
        return Response.ok().entity(estados).build();
    }

    @Override
    public Response insert(Estado obj, UriInfo uriInfo) {
        final Estado newEstado = estadoDAO.insert(obj);
        URI uri = uriInfo.getAbsolutePathBuilder().path(newEstado.getCodigo().toString()).build();
        return Response.created(uri).entity(newEstado).build();
    }

    @Override
    public Response findById(Integer id) {
        Estado estado = estadoDAO.findById(Estado.class, id);
        return Response.ok().entity(estado).build();
    }

    @Override
    public Response update(Integer id, Estado obj) {
        obj.setCodigo(id);
        estadoDAO.update(obj);
        return  Response.noContent().build();
    }

    @Override
    public Response delete(Integer id) {
        estadoDAO.delete(Estado.class, id);
        return  Response.noContent().build();
    }

    @GET
    @Produces({ "application/json" })
    @Path("/buscar-por-nome/{nome}")
    public Response getNome(@PathParam("nome") String nome) {
        return Response.ok().entity(estadoDAO.findByNome(nome)).build();
    }
}
