package arquitetossemcracha.example.com.appdeassistencia2;
import com.example.msa.appdeassistencia.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import classes.CadastroObj;
import util.IncluirSugestao;

public class Sugestoes extends AppCompatActivity {
    public static final String NOME_PREFERENCE = "INFORMACOES_LOGIN_AUTOMATICO";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sugestoes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        final EditText ed = findViewById(R.id.editTextSugestao);
        Button voltar = (Button)findViewById(R.id.button_enviar2);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences(NOME_PREFERENCE, MODE_PRIVATE);
                String login= prefs.getString("cpf", null);
                if(login == null){
                    login = "-1";
                }
                Sincronizar is = new Sincronizar(login,ed.getText().toString(),Sugestoes.this);
                is.execute();
                Intent info2 = new Intent(Sugestoes.this, TelaFinal.class);
                info2.putExtra("sugestao",true);
                startActivity(info2);
                finish();
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                Intent intent = new Intent(Sugestoes.this, Dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:break;
        }
        return true;
    }
    private class Sincronizar extends AsyncTask<String,String,String > {
        public static final int CONNECTION_TIMEOUT = 10000;
        public static final int READ_TIMEOUT = 15000;
        public static final String URL_GET = "https://santosalmeidamatheus.000webhostapp.com/wsGetData.php";
        public static final String URL_PUT = "https://santosalmeidamatheus.000webhostapp.com/incluirSugestao.php";
        public static final String LOG_TAG = "appdeassistencia";
        ProgressDialog progressDialog;

        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;

        Context context;


        public Sincronizar(String cpf, String sugestao, Context context) {
            this.builder = new Uri.Builder();

            this.context = context;

            builder.appendQueryParameter("app", "Assistencia");
            builder.appendQueryParameter("cpf", cpf);
            builder.appendQueryParameter("sugestao", sugestao);
            //builder.appendQueryParameter("idade", "10");
        }

        @Override
        protected void onPreExecute() {
            Log.i("WebService", "Sincronizar()");

            progressDialog = new ProgressDialog(context);

            progressDialog.setMessage("Incluindo, por favor espere...");
            progressDialog.show();
            progressDialog.dismiss();
        }

        @Override
        protected String doInBackground(String... params) {

            try {

                url = new URL(URL_PUT);

            } catch (MalformedURLException e) {

                Log.e("WebService", "MalformedURLException - " + e.getMessage());

            } catch (Exception e) {

                Log.e("WebService", "Exception - " + e.getMessage());

            }

            try {

                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("charset", "utf-8");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                conn.connect();

                String query = builder.build().getEncodedQuery();
                System.out.println(query);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException erro) {

                Log.e("WebService", "IOException - " + erro.getMessage());

            }

            try {

                int response_code = conn.getResponseCode();

                if (response_code == HttpURLConnection.HTTP_OK) {

                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    return (result.toString());

                } else {
                    return ("Erro de conexão");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }

        }

        @Override
        protected void onPostExecute(String result) {

            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

        }
    }

}
