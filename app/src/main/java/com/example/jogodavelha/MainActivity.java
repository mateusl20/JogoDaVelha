package com.example.jogodavelha;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button[] tabuleiro = new Button[10];
    private String vez = "X";
    private int jogadas = 0;
    private String[] matriz = new String[10];
    private int[] corVencedor = new int[3];
    int c;
   // private Drawable corOriginal = tabuleiro[c].getBackground();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializaTabuleiro();
        onClickTabuleiro();
    }

    private void inicializaTabuleiro(){
        tabuleiro[1] = (Button) findViewById(R.id.btn1);
        tabuleiro[2] = (Button) findViewById(R.id.btn2);
        tabuleiro[3] = (Button) findViewById(R.id.btn3);
        tabuleiro[4] = (Button) findViewById(R.id.btn4);
        tabuleiro[5] = (Button) findViewById(R.id.btn5);
        tabuleiro[6] = (Button) findViewById(R.id.btn6);
        tabuleiro[7] = (Button) findViewById(R.id.btn7);
        tabuleiro[8] = (Button) findViewById(R.id.btn8);
        tabuleiro[9] = (Button) findViewById(R.id.btn9);
    }

    private void onClickTabuleiro(){
        for(int i=1; i<10;i++){
            final int finalI = i;
            tabuleiro[finalI].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    jogada(finalI);
                }
            });
            matriz[i]="";
        }
    }

    private void jogada(int i){
        if (matriz[i] == ""){
            matriz[i] = vez;
            jogadas++;
            if(vez == "X"){
                vez="O";
            }else{
                vez="X";
            }
        }
        exibirTabuleiro();
        verificaFim();
    }

    private void exibirTabuleiro(){
        for(int i=1; i<10; i++){
            tabuleiro[i].setText(matriz[i]);
        }
    }


    private void verificaFim(){
        if(matriz[1].equals(matriz[2])&& matriz[1].equals(matriz[3]) && matriz[1].toString() != ""){
            ganhou(matriz[1]);
            corVencedor[0] = 1;
            corVencedor[1] = 2;
            corVencedor[2] = 3;
            int c=4;
            return;
        }
        if(matriz[1].equals(matriz[4])&& matriz[1].equals(matriz[7]) && matriz[1].toString() != ""){
            ganhou(matriz[1]);
            corVencedor[0] = 1;
            corVencedor[1] = 4;
            corVencedor[2] = 7;
            int c=5;
            return;
        }
        if(matriz[1].equals(matriz[5])&& matriz[1].equals(matriz[9]) && matriz[1].toString() != ""){
            ganhou(matriz[1]);
            corVencedor[0] = 1;
            corVencedor[1] = 5;
            corVencedor[2] = 9;
            int c=4;
            return;
        }
        if(matriz[2].equals(matriz[5])&& matriz[2].equals(matriz[8]) && matriz[2].toString() != ""){
            ganhou(matriz[2]);
            corVencedor[0] = 2;
            corVencedor[1] = 5;
            corVencedor[2] = 8;
            int c=4;
            return;
        }
        if(matriz[3].equals(matriz[6])&& matriz[3].equals(matriz[9]) && matriz[3].toString() != ""){
            ganhou(matriz[3]);
            corVencedor[0] = 3;
            corVencedor[1] = 6;
            corVencedor[2] = 9;
            int c=4;
            return;
        }
        if(matriz[3].equals(matriz[5])&& matriz[7].equals(matriz[3]) && matriz[3].toString() != ""){
            ganhou(matriz[3]);
            corVencedor[0] = 3;
            corVencedor[1] = 5;
            corVencedor[2] = 7;
            int c=4;
            return;
        }
        if(matriz[4].equals(matriz[5])&& matriz[6].equals(matriz[4]) && matriz[4].toString() != ""){
            ganhou(matriz[4]);
            corVencedor[0] = 4;
            corVencedor[1] = 5;
            corVencedor[2] = 6;
            int c=7;
            return;
        }
        if(matriz[7].equals(matriz[8])&& matriz[7].equals(matriz[9]) && matriz[7].toString() != ""){
            ganhou(matriz[7]);
            corVencedor[0] = 7;
            corVencedor[1] = 8;
            corVencedor[2] = 9;
            int c=4;
            return;
        }
        if(jogadas==9){
            ganhou("");
            return;
        }

    }

    private void ganhou(String ganhador){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("ACABOU!");
        //colorirVencedor();
        if (ganhador.equals("")){
            builder.setMessage("DEU VELHA");
        }else{
            if (ganhador.equals("X")){
                builder.setMessage("\" X \" VENCEU ");
            }else{
                builder.setMessage("\" O\" VENCEU ");
            }
        }
        builder.setPositiveButton("NOVO JOGO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                jogadas = 0;
                for (int a = 1; a < 10; a++) {
                    matriz[a] = "";
                }
                exibirTabuleiro();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
