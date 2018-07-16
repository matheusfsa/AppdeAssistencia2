package arquitetossemcracha.example.com.appdeassistencia2;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msa.appdeassistencia.R;

import java.util.ArrayList;

public class CidadeEstadoActivity extends AppCompatActivity {
    public static final String NOME_PREFERENCE = "INFORMACOES_LOGIN_AUTOMATICO";
    private AlertDialog alerta;
    private TextView cidade;
    private TextView estado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cidade_estado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.ico_pequeno56);
        getSupportActionBar().setTitle(R.string.app_name);
        cidade = findViewById(R.id.cidade);
        estado = findViewById(R.id.estado);
        Button btnestado = findViewById(R.id.button4);
        Button btncidade = findViewById(R.id.button5);
        Button btnavancar = findViewById(R.id.button6);
        btnestado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exemplo_lista_single(0);
            }
        });
        btncidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exemplo_lista_single(1);
            }
        });
        btnavancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent avancar = new Intent(CidadeEstadoActivity.this,Info.class);
                SharedPreferences.Editor editor = getSharedPreferences(NOME_PREFERENCE, MODE_PRIVATE).edit();
                editor.putString("estado", estado.getText().toString());
                editor.putString("cidade", cidade.getText().toString());
                editor.commit();
                startActivity(avancar);
            }
        });
    }
    private void exemplo_lista_single(int op) {
        //Lista de itens
        final int coe = op;
        final ArrayList<String> itens = new ArrayList<String>();
        if(coe == 0){
            itens.add("Sergipe");
        }else{
            if(estado.getText().toString().equals("Sergipe")){
                itens.add("Aracaju");
            }
        }


        //adapter utilizando um layout customizado (TextView)
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itens);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(coe == 0){
            builder.setTitle("Estado");
        }else{
            builder.setTitle("Cidade");
            }
        //define o diálogo como uma lista, passa o adapter.
        builder.setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                if(coe == 0){
                    estado.setText(itens.get(arg1));
                }else{
                    cidade.setText(itens.get(arg1));
                }
                alerta.dismiss();
            }
        });

        alerta = builder.create();
        alerta.show();
    }


}
