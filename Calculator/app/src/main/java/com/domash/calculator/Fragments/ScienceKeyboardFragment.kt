package com.domash.calculator.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.domash.calculator.R

import com.domash.calculator.Fragments.BaseKeyboardFragment.Companion.getAllViews
import com.domash.calculator.MainActivity

class ScienceKeyboardFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.science_keyboard_fragment, container, false)

        view.getAllViews()
            .filterIsInstance<Button>()
            .forEach { it.setOnClickListener(activity as MainActivity?) }

        return view
    }
}