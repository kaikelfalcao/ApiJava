package kaike.project.controller;

import java.util.List;
import kaike.project.dao.PacienteDAO;
import kaike.project.exceptions.CpfAssociadoException;
import kaike.project.exceptions.CpfDuplicadoException;
import kaike.project.exceptions.PacienteNaoEncontradoException;
import kaike.project.exceptions.PacienteQueryException;
import kaike.project.model.Paciente;


/**
 *
 * @author kaike
 */
public class PacienteController {

    private final PacienteDAO pacienteDAO = new PacienteDAO();

    public Paciente obterPacientePorCPF(String cpf) throws PacienteNaoEncontradoException {
        return pacienteDAO.obterPacientePorCPF(cpf);
    }

    public List<Paciente> listarTodosOsPacientes() {
        return pacienteDAO.listarTodos();
    }

    public void cadastrarPaciente(Paciente paciente) throws PacienteQueryException, CpfDuplicadoException {
        if (paciente.getNumCarteirinhaPlanoSaude().isEmpty()) {
            paciente.setNumCarteirinhaPlanoSaude("particular");
        }

        this.checarCpf(paciente.getCpf());

        pacienteDAO.salvar(paciente);
    }

    public void atualizarPaciente(Paciente paciente, String cpf) throws PacienteNaoEncontradoException, PacienteQueryException, CpfAssociadoException {
        this.verificarCpfAssociado(paciente.getCpf());

        this.pacienteExiste(cpf);

        pacienteDAO.atualizar(paciente, cpf);
    }

    public void removerPaciente(String cpf) throws PacienteNaoEncontradoException, PacienteQueryException, CpfAssociadoException {
        this.verificarCpfAssociado(cpf);

        this.pacienteExiste(cpf);
        
        pacienteDAO.remover(cpf);
    }

    private void verificarCpfAssociado(String cpf) throws CpfAssociadoException {
        if (! pacienteDAO.podeAlterarCpf(cpf)) {
            throw new CpfAssociadoException("O CPF informado está associado a um atendimento.");
        }
    }
    
    private void checarCpf(String cpf) throws CpfDuplicadoException {
        if (! pacienteDAO.podeAlterarCpf(cpf)) {
            throw new CpfDuplicadoException("Paciente já cadastrado com esse CPF..");
        }
    }
    
    private void pacienteExiste(String cpf) throws PacienteNaoEncontradoException{
        pacienteDAO.obterPacientePorCPF(cpf);
    }
    
    

}
