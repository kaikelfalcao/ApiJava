
package kaike.project.controller;

import java.util.List;
import kaike.project.dao.AtendimentoDAO;
import kaike.project.exceptions.NaoEncontradoException;
import kaike.project.exceptions.QueryException;
import kaike.project.model.Atendimento;

/**
 *
 * @author kaike
 */
public class AtendimentoController {
    private final AtendimentoDAO dao = new AtendimentoDAO();
    
    public List<Atendimento> consultarTodosPorPaciente(String cpfPaciente) throws NaoEncontradoException {
        return dao.consultarTodosPorPaciente(cpfPaciente);
    }
    
    public List<Atendimento> consultarPorTipo(String tipoAtendimento) throws NaoEncontradoException {
        return dao.consultarPorTipo(tipoAtendimento);
    }
    
    public void salvarAtendimento(Atendimento atendimento) throws QueryException {
        dao.salvar(atendimento);
    }

    public void removerAtendimento(int numeroAtendimento) throws QueryException {
        dao.remover(numeroAtendimento);
    }
}
