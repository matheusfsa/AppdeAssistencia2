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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Precadastro extends AppCompatActivity {
    int id;
    EditText n_ins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precadastro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setIcon(R.drawable.ico_pequeno56);
        id = 1;
        n_ins = findViewById(R.id.editText);
        CheckBox cb = findViewById(R.id.is);
        n_ins.setVisibility(View.VISIBLE);
        Button avancar = (Button)findViewById(R.id.avancar_is);
        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(id != -1) {
                    if(id == 1) {
                        Intent cadastro2 = new Intent(Precadastro.this, CadastroEtapa2.class);
                        cadastro2.putExtra("is", n_ins.getText().toString());
                        startActivity(cadastro2);
                    }else{
                        Intent cadastro2 = new Intent(Precadastro.this, CadastroEtapa1.class);
                        startActivity(cadastro2);
                    }
                }else{
                    Toast.makeText(Precadastro.this,"Escolha uma opção.",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.is:
                if (checked) {
                    id = 0;
                    n_ins.setVisibility(View.INVISIBLE);
                    Toast.makeText(this, "Clicou", Toast.LENGTH_SHORT);
                }else {
                    id = 1;
                    n_ins.setVisibility(View.VISIBLE);
                    Toast.makeText(this, "Desclicou", Toast.LENGTH_SHORT);
                }
                break;
        }
    }

}
