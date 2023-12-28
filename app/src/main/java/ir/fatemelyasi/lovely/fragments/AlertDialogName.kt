package ir.fatemelyasi.lovely.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import ir.fatemelyasi.lovely.R
import ir.fatemelyasi.lovely.databinding.DialogFragmentNameBinding

const val KEY_SEND_DATA = "NAME_USER"
class AlertDialogName : DialogFragment() {

    private lateinit var binding: DialogFragmentNameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DialogFragmentNameBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {


            val builder = AlertDialog.Builder(it)
            // Get the layout inflater.
            val inflater = requireActivity().layoutInflater;

            // Inflate and set the layout for the dialog.
            // Pass null as the parent view because it's going in the dialog
            // layout.
            builder.setView(inflater.inflate(R.layout.dialog_fragment_name, null))
                // Add action buttons.
                .setPositiveButton("ok",
                    DialogInterface.OnClickListener { dialog, id ->

                        val name = binding.editTextNameDialog.text.toString()
                        if (name.isNotEmpty()) {
                            val bundle = Bundle()
                            bundle.putString(KEY_SEND_DATA,name)

                            val fragment = MainFragment()
                            fragment.arguments = bundle

                        } else {
                            Toast.makeText(context, "enter your name", Toast.LENGTH_SHORT).show()
                        }

                    })
                .setNegativeButton("cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        getDialog()?.cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
