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
import kaike.project.exceptions.PacienteNaoEncontradoException;
import kaike.project.exceptions.PacienteQueryException;
import kaike.project.model.Paciente;

/**
 *
 * @author kaike
 */
@Path("/pacientes")
public class PacienteResource {

    private PacienteController pacienteController = new PacienteController();

    @GET
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterPacientePorCPF(@PathParam("cpf") String cpf) {
        try {
            Paciente paciente = pacienteController.obterPacientePorCPF(cpf);
            return Response.status(Response.Status.OK)
                    .entity(paciente)
                    .build();
        } catch (PacienteNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Paciente não encontrado para o CPF: " + cpf)
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, String>> listarTodosOsPacientes() {
        List<Paciente> pacientes = pacienteController.listarTodosOsPacientes();
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
            pacienteController.cadastrarPaciente(paciente);
        } catch (PacienteQueryException ex) {
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
            pacienteController.atualizarPaciente(paciente, cpf);
        } catch (PacienteNaoEncontradoException ex) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Paciente não encontrado para o CPF: " + cpf)
                    .build();
        } catch (PacienteQueryException ex) {
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
            pacienteController.removerPaciente(cpf);
        } catch (PacienteNaoEncontradoException ex) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Paciente não encontrado para o CPF: " + cpf)
                    .build();
        } catch (PacienteQueryException ex) {
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
