/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kaike.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import kaike.project.exceptions.NaoEncontradoException;
import kaike.project.exceptions.QueryException;
import kaike.project.model.Atendimento;
import kaike.project.model.DatabaseConnection;

/**
 *
 * @author kaike
 */
public class AtendimentoDAO {

    private final Connection connection = DatabaseConnection.getConnection();

    public List<Atendimento> consultarTodosPorPaciente(String cpfPaciente) throws NaoEncontradoException {
        List<Atendimento> atendimentos = new ArrayList<>();
        String sql = "SELECT * FROM atendimento WHERE cpfpaciente = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, cpfPaciente);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Atendimento atendimento = new Atendimento(
                            resultSet.getInt("numeroatendimento"),
                            resultSet.getString("cpfpaciente"),
                            resultSet.getString("nomepaciente"),
                            resultSet.getString("cpfusuario"),
                            resultSet.getString("descricaoatendimento"),
                            resultSet.getDate("dataatendimento"),
                            resultSet.getDouble("valorcobrado"),
                            resultSet.getString("tipoatendimento")
                    );
                    atendimentos.add(atendimento);
                }
            }

            if (atendimentos.isEmpty()) {
                throw new NaoEncontradoException("Nenhum atendimento encontrado para o CPF do paciente: " + cpfPaciente);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return atendimentos;
    }
    
    public List<Atendimento> consultarPorTipo(String tipoAtendimento) throws NaoEncontradoException {
        List<Atendimento> atendimentos = new ArrayList<>();
        String sql = "SELECT * FROM atendimento WHERE tipoatendimento = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, tipoAtendimento);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Atendimento atendimento = new Atendimento(
                            resultSet.getInt("numeroatendimento"),
                            resultSet.getString("cpfpaciente"),
                            resultSet.getString("nomepaciente"),
                            resultSet.getString("cpfusuario"),
                            resultSet.getString("descricaoatendimento"),
                            resultSet.getDate("dataatendimento"),
                            resultSet.getDouble("valorcobrado"),
                            resultSet.getString("tipoatendimento")
                    );
                    atendimentos.add(atendimento);
                }
            }
            
            if (atendimentos.isEmpty()) {
                throw new NaoEncontradoException("Nenhum atendimento encontrado para o CPF do paciente: " + tipoAtendimento);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return atendimentos;
    }

    public void salvar(Atendimento atendimento) throws QueryException {
        String sql = "INSERT INTO atendimento (numeroatendimento, cpfpaciente, nomepaciente, cpfusuario, descricaoatendimento, dataatendimento, valorcobrado, tipoatendimento)VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, atendimento.getNumeroAtendimento());
            preparedStatement.setString(2, atendimento.getCpfPaciente());
            preparedStatement.setString(3, atendimento.getNomePaciente());
            preparedStatement.setString(4, atendimento.getCpfUsuario());
            preparedStatement.setString(5, atendimento.getDescricaoAtendimento());
            preparedStatement.setDate(6, atendimento.getDataAtendimento());
            preparedStatement.setDouble(7, atendimento.getValorAtendimento());
            preparedStatement.setString(8, atendimento.getTipoAtendimento());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new QueryException("salvar", ex.getMessage());
        }
    }

    public void remover(int numeroAtendimento) throws QueryException {
        String sql = "DELETE FROM atendimento WHERE numeroatendimento = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, numeroAtendimento);

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            throw new QueryException("remover", ex.getMessage());
        }
    }
}
