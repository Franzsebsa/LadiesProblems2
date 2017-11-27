package com.example.franz.ladiesproblems;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.franz.ladiesproblems.modelo.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Registro extends AppCompatActivity {

    MaterialEditText edtphone, edtnombre, edtcontra;
    Button registrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edtnombre = (MaterialEditText)findViewById(R.id.edtnombre);
        edtphone = (MaterialEditText)findViewById(R.id.edtphone);
        edtcontra = (MaterialEditText)findViewById(R.id.contra);

        registrar = (Button)findViewById(R.id.btnregistrar);

        //Init Firebase

        FirebaseDatabase datos = FirebaseDatabase.getInstance();
        final DatabaseReference tabla_u =  datos.getReference("user");

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mdialog = new ProgressDialog(Registro.this);
                mdialog.setMessage(" Por favor espere.. ");
                mdialog.show();

                tabla_u.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Analizamos el usuario
                        if(dataSnapshot.child(edtphone.getText().toString()).exists())
                        {
                            mdialog.dismiss();
                            Toast.makeText(Registro.this, "Numero Registrado ", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mdialog.dismiss();
                            Usuario user = new Usuario(edtnombre.getText().toString(),edtcontra.getText().toString());
                            tabla_u.child(edtphone.getText().toString()).setValue(user);
                            Toast.makeText(Registro.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                            finish();
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
