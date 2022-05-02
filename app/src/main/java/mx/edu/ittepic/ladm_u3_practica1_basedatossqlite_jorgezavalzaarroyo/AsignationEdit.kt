package mx.edu.ittepic.ladm_u3_practica1_basedatossqlite_jorgezavalzaarroyo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import mx.edu.ittepic.ladm_u3_practica1_basedatossqlite_jorgezavalzaarroyo.databinding.ActivityAsignationEditBinding

class AsignationEdit : AppCompatActivity() {
    lateinit var binding: ActivityAsignationEditBinding
    var idasignacion = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAsignationEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle("Editar Asignación")

        idasignacion = this.intent.extras!!.getString("idasig")!!
        val asignacion = Asignacion(this).selectAsignacion(idasignacion)
        binding.nombre.setText(asignacion.nombreempleado)
        binding.area.setText(asignacion.areatrabajo)
        binding.fecha.setText(asignacion.fecha)
        binding.codigo.setText(asignacion.codigobarras)

        binding.actualizar.setOnClickListener {
            val asignacion = Asignacion(this)
            asignacion.idasignacion = idasignacion
            asignacion.nombreempleado = binding.nombre.text.toString()
            asignacion.areatrabajo = binding.area.text.toString()
            asignacion.fecha = binding.fecha.text.toString()
            asignacion.codigobarras = binding.codigo.text.toString()


            val respuesta = asignacion.actualizar()
            if ( respuesta ) {
                Toast.makeText(this, "Se actualizó con éxito", Toast.LENGTH_LONG).show()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Ha ocurrido un error al intentar actualizar")
                    .show()
            }
        }

        binding.regresar.setOnClickListener {
            finish()
        }
    }
}