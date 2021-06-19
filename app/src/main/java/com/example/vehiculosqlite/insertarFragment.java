package com.example.vehiculosqlite;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class insertarFragment extends Fragment {

    EditText editTextMarca;
    EditText editTextModelo;
    EditText editTextMatricula;
    IDAOVehiculo dao;
    Context context;

    public insertarFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.insertar_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dao = DAOVehiculo.getInstance(getContext());
        context = view.getContext();
        editTextMarca = view.findViewById(R.id.editTextMarca);
        editTextModelo = view.findViewById(R.id.editTextModelo);
        editTextMatricula = view.findViewById(R.id.editTextMatricula);
        FloatingActionButton btnInsertar = view.findViewById(R.id.btnInsertar);
        btnInsertar.setOnClickListener(insertarVehiculo);
    }

    View.OnClickListener insertarVehiculo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!editTextMarca.getText().toString().equals("") && !editTextModelo.getText().toString().equals("") && !editTextMatricula.getText().toString().equals("")) {
                if (dao.insertarVehiculo(new Vehiculo(editTextMarca.getText().toString(), editTextModelo.getText().toString(), editTextMatricula.getText().toString())) == 1) {
                    editTextMarca.setText("");
                    editTextModelo.setText("");
                    editTextMatricula.setText("");
                    Toast.makeText(context, "Vehiculo insertado.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Error en la inserción.", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(context, "Ha dejado algún campo vacío.", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
