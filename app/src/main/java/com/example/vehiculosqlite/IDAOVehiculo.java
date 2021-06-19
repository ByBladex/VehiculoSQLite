package com.example.vehiculosqlite;

import java.util.List;

public interface IDAOVehiculo {

    public int insertarVehiculo(Vehiculo vehiculo);
    public int eliminarVehiculo(Vehiculo vehiculo);
    public int eliminarVehiculos(List<Vehiculo> lstVehiculos);
    public int modificarVehiculo(int ind, String marca, String modelo, String matricula);
    public Vehiculo getVehiculo(String matricula);
    public  List<Vehiculo> getVehiculos();
}
