package arquitetossemcracha.example.com.appdeassistencia2;
import com.example.msa.appdeassistencia.R;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import classes.Casa;
import classes.Pessoa;
import util.IncluirAsyncTask;
import util.InsereCasa;
import util.VerificaConexao;

public class CadastroEtapa5 extends AppCompatActivity {
    public static final String NOME_PREFERENCE = "INFORMACOES_LOGIN_AUTOMATICO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_etapa5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.ico_pequeno56);
        getSupportActionBar().setTitle(R.string.app_name);
        final Pessoa p = (Pessoa)getIntent().getSerializableExtra("pessoa");
        final Casa c = (Casa)getIntent().getSerializableExtra("casa");
        final Button b  = findViewById(R.id.button3);
        final EditText comentario = findViewById(R.id.editText2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean con = VerificaConexao.isOnline(CadastroEtapa5.this);
                if(con) {
                    SharedPreferences.Editor editor = getSharedPreferences(NOME_PREFERENCE, MODE_PRIVATE).edit();
                    editor.putString("cpf", p.getCpf());
                    editor.commit();
                    c.setComentario(comentario.getText().toString());
                    InsereCasa is = new InsereCasa(c, CadastroEtapa5.this);
                    is.execute();
                    Intent info2 = new Intent(CadastroEtapa5.this, TelaFinal.class);
                    info2.putExtra("sugestao",false);
                    startActivity(info2);
                    finish();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(CadastroEtapa5.this);
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
        });
    }

}
