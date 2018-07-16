package arquitetossemcracha.example.com.appdeassistencia2;
import com.example.msa.appdeassistencia.R;
import com.vicmikhailau.maskededittext.MaskedFormatter;
import com.vicmikhailau.maskededittext.MaskedWatcher;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Calendar;

import classes.Pessoa;
import util.IncluirAsyncTask;
import util.InsereCasa;
import util.VerificaConexao;

public class CadastroEtapa2 extends AppCompatActivity {

    int renda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_etapa2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.ico_pequeno56);
        getSupportActionBar().setTitle(R.string.app_name);
        renda = getIntent().getIntExtra("renda",-1);
        System.out.println("Renda2: " + renda);
        final EditText nome = (EditText)findViewById(R.id.nome_edit);
        final EditText sobrenome = (EditText)findViewById(R.id.sobrenome_edit);
        final EditText cpf = (EditText)findViewById(R.id.cpfedit);
        final EditText telefone = (EditText)findViewById(R.id.telefone_edit);
        final EditText data_nascimento = (EditText)findViewById(R.id.data_edit);
        Button avancar = (Button)findViewById(R.id.avancar_pessoa);
        final MaskedFormatter formatter = new MaskedFormatter("###.###.###.##");
        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = cpf.getText().toString();
                String unmaskedCpf = formatter.formatString(text).getUnMaskedString();
                Pessoa p = new Pessoa(nome.getText().toString(),sobrenome.getText().toString(),unmaskedCpf,telefone.getText().toString(), data_nascimento.getText().toString(),String.valueOf(renda));
                if(p.getNome().equals("") | p.getData_nascimento().equals("") | p.getNumero().equals("") | p.getCpf().equals("") | p.getSobrenome().equals("")){
                    Toast.makeText(CadastroEtapa2.this, "Preencha todos os campos",Toast.LENGTH_LONG).show();
                }else{

                    boolean con = VerificaConexao.isOnline(CadastroEtapa2.this);
                    if(con) {
                        int tam = p.getCpf().length();
                        if(tam!=11){
                            Toast.makeText(CadastroEtapa2.this, "Campo CPF incorreto!",Toast.LENGTH_LONG).show();
                        }else{
                            System.out.println(p);
                            IncluirAsyncTask is = new IncluirAsyncTask(p, CadastroEtapa2.this) ;
                            is.execute();
                            Intent info2 = new Intent(CadastroEtapa2.this, CadastroEtapa3.class);
                            info2.putExtra("pessoa",p);
                            startActivity(info2);
                        }
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(CadastroEtapa2.this);
                        //define o titulo
                        builder.setTitle("Aviso");
                        //define a mensagem
                        builder.setMessage("É necessário conexão com a internet!");
                        builder.setNeutralButton("Ok",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        });
                        builder.show();
                    }
                }


            }
        });

    }


}
