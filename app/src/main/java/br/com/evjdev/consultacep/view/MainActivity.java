package br.com.evjdev.consultacep.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import br.com.evjdev.consultacep.R;
import br.com.evjdev.consultacep.model.Endereco;
import br.com.evjdev.consultacep.service.HttpService;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editCep = findViewById(R.id.editCep);
//        final String cep = editCep.getText().toString();
        final TextView tvResultado = findViewById(R.id.tvResultado);

        Button btBuscar = findViewById(R.id.btBuscar);

        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editCep.getText().toString().isEmpty() && editCep.getText().toString().length() > 0 && editCep.getText().toString().length() == 8) {
                    Endereco retorno;
                    try {
                        retorno = new HttpService(editCep.getText().toString()).execute().get();
                        tvResultado.setText(retorno.toString());
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Cep Inválido", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


}
