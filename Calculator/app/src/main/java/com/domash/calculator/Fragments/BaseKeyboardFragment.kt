package com.domash.calculator.Fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.domash.calculator.MainActivity
import com.domash.calculator.R

class BaseKeyboardFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.base_keyboard_fragment, container, false)

        view.getAllViews()
            .filterIsInstance<Button>()
            .forEach { it.setOnClickListener(activity as MainActivity?) }


        return view

    }

    companion object {

        fun View.getAllViews(): List<View> {

            if (this !is ViewGroup || childCount == 0) return listOf(this)

            return children.toList()
                           .flatMap { it.getAllViews() }
                           .plus(this as View)

        }

    }

}
