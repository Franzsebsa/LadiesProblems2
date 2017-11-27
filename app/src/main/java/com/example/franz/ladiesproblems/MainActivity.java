package com.example.franz.ladiesproblems;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btningresar, btnregistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnregistrar = (Button)findViewById(R.id. btnregistrar);
        btningresar = (Button)findViewById(R.id. btningresar);


        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regi = new Intent(MainActivity.this,Registro.class);
                startActivity(regi);

            }
        });
        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ingr = new Intent(MainActivity.this,Ingreso.class);
                startActivity(ingr);

            }
        });
    }
}
