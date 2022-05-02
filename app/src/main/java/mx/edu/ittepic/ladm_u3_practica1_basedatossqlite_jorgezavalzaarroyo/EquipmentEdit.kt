package mx.edu.ittepic.ladm_u3_practica1_basedatossqlite_jorgezavalzaarroyo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import mx.edu.ittepic.ladm_u3_practica1_basedatossqlite_jorgezavalzaarroyo.databinding.ActivityEquipmentEditBinding

class EquipmentEdit : AppCompatActivity() {
    lateinit var binding: ActivityEquipmentEditBinding
    var codigoBarras = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEquipmentEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle("Editar Equipo")

        codigoBarras = this.intent.extras!!.getString("codigobarras")!!
        val equipo = Inventario(this).selectEquipo(codigoBarras)
        binding.tipo.setText(equipo.tipoequipo)
        binding.caracteristicas.setText(equipo.caracteristicas)
        binding.fecha.setText(equipo.fechacompra)
        binding.codigo.setText(equipo.codigobarras)

        binding.actualizar.setOnClickListener {
            var equipo = Inventario(this)
            equipo.tipoequipo = binding.tipo.text.toString()
            equipo.caracteristicas = binding.caracteristicas.text.toString()
            equipo.fechacompra = binding.fecha.text.toString()
            equipo.codigobarras = binding.codigo.text.toString()

            val respuesta = equipo.actualizar()
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