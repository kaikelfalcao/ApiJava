/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kaike.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author kaike
 */
public class Paciente {

    private String cpf;
    private String nome;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dtNascimento;
    private String endereco;
    private String email;
    private String planoSaude;
    private String numCarteirinhaPlanoSaude;

    public Paciente() {
    }
    
    public Paciente(String cpf, String nome, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    public Paciente(String cpf, String nome, Date dtNascimento, String endereco, String email, String planoSaude, String numCarteirinhaPlanoSaude) {
        this.cpf = cpf;
        this.nome = nome;
        this.dtNascimento = dtNascimento;
        this.endereco = endereco;
        this.email = email;
        this.planoSaude = planoSaude;
        this.numCarteirinhaPlanoSaude = numCarteirinhaPlanoSaude;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereço) {
        this.endereco = endereço;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlanoSaude() {
        return planoSaude;
    }

    public void setPlanoSaude(String planoSaude) {
        this.planoSaude = planoSaude;
    }

    public String getNumCarteirinhaPlanoSaude() {
        return numCarteirinhaPlanoSaude;
    }

    public void setNumCarteirinhaPlanoSaude(String numCarteirinhaPlanoSaude) {
        this.numCarteirinhaPlanoSaude = numCarteirinhaPlanoSaude;
    }

}
