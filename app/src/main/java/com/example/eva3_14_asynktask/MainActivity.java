package com.example.eva3_14_asynktask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtVwMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtVwMostrar = findViewById(R.id.txtVwMostrar);
        MiClaseAsincrona miClaseAsincrona = new MiClaseAsincrona();
        miClaseAsincrona.execute(10,500);
        //15 --> BANNER_ASYNKTASK
        //16 --> LOAD_IMAGE_ASYNTASK
    }


    class MiClaseAsincrona extends AsyncTask<Integer,String,String>{
        @Override
        //Si puede interactuar con la UI
        protected void onPreExecute() {
            super.onPreExecute();
            txtVwMostrar.append("Iniciando la tarea asíncrona!!");
        }

        @Override
        //Equivalente a un metodo run() en un Thread
        //No puede interactuar con la UI
        protected String doInBackground(Integer... integers) {
            int limite = integers[0], time = integers[1];
            for(int i = 0; i < limite; i++){
                try {
                    Thread.sleep(time);
                    publishProgress("i = " + i +"\n");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        //Si puede interactuar con la UI
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            txtVwMostrar.append(values[0]);
        }

        @Override
        //Si puede interactuar con la UI
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtVwMostrar.append("Fin de la tarea asíncrona!!\n");
        }

    }
}
