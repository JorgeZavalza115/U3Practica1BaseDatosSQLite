package mx.edu.ittepic.ladm_u3_practica1_basedatossqlite_jorgezavalzaarroyo

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import mx.edu.ittepic.ladm_u3_practica1_basedatossqlite_jorgezavalzaarroyo.databinding.ActivityEquipmentBinding

class Equipment : AppCompatActivity() {
    lateinit var binding: ActivityEquipmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEquipmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle("Agregar Equipo")

        binding.agregarequipo.setOnClickListener {
            var inventario = Inventario(this)
            inventario.codigobarras = binding.codigo.text.toString()
            inventario.tipoequipo = binding.tipo.text.toString()
            inventario.caracteristicas = binding.caracteristicas.text.toString()
            inventario.fechacompra = binding.fecha.text.toString()

            val resultado = inventario.insertar()
            if ( resultado ) {
                Toast.makeText(this, "Se insertó con éxito", Toast.LENGTH_LONG).show()
                binding.tipo.setText("")
                binding.caracteristicas.setText("")
                binding.fecha.setText("")
                binding.codigo.setText("")
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Error de inserción")
                    .setMessage("Se ha producido un error al intentar insertar los datos")
                    .show()
            }
        }

        binding.regresar.setOnClickListener {
            onBackPressed()
        }

    }
}