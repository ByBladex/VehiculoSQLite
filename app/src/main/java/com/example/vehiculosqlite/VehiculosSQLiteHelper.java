package com.example.vehiculosqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class VehiculosSQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE \"Vehiculo\" (\n" +
            "\t\"marca\"\tTEXT NOT NULL,\n" +
            "\t\"modelo\"\tTEXT NOT NULL,\n" +
            "\t\"matricula\"\tTEXT NOT NULL,\n" +
            "\tPRIMARY KEY(\"matricula\")\n" +
            ")";

    public VehiculosSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
        db.execSQL("INSERT INTO Vehiculo (marca, modelo, matricula) VALUES ('Toyota', 'Supra', 'AVD0101'), ('Nissan', 'Skyline', 'JDK0101'), ('Mazda', 'Rx-7', 'SDK0101'), ('Honda', 'NSX', 'SQL0101')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Vehiculo");
        db.execSQL(sqlCreate);
    }
}
