package classes;

import java.io.Serializable;

public class Pessoa implements Serializable{
    private String nome;
    private String sobrenome;
    private String cpf;
    private String numero;
    private String data_nascimento;
    private String renda;

    public String getRenda() {
        return renda;
    }

    public void setRenda(String renda) {
        this.renda = renda;
    }

    public Pessoa(String nome, String sobrenome, String cpf, String numero, String data_nascimento, String renda){
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.numero = numero;
        this.data_nascimento = data_nascimento;
        this.renda = renda;
    }
    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNumero() {
        return numero;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    @Override
    public String toString() {
        String res = nome + ", " + cpf + ", " + numero + ", " + data_nascimento + ", " + renda;
        return res;
    }
}
