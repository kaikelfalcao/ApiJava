package kaike.project.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kaike.project.controller.PacienteController;
import kaike.project.exceptions.CpfAssociadoException;
import kaike.project.exceptions.CpfDuplicadoException;
import kaike.project.exceptions.NaoEncontradoException;
import kaike.project.exceptions.QueryException;
import kaike.project.model.Paciente;

/**
 *
 * @author kaike
 */
@Path("/paciente")
public class PacienteResource {

    private PacienteController controller = new PacienteController();

    @GET
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterPacientePorCPF(@PathParam("cpf") String cpf) {
        try {
            Paciente paciente = controller.obterPacientePorCPF(cpf);
            return Response.status(Response.Status.OK)
                    .entity(paciente)
                    .build();
        } catch (NaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Paciente não encontrado para o CPF: " + cpf)
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, String>> listarTodosOsPacientes() {
        List<Paciente> pacientes = controller.listarTodosOsPacientes();
        List<Map<String, String>> pacientesComCamposNaoNulos = new ArrayList<>();

        for (Paciente paciente : pacientes) {
            Map<String, String> pacienteMap = new HashMap<>();

            if (paciente.getCpf() != null) {
                pacienteMap.put("cpf", paciente.getCpf());
            }
            if (paciente.getNome() != null) {
                pacienteMap.put("nome", paciente.getNome());
            }
            if (paciente.getEmail() != null) {
                pacienteMap.put("email", paciente.getEmail());
            }

            if (!pacienteMap.isEmpty()) {
                pacientesComCamposNaoNulos.add(pacienteMap);
            }
        }

        return pacientesComCamposNaoNulos;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarPaciente(Paciente paciente) {
        try {
            controller.cadastrarPaciente(paciente);
        } catch (QueryException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao " + ex.getTipoOperacao() + " o paciente: " + ex.getMessage())
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
    public Response atualizarPaciente(Paciente paciente, @PathParam("cpf") String cpf) {
        try {
            controller.atualizarPaciente(paciente, cpf);
        } catch (NaoEncontradoException ex) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Paciente não encontrado para o CPF: " + cpf)
                    .build();
        } catch (QueryException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao " + ex.getTipoOperacao() + " o paciente: " + ex.getMessage())
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
    public Response removerPaciente(@PathParam("cpf") String cpf) {
        try {
            controller.removerPaciente(cpf);
        } catch (NaoEncontradoException ex) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Paciente não encontrado para o CPF: " + cpf)
                    .build();
        } catch (QueryException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao " + ex.getTipoOperacao() + " o paciente: " + ex.getMessage())
                    .build();
        } catch (CpfAssociadoException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }

        return Response.status(Response.Status.NO_CONTENT).entity("Removido com Sucesso").build();

    }

}
