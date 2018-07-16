package arquitetossemcracha.example.com.appdeassistencia2;
import com.example.msa.appdeassistencia.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import classes.Casa;
import classes.Pessoa;
import util.IncluirAsyncTask;
import util.InsereCasa;

public class CadastroEtapa4 extends AppCompatActivity {
    private Pessoa p;
    private int tr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_etapa4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.ico_pequeno56);
        getSupportActionBar().setTitle(R.string.app_name);
        p = (Pessoa)getIntent().getSerializableExtra("pessoa");
        tr = getIntent().getIntExtra("reforma",-1);
        final EditText cep = (EditText)findViewById(R.id.cep_edit);
        final EditText bairro = (EditText)findViewById(R.id.bairro_edit);
        final EditText rua = (EditText)findViewById(R.id.rua_edit);
        final EditText numero = (EditText)findViewById(R.id.numero_casa_edit);
        final EditText complemento = (EditText)findViewById(R.id.complemento_edit);

        Button avancar = (Button)findViewById(R.id.avancar_casa);
        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Casa c = new Casa(p, bairro.getText().toString(), cep.getText().toString(), rua.getText().toString(), numero.getText().toString(), complemento.getText().toString(), String.valueOf(tr),"");
                if (c.getBairro().equals("") | c.getCep().equals("") | c.getRua().equals("")) {
                    Toast.makeText(CadastroEtapa4.this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
                } else {
                        Intent info2 = new Intent(CadastroEtapa4.this, CadastroEtapa5.class);
                        info2.putExtra("pessoa",p);
                        info2.putExtra("casa",c);
                        startActivity(info2);

                }
            }
        });

    }

}
