package com.domash.calculator

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import androidx.viewpager.widget.ViewPager
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.jetbrains.annotations.NotNull
import com.domash.calculator.Fragments.BaseKeyboardFragment
import com.domash.calculator.Fragments.ScienceKeyboardFragment
import org.mariuszgromada.math.mxparser.Expression


class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        val functions = listOf("sin", "cos", "tan", "ctg", "ln", "log2", "log10", "sqrt")
    }

    private lateinit var textExpression: TextView
    private lateinit var keyboardViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when(BuildConfig.FLAVOR) {
            "free" -> onCreateFreeVersion()
            "paid" -> onCreatePaidVersion()
        }

        textExpression = findViewById(R.id.expression)
        textExpression.movementMethod = ScrollingMovementMethod()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("expression_text", textExpression.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textExpression.text = savedInstanceState.getString("expression_text")
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
        when(v.id) {
            R.id.res_op -> onSolve()
            R.id.del_op -> onDelete(1)
            R.id.clear_button -> onDelete(textExpression.text.length)
            else -> onAppend((v as Button).text.toString())
        }
    }

    private fun onSolve() {

        if(textExpression.text.isNotEmpty()) {

            val expr = Expression(textExpression.text.toString())

            if(expr.checkSyntax()) {
                textExpression.text = expr.calculate().toString()
            } else {
                Log.i("Syntax error", expr.errorMessage)
                Toast.makeText(baseContext, "Bad expression, try again!", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun onDelete(n: Int) {
        if(textExpression.text.isNotEmpty()) {
            textExpression.text = textExpression.text.dropLast(n)
        }
    }

    private fun onAppend(operation: String) {
        if(functions.contains(operation)) {
            textExpression.append("$operation(")
        } else {
            textExpression.append(operation)
        }
    }

}
