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
import android.widget.RadioGroup;
import android.widget.Toast;

import classes.Pessoa;

public class CadastroEtapa3 extends AppCompatActivity {
    int id;
    Pessoa p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_etapa3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.ico_pequeno56);
        getSupportActionBar().setTitle(R.string.app_name);
        p = (Pessoa) getIntent().getSerializableExtra("pessoa");
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup2);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tipo1:
                        id = 1;
                        break;
                    case R.id.tipo2:
                        id = 2;
                        break;
                    case R.id.tipo3:
                        id = 3;
                        break;
                    case R.id.tipo4:
                        id = 4;
                        break;
                }
            }
        });
        System.out.println("3:" + p);
        id = rg.getCheckedRadioButtonId();
        Button avancar = (Button)findViewById(R.id.avancar_tipo);
        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(id != -1) {
                    Intent cadastro2 = new Intent(CadastroEtapa3.this, CadastroEtapa4.class);
                    cadastro2.putExtra("reforma",id);
                    cadastro2.putExtra("pessoa",p);
                    startActivity(cadastro2);
                }else{
                    Toast.makeText(CadastroEtapa3.this,"Escolha uma opção.",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
