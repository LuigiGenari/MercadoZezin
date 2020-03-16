package com.senac.br.mercadozezin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.senac.br.mercadozezin.dao.ProdutoDAO;
import com.senac.br.mercadozezin.model.Produto;

public class CadastroProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);
    }

    public void voltarPrimeira(View view){
        //Leia as informações da tela (nome, preço e quantidade)
        // e criem um objeto do tipo pessoa

        EditText nome = findViewById(R.id.nomeEditText);
        EditText preco = findViewById(R.id.precoEditText);
        EditText quantidade = findViewById(R.id.quantidadeEditText);

        if(quantidade == null || quantidade.getText() == null ||
        quantidade.getText().toString().equals("null") ||
        quantidade.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor informe a quantidade",
                    Toast.LENGTH_LONG).show();
        } else {
            Produto produto = new Produto(nome.getText().toString(),
                    Integer.parseInt(quantidade.getText().toString()),
                    preco.getText().toString());

            ProdutoDAO dao = new ProdutoDAO(this);
            dao.salvar(produto);

            Intent voltar = new Intent(this, MainActivity.class);
            startActivity(voltar);
        }
    }
}
