package ir.fatemelyasi.lovely.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.JsonObject
import ir.fatemelyasi.lovely.R
import ir.fatemelyasi.lovely.databinding.FragmentGenderGuesserBinding
import org.json.JSONObject


class GenderGuesserFragment : Fragment() {
    lateinit var binding: FragmentGenderGuesserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGenderGuesserBinding.inflate(layoutInflater, container, false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnCalculateGender.setOnClickListener {

        }
    }

}