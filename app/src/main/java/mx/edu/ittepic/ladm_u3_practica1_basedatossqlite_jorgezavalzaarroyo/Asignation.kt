package mx.edu.ittepic.ladm_u3_practica1_basedatossqlite_jorgezavalzaarroyo

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import mx.edu.ittepic.ladm_u3_practica1_basedatossqlite_jorgezavalzaarroyo.databinding.ActivityAsignationBinding

class Asignation : AppCompatActivity() {
    lateinit var binding: ActivityAsignationBinding
    var codigobarras = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAsignationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle("Asignar Equipo")

        codigobarras = this.intent.extras!!.getString("codigobarras")!!
        binding.codigo.setText(codigobarras)

        binding.asignar.setOnClickListener {
            val asignacion = Asignacion(this)
            asignacion.nombreempleado = binding.nombre.text.toString()
            asignacion.areatrabajo = binding.area.text.toString()
            asignacion.fecha = binding.fecha.text.toString()
            asignacion.codigobarras = codigobarras

            val resultado = asignacion.insertar()
            if ( resultado ) {
                Toast.makeText(this, "Se insertó con éxito", Toast.LENGTH_LONG).show()
                binding.nombre.setText("")
                binding.area.setText("")
                binding.fecha.setText("")
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Error de asignación")
                    .setMessage("Se ha producido un error al intentar asignar el equipo")
                    .show()
            }

            binding.regresar.setOnClickListener {
                onBackPressed()
            }
        }
    }
}