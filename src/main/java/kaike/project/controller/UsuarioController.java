package kaike.project.controller;

import kaike.project.dao.UsuarioDAO;
import kaike.project.exceptions.CpfAssociadoException;
import kaike.project.exceptions.CpfDuplicadoException;
import kaike.project.exceptions.NaoEncontradoException;
import kaike.project.exceptions.QueryException;
import kaike.project.model.Usuario;

/**
 *
 * @author kaike
 */
public class UsuarioController {
    
    private final UsuarioDAO dao = new UsuarioDAO();
    
    public Usuario obter(String cpf) throws NaoEncontradoException {
        return dao.obterUsuarioPorCPF(cpf);
    }
    
    public void cadastrar(Usuario usuario) throws QueryException, CpfDuplicadoException {
        this.checarCpf(usuario.getCpf());

        dao.salvar(usuario);
    }

    public void atualizar(Usuario usuario, String cpf) throws NaoEncontradoException, QueryException, CpfAssociadoException {
        this.verificarCpfAssociado(usuario.getCpf());

        this.usuarioExiste(cpf);

        dao.atualizar(usuario, cpf);
    }

    public void remover(String cpf) throws NaoEncontradoException, QueryException, CpfAssociadoException {
        this.verificarCpfAssociado(cpf);

        this.usuarioExiste(cpf);

        dao.remover(cpf);
    }

    private void verificarCpfAssociado(String cpf) throws CpfAssociadoException {
        if (!dao.podeAlterarCpf(cpf)) {
            throw new CpfAssociadoException("O CPF informado está associado a um atendimento.");
        }
    }

    private void checarCpf(String cpf) throws CpfDuplicadoException {
        if (!dao.podeAlterarCpf(cpf)) {
            throw new CpfDuplicadoException("Usuario já cadastrado com esse CPF.");
        }
    }

    private void usuarioExiste(String cpf) throws NaoEncontradoException {
        dao.obterUsuarioPorCPF(cpf);
    }

    
}
