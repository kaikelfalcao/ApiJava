
package kaike.project.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import kaike.project.controller.UsuarioController;
import kaike.project.exceptions.CpfAssociadoException;
import kaike.project.exceptions.CpfDuplicadoException;
import kaike.project.exceptions.NaoEncontradoException;
import kaike.project.exceptions.QueryException;
import kaike.project.model.Usuario;

/**
 *
 * @author kaike
 */
@Path("/usuario")
public class UsuarioResource {
    
    private final UsuarioController controller = new UsuarioController();
    
    @GET
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterUsuario(@PathParam("cpf") String cpf) {
        try {
            Usuario usuario = controller.obter(cpf);
            return Response.status(Response.Status.OK)
                    .entity(usuario)
                    .build();
        } catch (NaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario não encontrado para o CPF: " + cpf)
                    .build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrar(Usuario usuario) {
        try {
            controller.cadastrar(usuario);
        } catch (QueryException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao " + ex.getTipoOperacao() + " o usuario: " + ex.getMessage())
                    .build();
        } catch (CpfDuplicadoException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }

        return Response.status(Response.Status.CREATED).entity("Criado com Sucesso").build();
    }

    @PUT
    @Path("/{cpf}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(Usuario usuario, @PathParam("cpf") String cpf) {
        try {
            controller.atualizar(usuario, cpf);
        } catch (NaoEncontradoException ex) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario não encontrado para o CPF: " + cpf)
                    .build();
        } catch (QueryException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao " + ex.getTipoOperacao() + " o usuario: " + ex.getMessage())
                    .build();
        } catch (CpfAssociadoException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }

        return Response.status(Response.Status.OK).entity("Atualizado com Sucesso").build();

    }

    @DELETE
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remover(@PathParam("cpf") String cpf) {
        try {
            controller.remover(cpf);
        } catch (NaoEncontradoException ex) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Usuario não encontrado para o CPF: " + cpf)
                    .build();
        } catch (QueryException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao " + ex.getTipoOperacao() + " o usuario: " + ex.getMessage())
                    .build();
        } catch (CpfAssociadoException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }

        return Response.status(Response.Status.NO_CONTENT).entity("Removido com Sucesso").build();

    }
    
}
