package com.domash.calculator

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager.widget.ViewPager
import android.view.WindowManager
import org.jetbrains.annotations.NotNull
import com.domash.calculator.Fragments.BaseKeyboardFragment
import com.domash.calculator.Fragments.ScienceKeyboardFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.mariuszgromada.math.mxparser.Expression


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var keyboardViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when(BuildConfig.FLAVOR) {
            "free" -> onCreateFreeVersion()
            "paid" -> onCreatePaidVersion()
        }

    }

    private fun onCreateFreeVersion() {

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.base_frame, BaseKeyboardFragment()).commit()

    }

    private fun onCreatePaidVersion() {

        val display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay

        if(display.orientation % 2 == 0) {
            keyboardViewPager = findViewById(R.id.keyboards_vp)
            keyboardViewPager.adapter = MainActivityAdapter(supportFragmentManager)
        } else {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.base_frame, BaseKeyboardFragment())
                .add(R.id.science_frame, ScienceKeyboardFragment()).commit()
        }

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.i("SWAP_ORIENTATION", "TRUE")
    }

    @NotNull
    override fun onClick(v: View) {

        val expr = Expression("10 * 10")
        expression.text = expr.calculate().toString()

    }

}
