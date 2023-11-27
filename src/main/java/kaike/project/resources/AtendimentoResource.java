
package kaike.project.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kaike.project.controller.AtendimentoController;
import kaike.project.exceptions.NaoEncontradoException;
import kaike.project.exceptions.QueryException;
import kaike.project.model.Atendimento;
import kaike.project.model.Paciente;

/**
 *
 * @author kaike
 */
@Path("/atendimento")
public class AtendimentoResource {
    
    private AtendimentoController controller = new AtendimentoController();
    
    @GET
    @Path("/paciente/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obterAtendimentosPorPacienteCPF(@PathParam("cpf") String cpf) {
        try {
            List<Atendimento> atendimentos = controller.consultarTodosPorPaciente(cpf);
            return Response.status(Response.Status.OK)
                    .entity(atendimentos)
                    .build();
        } catch (NaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Atendimentos não encontrado para o CPF: " + cpf)
                    .build();
        }
    }
    
    @GET
@Path("/tipo/{tipo}")
@Produces(MediaType.APPLICATION_JSON)
public Response obterAtendimentosPorTipo(@PathParam("tipo") String tipo) {
    try {
        List<Atendimento> atendimentos = controller.consultarPorTipo(tipo);
        
        List<Map<String, Object>> resultado = new ArrayList<>();
        
        for (Atendimento atendimento : atendimentos) {
            Map<String, Object> detalhesAtendimento = new HashMap<>();
            
            detalhesAtendimento.put("Numero do atendimento", atendimento.getNumeroAtendimento());
            
            detalhesAtendimento.put("Cpf do Paciente", atendimento.getCpfPaciente());
            detalhesAtendimento.put("Nome do Paciente", atendimento.getNomePaciente());
            
            detalhesAtendimento.put("Descricao do Atendimento", atendimento.getDescricaoAtendimento());
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            detalhesAtendimento.put("Data de Realizacao", dateFormat.format(atendimento.getDataAtendimento()));            
            detalhesAtendimento.put("valor Cobrado", atendimento.getValorAtendimento());
           
            detalhesAtendimento.put("Cpf do Usuario", atendimento.getCpfUsuario());
            
            resultado.add(detalhesAtendimento);
        }
        
        return Response.status(Response.Status.OK)
                .entity(resultado)
                .build();
    } catch (NaoEncontradoException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity("Atendimentos não encontrado para o tipo: " + tipo)
                .build();
    }
}

    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response salvarAtendimento(Atendimento atendimento) {
        try {
            controller.salvarAtendimento(atendimento);
        } catch (QueryException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao " + ex.getTipoOperacao() + " o paciente: " + ex.getMessage())
                    .build();
        }
        return Response.status(Response.Status.CREATED).entity("Criado com Sucesso").build();
    }
    
    @DELETE
    @Path("/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removerAtendimento(@PathParam("numero") Integer numero) {
        try {
            controller.removerAtendimento(numero);
        } catch (QueryException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro ao " + ex.getTipoOperacao() + " o paciente: " + ex.getMessage())
                    .build();
        }
        return Response.status(Response.Status.NO_CONTENT).entity("Removido com Sucesso").build();

    }
    
}
