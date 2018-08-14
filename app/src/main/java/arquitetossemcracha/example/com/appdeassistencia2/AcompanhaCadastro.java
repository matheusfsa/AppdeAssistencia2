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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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
import java.util.ArrayList;

import classes.CadastroObj;
import classes.NoticiaObj;

public class AcompanhaCadastro extends AppCompatActivity {
    ArrayList<CadastroObj> lista;
    ListView list;
    public static final String NOME_PREFERENCE = "INFORMACOES_LOGIN_AUTOMATICO";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acompanha_cadastro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        list = (ListView) findViewById(R.id.listview1);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setIcon(R.drawable.ico_pequeno56);
        lista = new ArrayList<>();
        SharedPreferences prefs = getSharedPreferences(NOME_PREFERENCE, MODE_PRIVATE);
        String login= prefs.getString("cpf", null);
        AcompanhaCadastro.Sincronizar s = new AcompanhaCadastro.Sincronizar(login,AcompanhaCadastro.this);
        s.execute();
        final ArrayAdapter<CadastroObj> adapter = new ArrayAdapter<CadastroObj>(AcompanhaCadastro.this, android.R.layout.simple_list_item_1, lista);
        list.setAdapter(adapter);
        Button voltar = (Button)findViewById(R.id.btn_voltar_acom);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcompanhaCadastro.this, Dashboard.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }
    private class Sincronizar extends AsyncTask<String,String,String > {
        public static  final int CONNECTION_TIMEOUT = 10000;
        public static  final int READ_TIMEOUT = 15000;
        public static  final String URL_GET = "https://santosalmeidamatheus.000webhostapp.com/getCadastro.php";
        public static  final String LOG_TAG = "appdeassistencia";
        ProgressDialog progressDialog;

        HttpURLConnection conn;
        URL url = null;
        Uri.Builder builder;
        Context context;
        public Sincronizar(String cpf,Context context){
            this.builder = new Uri.Builder();

            this.context = context;

            builder.appendQueryParameter("app", "Assistencia");
            builder.appendQueryParameter("cpf", cpf);

        }
        @Override
        protected void onPreExecute() {
            Log.i("WebService", "IncluirAsyncTask()");

            progressDialog = new ProgressDialog(context);

            progressDialog.setMessage("Carregando...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected String doInBackground(String... strings) {
            try {

                url = new URL(URL_GET);

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


                String query =  builder.build().getEncodedQuery();
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();
            }catch (IOException erro) {

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
                    System.out.println("Linha " +line);
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
            try{
                JSONArray jsonArray = new JSONArray(result);

                if(jsonArray.length()!=0){
                    final ArrayAdapter<CadastroObj> adapter = new ArrayAdapter<CadastroObj>(context, android.R.layout.simple_list_item_1, lista);
                    list.setAdapter(adapter);
                    for (int i = 0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        lista.add(new CadastroObj(jsonObject.getString("cpf"),jsonObject.getString("data"),jsonObject.getString("descricao")));
                        adapter.notifyDataSetChanged();
                        System.out.println(lista.size());
                    }
                }
            }catch (Exception e){

            }finally {

            }
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

        }
    }

}
