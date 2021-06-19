package com.example.vehiculosqlite;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VehiculoAdaptador extends RecyclerView.Adapter<VehiculoAdaptador.ViewHolder> {

    private List<Vehiculo> listaVehiculos;
    Context context;

    public VehiculoAdaptador(Context context, List<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehiculo, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String marcaModelo = listaVehiculos.get(position).getMarca() + " " + listaVehiculos.get(position).getModelo();
        String matricula = listaVehiculos.get(position).getMatricula();
        holder.marcaModelo.setText(marcaModelo);
        holder.matricula.setText(matricula);
    }

    @Override
    public int getItemCount() {
        return listaVehiculos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        private TextView marcaModelo;
        private TextView matricula;
        CardView card;
        public ViewHolder(View v) {
            super(v);
            marcaModelo = (TextView) v.findViewById(R.id.txtMarcaModelo);
            matricula = (TextView) v.findViewById(R.id.txtMatricula);
            card = v.findViewById(R.id.item);
            card.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Opciones del vehiculo");
            menu.add(this.getAdapterPosition(), 1, 0, "Eliminar vehiculo");
            menu.add(this.getAdapterPosition(), 2, 1, "Modificar vehiculo");
        }
    }
}
