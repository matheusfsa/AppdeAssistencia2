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

public class Info2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info2);

        Button b = (Button) findViewById(R.id.avancar_info2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dashboard = new Intent(Info2.this, Dashboard.class);
                dashboard.putExtra("cidade",getIntent().getStringExtra("cidade"));
                dashboard.putExtra("estado",getIntent().getStringExtra("estado"));
                startActivity(dashboard);

            }
        });
    }

}
