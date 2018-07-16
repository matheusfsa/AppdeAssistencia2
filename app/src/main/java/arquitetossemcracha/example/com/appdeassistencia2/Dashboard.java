package arquitetossemcracha.example.com.appdeassistencia2;
import com.example.msa.appdeassistencia.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {
    public static final String NOME_PREFERENCE = "INFORMACOES_LOGIN_AUTOMATICO";
    public boolean first;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setIcon(R.drawable.ico_pequeno56);
        Button cadastro = (Button) findViewById(R.id.cadastre);
        Button qs = (Button) findViewById(R.id.quem_somos);
        Button cf = (Button) findViewById(R.id.como_funciona);
        Button sug = (Button) findViewById(R.id.sugestoes);
        Button not = (Button) findViewById(R.id.noticias);

        SharedPreferences prefs = getSharedPreferences(NOME_PREFERENCE, MODE_PRIVATE);
        String login= prefs.getString("cpf", null);
        if (login!= null) {
                first = false;
                cadastro.setText(R.string.acompanhe);
        } else {
                first = true;
        }
        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(first) {
                    Intent cadastro = new Intent(Dashboard.this, Precadastro.class);
                    startActivity(cadastro);
                }else{
                    Intent cadastro = new Intent(Dashboard.this, AcompanhaCadastro.class);
                    startActivity(cadastro);
                }
            }
        });
        qs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent qsomos = new Intent(Dashboard.this, QuemSomos.class);
                startActivity(qsomos);
            }
        });
        cf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cfu = new Intent(Dashboard.this, ComoFunciona.class);
                startActivity(cfu);
            }
        });
        sug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sugg = new Intent(Dashboard.this, Sugestoes.class);
                startActivity(sugg);
            }
        });
        not.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sugg = new Intent(Dashboard.this, ListarNoticias.class);
                startActivity(sugg);
            }
        });
    }

}
