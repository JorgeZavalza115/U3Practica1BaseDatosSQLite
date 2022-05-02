package mx.edu.ittepic.ladm_u3_practica1_basedatossqlite_jorgezavalzaarroyo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException

class Inventario( este:Context ) {
    private val este = este
    var codigobarras = ""
    var tipoequipo = ""
    var caracteristicas = ""
    var fechacompra = ""
    private var err = ""

    fun insertar() : Boolean {
        val baseDatos = BaseDatos( este, "practica1", null, 1)
        err = ""
        try {
            val tabla = baseDatos.writableDatabase
            var datos = ContentValues()
            datos.put("CODIGOBARRAS", codigobarras)
            datos.put("TIPOEQUIPO", tipoequipo)
            datos.put("CARACTERISTICAS", caracteristicas)
            datos.put("FECHACOMPRA", fechacompra)

            val respuesta = tabla.insert("INVENTARIO", null, datos)
            if ( respuesta == -1L ) {
                return false
            }
        } catch ( err:SQLiteException ) {
            this.err = err.message!!
            return false
        } finally {
            baseDatos.close()
        }
        return true
    }

    fun selectTodos() : ArrayList<Inventario> {
        val baseDatos = BaseDatos(este, "practica1", null, 1)
        err = ""
        var arreglo = ArrayList<Inventario>()

        try {
            val tabla = baseDatos.readableDatabase
            val query = "SELECT * FROM INVENTARIO"
            val cursor = tabla.rawQuery( query, null)

            if ( cursor.moveToFirst() ) {
                do {
                    var inventario = Inventario(este)
                    inventario.codigobarras = cursor.getString(0)
                    inventario.tipoequipo = cursor.getString(1)
                    inventario.caracteristicas = cursor.getString(2)
                    inventario.fechacompra = cursor.getString(3)
                    arreglo.add(inventario)

                } while ( cursor.moveToNext() )
            }
        } catch ( err:SQLiteException ) {
            this.err = err.message!!
        }
        return arreglo
    }

    fun selectEquipo( codigobarras:String ) : Inventario {
        val baseDatos = BaseDatos(este, "practica1", null, 1)
        err = ""
        val inventario = Inventario(este)
        try {
            val tabla = baseDatos.readableDatabase
            val query = "SELECT * FROM INVENTARIO WHERE CODIGOBARRAS=?"
            val cursor = tabla.rawQuery(query, arrayOf(codigobarras))
            if ( cursor.moveToFirst() ) {
                inventario.codigobarras = cursor.getString(0)
                inventario.tipoequipo = cursor.getString(1)
                inventario.caracteristicas = cursor.getString(2)
                inventario.fechacompra = cursor.getString(3)
            }
        } catch ( err:SQLiteException ) {
            this.err = err.message!!
        } finally {
            baseDatos.close()
        }
        return inventario
    }

    fun selectPorTipo() : ArrayList<Inventario> {
        val baseDatos = BaseDatos(este, "practica1", null, 1)
        err = ""
        var arreglo = ArrayList<Inventario>()

        try {
            val tabla = baseDatos.readableDatabase
            val query = "SELECT * FROM INVENTARIO ORDER BY TIPOEQUIPO"
            val cursor = tabla.rawQuery( query, null)

            if ( cursor.moveToFirst() ) {
                do {
                    var inventario = Inventario(este)
                    inventario.codigobarras = cursor.getString(0)
                    inventario.tipoequipo = cursor.getString(1)
                    inventario.caracteristicas = cursor.getString(2)
                    inventario.fechacompra = cursor.getString(3)

                } while ( cursor.moveToNext() )
            }
        } catch ( err:SQLiteException ) {
            this.err = err.message!!
        }
        return arreglo
    }

    fun selectPorCaracteriticas() : ArrayList<Inventario> {
        val baseDatos = BaseDatos(este, "practica1", null, 1)
        err = ""
        var arreglo = ArrayList<Inventario>()

        try {
            val tabla = baseDatos.readableDatabase
            val query = "SELECT * FROM INVENTARIO ORDER BY CARACTERISTICAS"
            val cursor = tabla.rawQuery( query, null)

            if ( cursor.moveToFirst() ) {
                do {
                    var inventario = Inventario(este)
                    inventario.codigobarras = cursor.getString(0)
                    inventario.tipoequipo = cursor.getString(1)
                    inventario.caracteristicas = cursor.getString(2)
                    inventario.fechacompra = cursor.getString(3)

                } while ( cursor.moveToNext() )
            }
        } catch ( err:SQLiteException ) {
            this.err = err.message!!
        }
        return arreglo
    }

    fun selectPorFecha() : ArrayList<Inventario> {
        val baseDatos = BaseDatos(este, "practica1", null, 1)
        err = ""
        var arreglo = ArrayList<Inventario>()

        try {
            val tabla = baseDatos.readableDatabase
            val query = "SELECT * FROM INVENTARIO ORDER BY CARACTERISTICAS"
            val cursor = tabla.rawQuery( query, null)

            if ( cursor.moveToFirst() ) {
                do {
                    var inventario = Inventario(este)
                    inventario.codigobarras = cursor.getString(0)
                    inventario.tipoequipo = cursor.getString(1)
                    inventario.caracteristicas = cursor.getString(2)
                    inventario.fechacompra = cursor.getString(3)

                } while ( cursor.moveToNext() )
            }
        } catch ( err:SQLiteException ) {
            this.err = err.message!!
        }
        return arreglo
    }

    fun actualizar() : Boolean {
        val baseDatos = BaseDatos(este, "practica1", null, 1)
        err = ""
        try {
            var tabla = baseDatos.writableDatabase
            val datosActualizados = ContentValues()
            datosActualizados.put("TIPOEQUIPO", tipoequipo)
            datosActualizados.put("CARACTERISTICAS", caracteristicas)
            datosActualizados.put("FECHACOMPRA", fechacompra)
            val respuesta = tabla.update("INVENTARIO", datosActualizados, "CODIGOBARRAS=?", arrayOf(codigobarras))
            if ( respuesta == 0 ) {
                return false
            }
        } catch ( err:SQLiteException ) {
            this.err = err.message!!
            return false
        } finally {
            baseDatos.close()
        }
        return true
    }

    fun eliminar() : Boolean {
        val baseDatos = BaseDatos(este, "practica1", null, 1)
        err = ""
        try {
            var tabla = baseDatos.writableDatabase
            val respuesta = tabla.delete("INVENTARIO", "CODIGOBARRAS=?", arrayOf(codigobarras))
            if ( respuesta == 0 ) {
                return false
            }
        } catch ( err:SQLiteException ) {
            this.err = err.message!!
            return false
        } finally {
            baseDatos.close()
        }
        return true
    }
}