
package kaike.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;

/**
 *
 * @author kaike
 */
public class Atendimento {
    private int numeroAtendimento;
    private String cpfPaciente;
    private String nomePaciente;
    private String descricaoAtendimento;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataAtendimento;
    private String tipoAtendimento;
    private double valorAtendimento;
    private String cpfUsuario;

    public Atendimento() {
    }

    public Atendimento(int numeroAtendimento, String cpfPaciente, String nomePaciente,  String cpfUsuario, String descricaoAtendimento, Date dataAtendimento, double valorAtendimento, String tipoAtendimento) {
        this.numeroAtendimento = numeroAtendimento;
        this.cpfPaciente = cpfPaciente;
        this.nomePaciente = nomePaciente;
        this.descricaoAtendimento = descricaoAtendimento;
        this.dataAtendimento = dataAtendimento;
        this.tipoAtendimento = tipoAtendimento;
        this.valorAtendimento = valorAtendimento;
        this.cpfUsuario = cpfUsuario;
    }
    
   
    public int getNumeroAtendimento() {
        return numeroAtendimento;
    }

    public void setNumeroAtendimento(int numeroAtendimento) {
        this.numeroAtendimento = numeroAtendimento;
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getDescricaoAtendimento() {
        return descricaoAtendimento;
    }

    public void setDescricaoAtendimento(String descricaoAtendimento) {
        this.descricaoAtendimento = descricaoAtendimento;
    }

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(String tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public double getValorAtendimento() {
        return valorAtendimento;
    }

    public void setValorAtendimento(double valorAtendimento) {
        this.valorAtendimento = valorAtendimento;
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }
    
    
}
