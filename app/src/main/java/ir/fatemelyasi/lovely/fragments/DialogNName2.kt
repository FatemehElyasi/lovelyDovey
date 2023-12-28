//package ir.fatemelyasi.lovely.fragments
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import ir.fatemelyasi.lovely.R
//import ir.fatemelyasi.lovely.databinding.DialogFragmentNameBinding
//
//
//const val KEY_SEND_DATA = "NAME_USER"
//
//class DialogNName2 : Fragment() {
//
//    lateinit var binding: DialogFragmentNameBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View? {
//        binding = DialogFragmentNameBinding.inflate(layoutInflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val name = binding.editTextNameDialog.text.toString()
//
//
//        var bundle = Bundle()
//        bundle.putString(KEY_SEND_DATA, "$name")
//
//        val fragment=MainFragment()
//        fragment.arguments=bundle
//
//        if (bundle != null) {
//
////            val myText = bundle.getString(KEY_SEND_DATA)
////            binding.txtShowName.text = myText
//
//
//        } else {
//
//
//        }
//
//        binding.btnok.setOnClickListener {
//            //way33
//            // load second fragment in first fragment :
//            val transaction = childFragmentManager.beginTransaction()
//            transaction.add(R.id.fragmentContainerView, fragment)
//            transaction.addToBackStack(null)
//            transaction.commit()
//        }
//    }
//
//}
//
//
//
//
