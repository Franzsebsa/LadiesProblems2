package com.example.franz.ladiesproblems;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.franz.ladiesproblems.modelo.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Ingreso extends AppCompatActivity {
    EditText edtxphone, edtxcontra;
    Button ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);

        edtxcontra = (MaterialEditText)findViewById(R.id.contra);
        edtxphone = (MaterialEditText)findViewById(R.id.edtphone);

        ingresar=(Button)findViewById(R.id.btningresar);

        //Init Firebase

        FirebaseDatabase datos = FirebaseDatabase.getInstance();
        final DatabaseReference tabla_u =  datos.getReference("user");

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final ProgressDialog mdialog = new ProgressDialog(Ingreso.this);
                mdialog.setMessage(" Por favor espere.. ");
                mdialog.show();
                tabla_u.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Si el usuario no existe
                        if (dataSnapshot.child(edtxphone.getText().toString()).exists()){


                        //Obtenemos la informacion de usuario
                        mdialog.dismiss();
                        Usuario user = dataSnapshot.child(edtxphone.getText().toString()).getValue(Usuario.class);
                        if(user.getContraseña().equals(edtxcontra.getText().toString())){
                            Toast.makeText(Ingreso.this, "Sesion Iniciada", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Ingreso.this, "Sesion Incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                            mdialog.dismiss();
                            Toast.makeText(Ingreso.this, "El usuario no existe", Toast.LENGTH_SHORT).show();
                        }
                }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}
