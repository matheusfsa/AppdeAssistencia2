package util;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

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

import classes.Casa;
import classes.Pessoa;

public class InsereCasa extends AsyncTask<String, String, String> {
    public static  final int CONNECTION_TIMEOUT = 10000;
    public static  final int READ_TIMEOUT = 15000;
    public static  final String URL_GET = "https://santosalmeidamatheus.000webhostapp.com/wsGetData.php";
    public static  final String URL_PUT = "https://santosalmeidamatheus.000webhostapp.com/IncluirCasa.php";
    public static  final String LOG_TAG = "appdeassistencia";
    ProgressDialog progressDialog;

    HttpURLConnection conn;
    URL url = null;
    Uri.Builder builder;

    Context context;


    public InsereCasa(Casa obj, Context context){

        this.builder = new Uri.Builder();

        this.context = context;

        builder.appendQueryParameter("app", "Assistencia");
        builder.appendQueryParameter("morador", obj.getMorador().getCpf());
        builder.appendQueryParameter("cep", obj.getCep());
        builder.appendQueryParameter("bairro", obj.getBairro());
        builder.appendQueryParameter("rua", obj.getRua());
        builder.appendQueryParameter("numero", obj.getNumero());
        builder.appendQueryParameter("complemento", obj.getComplemento());
        builder.appendQueryParameter("comentario", obj.getComentario());
        builder.appendQueryParameter("cidade", obj.getCidade());
        builder.appendQueryParameter("estado", obj.getEstado());

    }

    @Override
    protected void onPreExecute() {
        Log.i("WebService", "IncluirAsyncTask()");

        progressDialog = new ProgressDialog(context);

        progressDialog.setMessage("Incluindo, por favor espere...");
        progressDialog.setCancelable(false);
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
