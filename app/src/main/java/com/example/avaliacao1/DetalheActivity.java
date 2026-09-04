package com.example.avaliacao1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalheActivity extends AppCompatActivity {

    public static final String EXTRA_TITULO = "extra_titulo";
    public static final String EXTRA_DESCRICAO = "extra_descricao";
    public static final String EXTRA_IMAGEM = "extra_imagem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        String titulo = getIntent().getStringExtra(EXTRA_TITULO);
        String descricao = getIntent().getStringExtra(EXTRA_DESCRICAO);
        int imagem = getIntent().getIntExtra(EXTRA_IMAGEM, 0);

        TextView txtTitulo = findViewById(R.id.txtDetalheTitulo);
        TextView txtDescricao = findViewById(R.id.txtDetalheDescricao);
        ImageView imgDetalhe = findViewById(R.id.imgDetalhe);
        Button btnOuvirSom = findViewById(R.id.btnOuvirSom);

        txtTitulo.setText(titulo);
        txtDescricao.setText(descricao);

        if (imagem != 0) {
            imgDetalhe.setImageResource(imagem);
        }

        btnOuvirSom.setOnClickListener(v -> {
            // Aqui vamos colocar o áudio do bioma
        });
    }
}