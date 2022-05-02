package mx.edu.ittepic.ladm_u3_practica1_basedatossqlite_jorgezavalzaarroyo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException

class Asignacion( este:Context ) {
    private val este = este
    var idasignacion = ""
    var nombreempleado = ""
    var areatrabajo = ""
    var fecha = ""
    var codigobarras = ""
    private var err = ""

    fun insertar() : Boolean {
        val baseDatos = BaseDatos(este, "practica1", null, 1)
        err = ""
        try {
            var tabla = baseDatos.writableDatabase
            var datos = ContentValues()
            datos.put("NOM_EMPLEADO", nombreempleado)
            datos.put("AREA_TRABAJO", areatrabajo)
            datos.put("FECHA", fecha)
            datos.put("CODIGOBARRAS", codigobarras)

            val respuesta = tabla.insert("ASIGNACION", null, datos)
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

    fun selectTodos() : ArrayList<Asignacion> {
        val baseDatos = BaseDatos(este, "practica1", null, 1)
        err = ""
        var arreglo = ArrayList<Asignacion>()
        try {
            val tabla = baseDatos.readableDatabase
            val query = "SELECT * FROM ASIGNACION"
            val cursor = tabla.rawQuery(query, null)

            if ( cursor.moveToFirst() ) {
                do {
                    var asignacion = Asignacion(este)
                    asignacion.idasignacion = cursor.getString(0)
                    asignacion.nombreempleado = cursor.getString(1)
                    asignacion.areatrabajo = cursor.getString(2)
                    asignacion.fecha = cursor.getString(3)
                    asignacion.codigobarras = cursor.getString(4)
                    arreglo.add(asignacion)

                } while ( cursor.moveToNext() )
            }
        } catch ( err:SQLiteException ) {
            this.err = err.message!!
        } finally {
            baseDatos.close()
        }
        return arreglo
    }

    fun selectAsignacion( idAsignacion:String ) : Asignacion {
        val baseDatos = BaseDatos(este, "practica1", null, 1)
        err = ""
        var asignacion = Asignacion(este)
        try {
            val tabla = baseDatos.readableDatabase
            val query = "SELECT * FROM ASIGNACION WHERE IDASIGNACION=?"
            val cursor = tabla.rawQuery(query, arrayOf(idAsignacion))
            if ( cursor.moveToFirst() ) {
                asignacion.idasignacion = cursor.getString(0)
                asignacion.nombreempleado = cursor.getString(1)
                asignacion.areatrabajo = cursor.getString(2)
                asignacion.fecha = cursor.getString(3)
                asignacion.codigobarras = cursor.getString(4)
            }
        } catch ( err:SQLiteException ) {
            this.err = err.message!!
        } finally {
            baseDatos.close()
        }
        return asignacion
    }

    fun selectPorEmpleado( nomEmpleado:String ) : ArrayList<Asignacion> {
        val baseDatos = BaseDatos(este, "practica1", null, 1)
        err = ""
        var arreglo = ArrayList<Asignacion>()
        try {
            val tabla = baseDatos.readableDatabase
            val query = "SELECT * FROM ASIGNACION WHERE NOM_EMPLEADO=?"
            val cursor = tabla.rawQuery(query, arrayOf(nomEmpleado))

            if ( cursor.moveToFirst() ) {
                do {
                    var asignacion = Asignacion(este)
                    asignacion.idasignacion = cursor.getString(0)
                    asignacion.nombreempleado = cursor.getString(1)
                    asignacion.areatrabajo = cursor.getString(2)
                    asignacion.fecha = cursor.getString(3)
                    asignacion.codigobarras = cursor.getString(4)
                    arreglo.add(asignacion)

                } while ( cursor.moveToNext() )
            }
        } catch ( err:SQLiteException ) {
            this.err = err.message!!
        } finally {
            baseDatos.close()
        }
        return arreglo
    }

    fun selectPorEquipo( tipoEquipo:String ) : ArrayList<Asignacion> { // Inner join
        val baseDatos = BaseDatos(este, "practica1", null, 1)
        err = ""
        var arreglo = ArrayList<Asignacion>()
        try {
            val tabla = baseDatos.readableDatabase
            val query = "SELECT * FROM ASIGNACION WHERE TIPOEQUIPO=?"
            val cursor = tabla.rawQuery(query, arrayOf(tipoEquipo))

            if ( cursor.moveToFirst() ) {
                do {
                    var asignacion = Asignacion(este)
                    asignacion.idasignacion = cursor.getString(0)
                    asignacion.nombreempleado = cursor.getString(1)
                    asignacion.areatrabajo = cursor.getString(2)
                    asignacion.fecha = cursor.getString(3)
                    asignacion.codigobarras = cursor.getString(4)
                    arreglo.add(asignacion)

                } while ( cursor.moveToNext() )
            }
        } catch ( err:SQLiteException ) {
            this.err = err.message!!
        } finally {
            baseDatos.close()
        }
        return arreglo
    }

    fun selectPorFecha( fechaAsig:String ) : ArrayList<Asignacion> {
        val baseDatos = BaseDatos(este, "practica1", null, 1)
        err = ""
        var arreglo = ArrayList<Asignacion>()
        try {
            val tabla = baseDatos.readableDatabase
            val query = "SELECT * FROM ASIGNACION WHERE FECHA=?"
            val cursor = tabla.rawQuery(query, arrayOf(fechaAsig))

            if ( cursor.moveToFirst() ) {
                do {
                    var asignacion = Asignacion(este)
                    asignacion.idasignacion = cursor.getString(0)
                    asignacion.nombreempleado = cursor.getString(1)
                    asignacion.areatrabajo = cursor.getString(2)
                    asignacion.fecha = cursor.getString(3)
                    asignacion.codigobarras = cursor.getString(4)
                    arreglo.add(asignacion)

                } while ( cursor.moveToNext() )
            }
        } catch ( err:SQLiteException ) {
            this.err = err.message!!
        } finally {
            baseDatos.close()
        }
        return arreglo
    }
    fun selectPorArea( areaTrabajo:String ) : ArrayList<Asignacion> {
        val baseDatos = BaseDatos(este, "practica1", null, 1)
        err = ""
        var arreglo = ArrayList<Asignacion>()
        try {
            val tabla = baseDatos.readableDatabase
            val query = "SELECT * FROM ASIGNACION WHERE AREA_TRABAJO=?"
            val cursor = tabla.rawQuery(query, arrayOf(areaTrabajo))

            if ( cursor.moveToFirst() ) {
                do {
                    var asignacion = Asignacion(este)
                    asignacion.idasignacion = cursor.getString(0)
                    asignacion.nombreempleado = cursor.getString(1)
                    asignacion.areatrabajo = cursor.getString(2)
                    asignacion.fecha = cursor.getString(3)
                    asignacion.codigobarras = cursor.getString(4)
                    arreglo.add(asignacion)

                } while ( cursor.moveToNext() )
            }
        } catch ( err:SQLiteException ) {
            this.err = err.message!!
        } finally {
            baseDatos.close()
        }
        return arreglo
    }

    fun actualizar() : Boolean {
        val baseDatos = BaseDatos(este, "practica1", null, 1)
        err = ""
        try {
            val tabla = baseDatos.writableDatabase
            val datosActualizados = ContentValues()
            datosActualizados.put("NOM_EMPLEADO", nombreempleado)
            datosActualizados.put("AREA_TRABAJO", areatrabajo)
            datosActualizados.put("FECHA", fecha)
            val respuesta = tabla.update("ASIGNACION",datosActualizados, "IDASIGNACION=?", arrayOf(idasignacion))
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
            val tabla = baseDatos.writableDatabase
            val respuesta = tabla.delete("ASIGNACION", "IDASIGNACION=?", arrayOf(idasignacion))
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

    fun triggerCodigoEliminado( codigoBarra:String ) : Boolean {
        val baseDatos = BaseDatos(este, "practica1", null, 1)
        err = ""
        try {
            val tabla = baseDatos.writableDatabase
            val respuesta = tabla.delete("ASIGNACION", "CODIGOBARRAS=?", arrayOf(codigoBarra))
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