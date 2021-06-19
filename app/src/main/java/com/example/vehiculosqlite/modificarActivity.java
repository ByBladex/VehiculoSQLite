package com.example.vehiculosqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class modificarActivity extends AppCompatActivity {

    private IDAOVehiculo dao;
    private int id;
    EditText txtMarca;
    EditText txtModelo;
    EditText txtMatricula;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_view);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("id");

        dao = DAOVehiculo.getInstance(getApplicationContext());
        Vehiculo vehiculo = dao.getVehiculos().get(id);

        txtMarca = findViewById(R.id.txtModifMarca);
        txtMarca.setText(vehiculo.getMarca());
        txtModelo = findViewById(R.id.txtModifModelo);
        txtModelo.setText(vehiculo.getModelo());
        txtMatricula = findViewById(R.id.txtModifMatricula);
        txtMatricula.setText(vehiculo.getMatricula());

        Button btn = findViewById(R.id.btnModif);
        btn.setOnClickListener(modificar);
        ImageButton btnAtras = findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(retroceder);
    }

    View.OnClickListener modificar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!txtMarca.getText().toString().equals("") && !txtModelo.getText().toString().equals("") && !txtMatricula.getText().toString().equals("")) {
                if (dao.modificarVehiculo(id, txtMarca.getText().toString(), txtModelo.getText().toString(), txtMatricula.getText().toString()) == 1) {
                    Toast.makeText(v.getContext(), "Vehiculo modificado", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(v.getContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(v.getContext(), "Error en la modificación", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(v.getContext(), "Ha dejado algún campo vacío.", Toast.LENGTH_SHORT).show();
            }
        }
    };

    View.OnClickListener retroceder = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), MainActivity.class);
            startActivity(i);
            finish();
        }
    };

}
