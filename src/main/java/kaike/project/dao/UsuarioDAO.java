
package kaike.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import kaike.project.exceptions.NaoEncontradoException;
import kaike.project.exceptions.QueryException;
import kaike.project.model.DatabaseConnection;
import kaike.project.model.Usuario;

/**
 *
 * @author kaike
 */
public class UsuarioDAO {
     private final Connection connection = (Connection) DatabaseConnection.getConnection();

    public Usuario obterUsuarioPorCPF(String cpf) throws NaoEncontradoException {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuario WHERE cpf = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cpf);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    usuario = new Usuario(
                            resultSet.getString("cpf"),
                            resultSet.getString("login"),
                            resultSet.getString("senha")
                            
                    );
                }
            }

            if (usuario == null) {
                throw new NaoEncontradoException("Usuario não encontrado para o CPF: " + cpf);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return usuario;
    }

    public void salvar(Usuario usuario) throws QueryException {

        String sql = "INSERT INTO usuario (cpf, login, senha) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getCpf());
            preparedStatement.setString(2, usuario.getLogin());
            preparedStatement.setString(3, usuario.getSenha());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new QueryException("salvar", ex.getMessage());
        }
    }

    public void atualizar(Usuario usuario, String cpfOriginal) throws QueryException {

        String sql = "UPDATE usuario SET cpf = ?, login = ? , senha = ? WHERE cpf = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getCpf());
            preparedStatement.setString(2, usuario.getLogin());
            preparedStatement.setString(3, usuario.getSenha());
            preparedStatement.setString(4, cpfOriginal);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new QueryException("atualizar", ex.getMessage());
        }
    }

    public void remover(String cpf) throws QueryException {

        String sql = "DELETE FROM usuario WHERE cpf = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cpf);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new QueryException("salvar", ex.getMessage());
        }

    }

    public boolean podeAlterarCpf(String cpf) {

        boolean podeAlterar = true;

        String sql = "SELECT count(*) FROM atendimento WHERE cpfusuario = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cpf);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int atendimentos = resultSet.getInt(1);
                    podeAlterar = atendimentos == 0;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return podeAlterar;
    }

    public boolean checarCpf(String cpf) {

        boolean podeAlterar = true;

        String sql = "SELECT count(*) FROM usuario WHERE cpf = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cpf);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int atendimentos = resultSet.getInt(1);
                    podeAlterar = atendimentos > 0;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return podeAlterar;
    }
}
