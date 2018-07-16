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
import android.widget.TextView;

import classes.NoticiaObj;

public class Noticia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.ico_pequeno56);
        NoticiaObj n = (NoticiaObj)getIntent().getSerializableExtra("noticia");
        TextView titulo = (TextView)findViewById(R.id.titulo_not);
        TextView data = (TextView)findViewById(R.id.data_not);
        TextView texto = (TextView)findViewById(R.id.body);
        titulo.setText(n.getNoticia());
        data.setText(n.getData());
        texto.setText(n.getTxt());
        Button voltar = (Button)findViewById(R.id.btn_vol_not);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Noticia.this, ListarNoticias.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }

}
