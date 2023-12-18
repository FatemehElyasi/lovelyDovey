package ir.fatemelyasi.lovely.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.fatemelyasi.lovely.R
import ir.fatemelyasi.lovely.databinding.FragmentLoveCalculateBinding


class LoveCalculateFragment : Fragment() {

    private lateinit var binding: FragmentLoveCalculateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoveCalculateBinding.inflate(layoutInflater,container , false)

        return binding.root
    }

}