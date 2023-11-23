package kaike.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kaike.project.exceptions.PacienteNaoEncontradoException;
import kaike.project.exceptions.PacienteQueryException;
import kaike.project.model.DatabaseConnection;
import kaike.project.model.Paciente;

/**
 *
 * @author kaike
 */
public class PacienteDAO {

    private final Connection connection = (Connection) DatabaseConnection.getConnection();

    public Paciente obterPacientePorCPF(String cpf) throws PacienteNaoEncontradoException {
        Paciente paciente = null;
        String sql = "SELECT * FROM paciente WHERE cpf = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cpf);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    paciente = new Paciente(
                            resultSet.getString("cpf"),
                            resultSet.getString("nome"),
                            resultSet.getDate("dtnascimento"),
                            resultSet.getString("endereco"),
                            resultSet.getString("email"),
                            resultSet.getString("planosaude"),
                            resultSet.getString("numcarteirinhaplanosaude")
                    );
                }
            }

            if (paciente == null) {
                throw new PacienteNaoEncontradoException("Paciente não encontrado para o CPF: " + cpf);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return paciente;
    }

    public List<Paciente> listarTodos() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT cpf, nome, email FROM paciente"; // Modified SQL statement

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Paciente paciente = new Paciente(
                            resultSet.getString("cpf"),
                            resultSet.getString("nome"),
                            resultSet.getString("email")
                    );
                    pacientes.add(paciente);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pacientes;
    }

    public void salvar(Paciente paciente) throws PacienteQueryException {

        String sql = "INSERT INTO paciente (cpf, nome, dtnascimento, endereco, email, planosaude, numcarteirinhaplanosaude) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, paciente.getCpf());
            preparedStatement.setString(2, paciente.getNome());
            preparedStatement.setDate(3, new java.sql.Date(paciente.getDtNascimento().getTime()));
            preparedStatement.setString(4, paciente.getEndereco());
            preparedStatement.setString(5, paciente.getEmail());
            preparedStatement.setString(6, paciente.getPlanoSaude());
            preparedStatement.setString(7, paciente.getNumCarteirinhaPlanoSaude());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new PacienteQueryException("salvar", ex.getMessage());
        }
    }

    public void atualizar(Paciente paciente, String cpfOriginal) throws PacienteQueryException {

        String sql = "UPDATE paciente SET nome = ?, dtnascimento = ?, endereco = ?, email = ?, planosaude = ?, numcarteirinhaplanosaude = ? WHERE cpf = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, paciente.getNome());
            preparedStatement.setDate(2, new java.sql.Date(paciente.getDtNascimento().getTime()));
            preparedStatement.setString(3, paciente.getEndereco());
            preparedStatement.setString(4, paciente.getEmail());
            preparedStatement.setString(5, paciente.getPlanoSaude());
            preparedStatement.setString(6, paciente.getNumCarteirinhaPlanoSaude());
            preparedStatement.setString(7, cpfOriginal);
            
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new PacienteQueryException("atualizar", ex.getMessage());
        }
    }

    public void remover(String cpf) throws PacienteQueryException {

        String sql = "DELETE FROM paciente WHERE cpf = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cpf);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new PacienteQueryException("salvar", ex.getMessage());
        }

    }

    public boolean podeAlterarCpf(String cpf) {

        boolean podeAlterar = true;

        String sql = "SELECT count(*) FROM atendimento WHERE pacientecpf = ?";

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

        String sql = "SELECT count(*) FROM paciente WHERE cpf = ?";

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
