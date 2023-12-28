//package ir.fatemelyasi.lovely

//import android.app.AlertDialog
//import android.app.Dialog
//import android.content.DialogInterface
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.fragment.app.DialogFragment
//import ir.fatemelyasi.lovely.databinding.AlertDialogNameBinding
//import ir.fatemelyasi.lovely.fragments.MainFragment
//
//const val KEY_SEND_DATA = "NAME_USER"
//
//class AlertDialogName : DialogFragment() {
//
//    private lateinit var binding: AlertDialogNameBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View? {
//        binding = AlertDialogNameBinding.inflate(inflater, container, false)
//        return binding.root
//
//    }
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return activity?.let {
//
//
//            val builder = AlertDialog.Builder(it)
//            // Get the layout inflater.
//            val inflater = requireActivity().layoutInflater;
//
//            // Inflate and set the layout for the dialog.
//            // Pass null as the parent view because it's going in the dialog
//            // layout.
//            builder.setView(inflater.inflate(R.layout.dialog_name2, null))
//                // Add action buttons.
//                .setPositiveButton("ok",
//                    DialogInterface.OnClickListener { dialog, id ->
//
//                        val enteredName = binding.editTextNameDialog.text.toString()
//
//                        if (enteredName.isNotEmpty()) {
//                            val bundle=Bundle()
//                            bundle.putString(KEY_SEND_DATA,"$enteredName")
//                            val fragment=MainFragment()
//                            fragment.arguments=bundle
//
//                            val transaction = parentFragmentManager.beginTransaction()
//                            transaction.replace(R.id.fragmentContainerView, MainFragment())
//                            transaction.addToBackStack(null)
//                            transaction.commit()
//                        } else {
//                            Toast.makeText(context, "Enter your name", Toast.LENGTH_SHORT).show()
//                        }
//
//
//                        //way1
////                        val name = binding.editTextNameDialog.text.toString()
////                        if (name.isNotEmpty()) {
////                            val bundle = Bundle()
////                            bundle.putString(KEY_SEND_DATA,name)
////
////                            val fragment = MainFragment()
////                            fragment.arguments = bundle
////
////                        } else {
////                            Toast.makeText(context, "enter your name please", Toast.LENGTH_SHORT).show()
////                        }
//
//                    })
//                .setNegativeButton("cancel",
//                    DialogInterface.OnClickListener { dialog, id ->
//                        getDialog()?.cancel()
//                    })
//            builder.create()
//        } ?: throw IllegalStateException("Activity cannot be null")
//    }
//}
