package classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastroObj {
    private String cpf;
    private String data;
    private String descricao;

    public CadastroObj(String cpf, String data, String descricao) {
        this.cpf = cpf;
        this.data = data;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formato.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formato.applyPattern("dd/MM/yyyy");
        this.data = formato.format(date);
        this.descricao = descricao;

    }

    @Override
    public String toString() {
        return data + ": " + descricao;
    }
}
