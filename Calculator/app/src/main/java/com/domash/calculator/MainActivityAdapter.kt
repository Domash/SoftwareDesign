package com.domash.calculator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.domash.calculator.Fragments.BaseKeyboardFragment
import com.domash.calculator.Fragments.ScienceKeyboardFragment

class MainActivityAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return BaseKeyboardFragment()
            }
            1 -> {
                return ScienceKeyboardFragment()
            }
        }
        return BaseKeyboardFragment()
    }

}