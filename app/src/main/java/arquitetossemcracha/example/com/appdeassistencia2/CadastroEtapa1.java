package arquitetossemcracha.example.com.appdeassistencia2;
import com.example.msa.appdeassistencia.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CadastroEtapa1 extends AppCompatActivity {
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_etapa1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setIcon(R.drawable.ico_pequeno56);

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup2);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                System.out.println("------------------------|"+checkedId);
                switch (checkedId) {
                    case R.id.ate1:
                        id = 1;
                        break;
                    case R.id.ate2:
                        id = 2;
                        break;
                    case R.id.ate3:
                        id = 3;
                        break;
                }
            }
        });
        id = rg.getCheckedRadioButtonId();
        Button avancar = (Button)findViewById(R.id.avancar_renda);
        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               
                if(id != -1) {
                    Intent cadastro2 = new Intent(CadastroEtapa1.this, CadastroEtapa2.class);
                    cadastro2.putExtra("renda",id);
                    startActivity(cadastro2);
                }else{
                    Toast.makeText(CadastroEtapa1.this,"Escolha uma opção.",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
