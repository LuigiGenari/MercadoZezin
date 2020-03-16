package com.senac.br.mercadozezin.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.senac.br.mercadozezin.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;

    public ProdutoDAO(Context context) {
        this.sqLiteHelper = new SQLiteHelper(context);
    }

    public void salvar(Produto produto){
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("NOME", produto.getNome());
        values.put("PRECO", produto.getPreco());
        values.put("QUANTIDADE", produto.getQuantidade());

        sqLiteDatabase.insert("PRODUTO", null, values);

        sqLiteDatabase.close();
    }

    public List<Produto> listar(){
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();

        String sql = "SELECT * FROM PRODUTO;";

        Cursor c = sqLiteDatabase.rawQuery(sql, null);

        List<Produto> produtos = new ArrayList<>();

        while (c.moveToNext()){
            Produto produto = new Produto();
            produto.setId(c.getInt(c.getColumnIndex("ID")));
            produto.setNome(c.getString(c.getColumnIndex("NOME")));
            produto.setPreco(c.getString(c.getColumnIndex("PRECO")));
            produto.setQuantidade(c.getInt(c.getColumnIndex("QUANTIDADE")));

            produtos.add(produto);
        }

        return produtos;
    }

    public void excluir(Produto prod) {
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        sqLiteDatabase.delete("PRODUTO", "ID = ?",
                new String[] {String.valueOf(prod.getId())});
    }
}
