package br.com.faculdadedelta.exercicio3richard;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final int RETURN_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void enviar (View view){


        EditText etProduto = findViewById(R.id.tvProduto);
        EditText etData = findViewById(R.id.tvData);
        EditText etCliente = findViewById(R.id.tvCliente);
        EditText etValor = findViewById(R.id.tvValor);

        Intent intent = new Intent(getBaseContext(), ValidacaoActivity.class);

        String produtoP = etProduto.getText().toString();
        String valorP = etValor.getText().toString();
        String clientP = etCliente.getText().toString();
        String dataP = etData.getText().toString();

        if(produtoP.isEmpty()){
            Toast.makeText(getBaseContext(), "O campo produto é obrigatório!",
                    Toast.LENGTH_LONG).show();
        }else if (valorP.isEmpty()) {
            Toast.makeText(getBaseContext(),
                    "O campo valor é obrigatório!",
                    Toast.LENGTH_LONG).show();
        }else if (clientP.isEmpty()) {
            Toast.makeText(getBaseContext(),
                    "O campo cliente é obrigatório!",
                    Toast.LENGTH_LONG).show();
        }else if (dataP.isEmpty()) {
            Toast.makeText(getBaseContext(),
                    "O campo data da venda é obrigatório!",
                    Toast.LENGTH_LONG).show();
        }else {
            // Passa o valor para a intent
            intent.putExtra("produtoParam", produtoP);
            intent.putExtra("valorParam", valorP);
            intent.putExtra("clienteParam", clientP);
            intent.putExtra("dataParam",dataP);

            startActivityForResult(intent, RETURN_CODE);
        }
    }

    public void limparCampos(View view){

        EditText etProduto = findViewById(R.id.tvProduto);
        EditText etData = findViewById(R.id.tvData);
        EditText etCliente = findViewById(R.id.tvCliente);
        EditText etValor = findViewById(R.id.tvValor);

        etProduto.setText("");
        etValor.setText("");
        etCliente.setText("");
        etData.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if(requestCode == RETURN_CODE){
            if(resultCode ==
                    ValidacaoActivity.RETURN_CODE_SUCESS){
                String resp = data.getStringExtra("resposta");
                Toast.makeText(getBaseContext(), resp, Toast.LENGTH_LONG).show();
            }
        }
    };
}
