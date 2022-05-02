package mx.edu.ittepic.ladm_u3_practica1_basedatossqlite_jorgezavalzaarroyo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate( db: SQLiteDatabase ) {
        db.execSQL("CREATE TABLE INVENTARIO ( CODIGOBARRAS VARCHAR(50) PRIMARY KEY, TIPOEQUIPO VARCHAR(200), CARACTERISTICAS VARCHAR(500), FECHACOMPRA DATE )")
        db.execSQL("CREATE TABLE ASIGNACION ( IDASIGNACION INTEGER PRIMARY KEY AUTOINCREMENT, NOM_EMPLEADO VARCHAR(200), AREA_TRABAJO VARCHAR(50), FECHA DATE, CODIGOBARRAS VARCHAR(50), FOREIGN KEY(CODIGOBARRAS) REFERENCES INVENTARIO(CODIGOBARRAS) )")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}