package br.com.faculdadedelta.exercicio3richard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ValidacaoActivity extends AppCompatActivity {

    private String produto;
    private String valor;
    private String cliente;
    private String data;
    Date dataConvert = new Date();
    Date dia = new Date();
    private String datad = "01/01/2014";

    public static final  int RETURN_CODE_SUCESS = 1;
    public static final int RETURN_CODE_ERROR = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);

        Intent intent = getIntent();
        if (intent != null) {

            produto = intent.getStringExtra("produtoParam");
            valor = intent.getStringExtra("valorParam");
            cliente = intent.getStringExtra("clienteParam");
            data = intent.getStringExtra("dataParam");

            TextView idProduto = findViewById(R.id.tvProduto);
            idProduto.setText("Produto: " + produto);

            TextView idValor = findViewById(R.id.tvValor);
            idValor.setText("Valor: " + valor);

            TextView idCliente = findViewById(R.id.tvCliente);
            idCliente.setText("Cliente: " + cliente);

            TextView idData = findViewById(R.id.tvData);
            idData.setText("Data Venda: " + data);
        }
    }
    public void validaCampo (View view){

        String resposta = "";
        double valorConvert = Double.parseDouble(valor);

        if(produto.length() > 10 && valorConvert > 658 && cliente.length() > 40){
            resposta = "Informações foram validadas com sucesso!";
        } else if(valorConvert <= 658){
            resposta = "O valor deve ser maior que 658";

        } else if(cliente.length() < 40){
            resposta = "O nome do cliente deve ser maior que 40 caracteres";
        }else if(produto.length() < 10){
            resposta = "Produto invalido! O numero de caracteres deve ser maior que 10";
        }

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        dataConvert = null;

        try {
            dataConvert = formato.parse(data);
            dia = formato.parse(datad);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(dataConvert);

        if(dataConvert.before(dia)){
            resposta = "A data da compra está invalida! A data deve ser depois de 01/01/2014";
        }
        Intent data = new Intent();
        data.putExtra("resposta", resposta);
        setResult(RETURN_CODE_SUCESS, data);

        finish();
    }

}
