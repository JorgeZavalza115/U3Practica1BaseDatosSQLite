package mx.edu.ittepic.ladm_u3_practica1_basedatossqlite_jorgezavalzaarroyo.ui.gallery

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import mx.edu.ittepic.ladm_u3_practica1_basedatossqlite_jorgezavalzaarroyo.*
import mx.edu.ittepic.ladm_u3_practica1_basedatossqlite_jorgezavalzaarroyo.databinding.FragmentGalleryBinding
import mx.edu.ittepic.ladm_u3_practica1_basedatossqlite_jorgezavalzaarroyo.ui.home.HomeViewModel

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    var listaCodigos = ArrayList<String>()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mostrarDatosLista()

        binding.agregarequipo.setOnClickListener {
            var otraVentana = Intent(activity, Equipment::class.java)
            startActivity(otraVentana)
        }
        return root
    }

    fun mostrarDatosLista() {
        var listaTodos =Inventario(requireContext()).selectTodos()
        var elementosLista = ArrayList<String>()
        listaCodigos.clear()

        (0..listaTodos.size-1).forEach {
            val eqp = listaTodos.get(it)
            elementosLista.add("Código: "+eqp.codigobarras+"\nEquipo: "+eqp.tipoequipo+"\nFecha: "+eqp.fechacompra)
            listaCodigos.add(eqp.codigobarras)
        }
        binding.listainventario.adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, elementosLista)

        binding.listainventario.setOnItemClickListener { adapterView, view, indice, l ->
            val codigoLista = listaCodigos.get(indice)
            val equipo = Inventario(requireContext()).selectEquipo(codigoLista)
            AlertDialog.Builder(requireContext())
                .setTitle("Atención")
                .setMessage("¿Qué desea hacer con el equipo: ${equipo.tipoequipo}?")
                .setNegativeButton("Eliminar"){ d, i ->
                    val asignacion = Asignacion(requireContext()).triggerCodigoEliminado(equipo.codigobarras)
                    equipo.eliminar()
                    mostrarDatosLista()
                }
                .setPositiveButton("Actualizar"){ d, i ->
                    var otraVentana = Intent(activity, EquipmentEdit::class.java)
                    otraVentana.putExtra("codigobarras", equipo.codigobarras)
                    startActivity(otraVentana)
                }
                .setNeutralButton("Asignar"){ d, i ->
                    var otraVentana = Intent(activity, Asignation::class.java)
                    otraVentana.putExtra("codigobarras", equipo.codigobarras)
                    startActivity(otraVentana)
                }
                .show()
        }
    }

    override fun onResume() {
        super.onResume()
        mostrarDatosLista()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}