package com.example.vehiculosqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DAOVehiculo implements IDAOVehiculo {

    private static IDAOVehiculo dao = null;
    private List<Vehiculo> listaVehiculos;
    private VehiculosSQLiteHelper helper;
    private SQLiteDatabase db;
    private Context context;

    private DAOVehiculo(Context context) {
        listaVehiculos = new ArrayList<Vehiculo>();
        this.context = context;
        helper = new VehiculosSQLiteHelper(context,"VehiculosSQLite", null, 1);

        construirLista();
    }

    public int construirLista() {
        try {
            db = helper.getWritableDatabase();
            listaVehiculos.clear();
            if (db != null) {
                Cursor c = db.rawQuery(" SELECT * FROM Vehiculo ", null);

                if (c.moveToFirst()){
                    do {
                        listaVehiculos.add(new Vehiculo(c.getString(0), c.getString(1), c.getString(2)));
                    }
                    while (c.moveToNext());
                }
                c.close();
                db.close();
                return 1;
            }
            return 0;
        }
        catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public int insertarVehiculo(Vehiculo vehiculo) {
        try {
            db = helper.getWritableDatabase();
            if (db != null) {
                db.execSQL("INSERT INTO Vehiculo (marca, modelo, matricula)VALUES('" + vehiculo.getMarca() + "', '" + vehiculo.getModelo() +  "', '" + vehiculo.getMatricula() + "')");
                construirLista();
                db.close();
                return 1;
            }
            else {
                return 0;
            }
        }
        catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public int eliminarVehiculo(Vehiculo vehiculo) {
        try {
            db = helper.getWritableDatabase();
            if (db != null) {
                db.execSQL("DELETE FROM Vehiculo WHERE matricula = '" + vehiculo.getMatricula() + "'");
                construirLista();
                db.close();
                return 1;
            }
            construirLista();
            return 0;
        }
        catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public int eliminarVehiculos(List<Vehiculo> lstVehiculos) {
        try {
            db = helper.getWritableDatabase();
            if (db != null) {
                for (Vehiculo vehiculo: listaVehiculos) {
                    db.execSQL("DELETE FROM Vehiculo WHERE matricula = '" + vehiculo.getMatricula() + "'");
                }
                db.close();
                construirLista();
                return 1;
            }
            construirLista();
            return 0;
        }
        catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public int modificarVehiculo(int ind, String marca, String modelo, String matricula) {
        try {
            Vehiculo vehiculo = listaVehiculos.get(ind);
            db = helper.getWritableDatabase();
            if (db != null) {
                db.execSQL("UPDATE Vehiculo SET marca = '" + marca + "', modelo = '" + modelo + "', matricula = '" + matricula + "' WHERE matricula = '" + vehiculo.getMatricula() + "'");
                db.close();
                construirLista();
                return 1;
            }
            construirLista();
            return 0;
        }
        catch (ArrayIndexOutOfBoundsException | SQLException e) {
            return 0;
        }
    }

    @Override
    public Vehiculo getVehiculo(String matricula) {
        for (Vehiculo elemento: listaVehiculos) {
            if (elemento.getMatricula().equals(matricula)) {
                return elemento;
            }
        }
        return null;
    }

    @Override
    public List<Vehiculo> getVehiculos() {
        return listaVehiculos;
    }

    public static IDAOVehiculo getInstance(Context context) {
        if (dao == null) dao = new DAOVehiculo(context);

        return dao;
    }
}
