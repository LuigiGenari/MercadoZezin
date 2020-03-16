package com.senac.br.mercadozezin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.senac.br.mercadozezin.dao.ProdutoDAO;
import com.senac.br.mercadozezin.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "mercado.senac.br.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ProdutoDAO dao = new ProdutoDAO(this);
        List<Produto> produtos = dao.listar();

        ListView listView = findViewById(R.id.listViewProdutos);

        ListAdapter listAdapter =
                new ArrayAdapter<Produto>(this, android.R.layout.simple_list_item_1, produtos);

        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent cad = new Intent(MainActivity.this,
                        CadastroProdutoActivity.class);
                startActivity(cad);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Produto prod = (Produto) parent.getItemAtPosition(position);

                ProdutoDAO dao = new ProdutoDAO(MainActivity.this);
                dao.excluir(prod);

                Toast.makeText(MainActivity.this, "Item excluído com sucesso",
                        Toast.LENGTH_LONG).show();

                Intent cad = new Intent(MainActivity.this,
                        MainActivity.class);
                startActivity(cad);
                return true;
            }
        });
    }

    public void cadastrarProduto(View view){
        Intent cad = new Intent(this, CadastroProdutoActivity.class);
        startActivity(cad);
    }
}
