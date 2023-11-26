package kaike.project.controller;

import java.util.List;
import kaike.project.dao.PacienteDAO;
import kaike.project.exceptions.CpfAssociadoException;
import kaike.project.exceptions.CpfDuplicadoException;
import kaike.project.exceptions.NaoEncontradoException;
import kaike.project.exceptions.QueryException;
import kaike.project.model.Paciente;

/**
 *
 * @author kaike
 */
public class PacienteController {

    private final PacienteDAO dao = new PacienteDAO();

    public Paciente obterPacientePorCPF(String cpf) throws NaoEncontradoException {
        return dao.obterPacientePorCPF(cpf);
    }

    public List<Paciente> listarTodosOsPacientes() {
        return dao.listarTodos();
    }

    public void cadastrarPaciente(Paciente paciente) throws QueryException, CpfDuplicadoException {
        if (paciente.getNumCarteirinhaPlanoSaude().isEmpty()) {
            paciente.setNumCarteirinhaPlanoSaude("particular");
        }

        this.checarCpf(paciente.getCpf());

        dao.salvar(paciente);
    }

    public void atualizarPaciente(Paciente paciente, String cpf) throws NaoEncontradoException, QueryException, CpfAssociadoException {
        this.verificarCpfAssociado(paciente.getCpf());

        this.pacienteExiste(cpf);

        dao.atualizar(paciente, cpf);
    }

    public void removerPaciente(String cpf) throws NaoEncontradoException, QueryException, CpfAssociadoException {
        this.verificarCpfAssociado(cpf);

        this.pacienteExiste(cpf);

        dao.remover(cpf);
    }

    private void verificarCpfAssociado(String cpf) throws CpfAssociadoException {
        if (!dao.podeAlterarCpf(cpf)) {
            throw new CpfAssociadoException("O CPF informado está associado a um atendimento.");
        }
    }

    private void checarCpf(String cpf) throws CpfDuplicadoException {
        if (!dao.podeAlterarCpf(cpf)) {
            throw new CpfDuplicadoException("Paciente já cadastrado com esse CPF..");
        }
    }

    private void pacienteExiste(String cpf) throws NaoEncontradoException {
        dao.obterPacientePorCPF(cpf);
    }

}
