package classes;

import java.io.Serializable;

public class Casa implements Serializable{
    private Pessoa morador;
    private String bairro;
    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String tipo_de_reforma;
    private String comentario;
    private String cidade;
    private String estado;

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Casa(Pessoa morador, String bairro, String cep, String rua, String numero, String complemento, String tipo_de_reforma, String comentario) {
        this.morador = morador;
        this.bairro = bairro;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;

        this.tipo_de_reforma = tipo_de_reforma;
        this.comentario = comentario;
    }

    public Pessoa getMorador() {
        return morador;
    }

    public void setMorador(Pessoa morador) {
        this.morador = morador;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTipo_de_reforma() {
        return tipo_de_reforma;
    }

    public void setTipo_de_reforma(String tipo_de_reforma) {
        this.tipo_de_reforma = tipo_de_reforma;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
