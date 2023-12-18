package ir.fatemelyasi.lovely

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ir.fatemelyasi.lovely.fragments.GenderGuesserFragment
import ir.fatemelyasi.lovely.fragments.LoveCalculateFragment

class MyViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                LoveCalculateFragment()
            }

            1 -> {
                GenderGuesserFragment()
            }

            else -> {
                Fragment()
            }
        }

    }
}