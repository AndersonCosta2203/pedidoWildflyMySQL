package br.com.pedido.resource.impl;

import br.com.pedido.domain.Cidade;
import br.com.pedido.repository.CidadeDAO;
import br.com.pedido.resource.IResource;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("cidades")
public class CidadeResource implements IResource<Cidade> {

    @Inject
    private CidadeDAO cidadeDAO;

    @Override
    public Response findAll() {
        return Response.ok().entity(cidadeDAO.findAll(Cidade.class)).build();
    }

    @Override
    public Response insert(Cidade obj, UriInfo uriInfo) {
        obj.setCodigo(null);
        final Cidade newCidade = cidadeDAO.insert(obj);
        URI uri = uriInfo.getAbsolutePathBuilder().path(newCidade.getCodigo().toString()).build();
        return Response.created(uri).entity(newCidade).build();
    }

    @Override
    public Response findById(Integer id) {
        return Response.ok().entity(cidadeDAO.findById(Cidade.class, id)).build();
    }

    @Override
    public Response update(Integer id, Cidade obj) {
        obj.setCodigo(id);
        cidadeDAO.update(obj);
        return Response.noContent().build();
    }

    @Override
    public Response delete(Integer id) {
        cidadeDAO.delete(Cidade.class, id);
        return Response.noContent().build();
    }
}
