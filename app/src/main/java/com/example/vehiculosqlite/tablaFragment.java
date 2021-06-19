package com.example.vehiculosqlite;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class tablaFragment extends Fragment {

    RecyclerView recyclerVehiculos;
    VehiculoAdaptador vehiculoAdaptador;
    IDAOVehiculo dao;

    public tablaFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tabla_fragment, container, false);

        dao = DAOVehiculo.getInstance(getContext());
        vehiculoAdaptador = new VehiculoAdaptador(v.getContext(), dao.getVehiculos());
        recyclerVehiculos = v.findViewById(R.id.recyclerVehiculos);
        recyclerVehiculos.setHasFixedSize(true);
        recyclerVehiculos.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerVehiculos.setAdapter(vehiculoAdaptador);
        Toast.makeText(getContext(), "Manten pulsado un vehiculo para mostrar opciones.", Toast.LENGTH_LONG).show();

        return v;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case 1:
                dao.eliminarVehiculo(dao.getVehiculos().get(item.getGroupId()));
                actualizarTabla();
                Toast.makeText(getContext(), "Vehiculo eliminado.", Toast.LENGTH_SHORT).show();
                return true;
            case 2:
                Intent i = new Intent(getActivity(), modificarActivity.class);
                i.putExtra("id", item.getGroupId());
                startActivity(i);
                return true;
        }
        return super.onContextItemSelected(item);

    }

    public void actualizarTabla() {
        vehiculoAdaptador = new VehiculoAdaptador(getContext(), dao.getVehiculos());
        recyclerVehiculos.setAdapter(vehiculoAdaptador);
    }

}
