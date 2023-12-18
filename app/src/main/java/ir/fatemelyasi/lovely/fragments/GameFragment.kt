package ir.fatemelyasi.lovely.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ir.fatemelyasi.lovely.MyViewPagerAdapter
import ir.fatemelyasi.lovely.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentGameBinding.inflate(layoutInflater,container , false)

        val myAdapter = MyViewPagerAdapter(fragment = this)
        binding.viewPagerMain.adapter = myAdapter
        binding.viewPagerMain.offscreenPageLimit = 2

        val mediator = TabLayoutMediator(
            binding.tabLayoutMain,
            binding.viewPagerMain,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(p0: TabLayout.Tab, p1: Int) {
                    when (p1) {

                        0 -> {
                            p0.text = "LoveCalculate"
                        }

                        1 -> {
                            p0.text = "GenderGuesser"
                        }
                    }
                }

            })
        mediator.attach()


        return binding.root
    }

}