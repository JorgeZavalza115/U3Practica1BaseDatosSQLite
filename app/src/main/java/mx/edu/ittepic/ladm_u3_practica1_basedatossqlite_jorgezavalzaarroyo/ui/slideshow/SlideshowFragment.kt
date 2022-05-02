package mx.edu.ittepic.ladm_u3_practica1_basedatossqlite_jorgezavalzaarroyo.ui.slideshow

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.edu.ittepic.ladm_u3_practica1_basedatossqlite_jorgezavalzaarroyo.Asignacion
import mx.edu.ittepic.ladm_u3_practica1_basedatossqlite_jorgezavalzaarroyo.AsignationEdit
import mx.edu.ittepic.ladm_u3_practica1_basedatossqlite_jorgezavalzaarroyo.EquipmentEdit
import mx.edu.ittepic.ladm_u3_practica1_basedatossqlite_jorgezavalzaarroyo.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    var listaIDs = ArrayList<String>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mostrarDatosLista()

        binding.listaempleados.setOnItemClickListener { adapterView, view, indice, l ->  }

        return root
    }

    fun mostrarDatosLista() {
        val listaTodos = Asignacion(requireContext()).selectTodos()
        var elementosLista = ArrayList<String>()
        listaIDs.clear()

        (0..listaTodos.size-1).forEach {
            val asig = listaTodos.get(it)
            elementosLista.add("Código: "+asig.codigobarras+"\nEmpleado: "+asig.nombreempleado+"\nArea: "+asig.areatrabajo)
            listaIDs.add(asig.idasignacion)
        }
        binding.listaempleados.adapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_list_item_1, elementosLista)

        binding.listaempleados.setOnItemClickListener { adapterView, view, indice, l ->
            val idLista = listaIDs.get(indice)
            val asignacion = Asignacion(requireContext()).selectAsignacion(idLista)
            AlertDialog.Builder(requireContext())
                .setTitle("Atención")
                .setMessage("¿Qué desea hacer con\n" +
                        "Empleado: ${asignacion.nombreempleado}\n" +
                        "Area: ${asignacion.areatrabajo}\n" +
                        "Fecha: ${asignacion.fecha}\n" +
                        "Código: ${asignacion.codigobarras}?")
                .setNegativeButton("Eliminar"){ d, i ->
                    asignacion.eliminar()
                    mostrarDatosLista()
                }.setPositiveButton("Actualizar"){ d, i ->
                    var otraVentana = Intent(activity, AsignationEdit::class.java)
                    otraVentana.putExtra("idasig", asignacion.idasignacion)
                    startActivity(otraVentana)
                }.setNeutralButton("Cerrar"){ d, i -> }
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