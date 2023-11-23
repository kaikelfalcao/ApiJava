/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package kaike.project.exceptions;

/**
 *
 * @author kaike
 */
public class PacienteQueryException extends Exception {
    
    private String tipoOperacao;

    public PacienteQueryException(String tipoOperacao, String mensagem) {
        super(mensagem);
        this.tipoOperacao = tipoOperacao;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }
}