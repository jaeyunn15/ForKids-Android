package nh.hackaton.forkids.ui.activity

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.DisplayMetrics
import android.view.KeyEvent
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.*
import nh.hackaton.forkids.R
import kotlin.random.Random
import kotlin.collections.ArrayList

class CustomButtonActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var flipper: ViewFlipper
    private lateinit var tableLayout: TableLayout
    private lateinit var textView : TextView
    private val mActivity: Activity? = this
    private var currentText : StringBuilder = StringBuilder()
    private lateinit var imm: InputMethodManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_button)

        imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val btnWidth: Int = getBtnWidth()
        flipper = findViewById<ViewFlipper>(R.id.viewFlipper).apply {
            visibility = View.VISIBLE
            val inAni = AnimationUtils.loadAnimation(mActivity, R.anim.in_animation)
            inAni.interpolator = AccelerateInterpolator()
            inAnimation = inAni
            val outAni = AnimationUtils.loadAnimation(mActivity, R.anim.out_animation)
            outAni.interpolator = DecelerateInterpolator()
            outAnimation = outAni
        }

        tableLayout = findViewById<TableLayout>(R.id.tableLayout)
        textView = findViewById<TextView>(R.id.text_view)
        val emptyView = TextView(this)
        flipper.addView(emptyView, 0)

        editText = findViewById<EditText>(R.id.edit_text).apply {
            setOnClickListener { v: View ->
                imm.hideSoftInputFromWindow(editText.windowToken, 0)
                if (flipper.currentView.id != R.id.firstViewFlipper) {
                    reOrderKeyboard(btnWidth)
                    flipper.visibility = View.VISIBLE
                    flipper.displayedChild = 1 }
            }
        }

        imm.hideSoftInputFromWindow(editText.windowToken, 0)

        editText.requestFocus()
        findViewById<Button>(R.id.key_ok).apply {
            setOnClickListener{ v : View ->
                if (flipper.currentView.id == R.id.firstViewFlipper)
                { flipper.displayedChild = 0
                    var showtxt = StringBuilder(textView.text)
                    showtxt.append("\n"+currentText)
                    textView.text = showtxt.toString()
                    Toast.makeText(applicationContext, showtxt.toString(), Toast.LENGTH_SHORT).show()
                    currentText.clear()
                    editText.text.clear()
                }
            }
        }


        findViewById<Button>(R.id.key_cancel).apply {
            setOnClickListener{ v : View ->
                val curIndex = editText.selectionStart
                var passwordStr = editText.text.toString()
                var passWordLength = passwordStr.length
                if (curIndex == 0 || passWordLength == 0) {
                    return@setOnClickListener
                }
                passwordStr.apply {
                    passwordStr = substring(0,curIndex-1)+substring(curIndex,passWordLength)
                }
                currentText.apply{
                    currentText = StringBuilder(toString().substring(0,curIndex-1)+toString().substring(curIndex,passWordLength))
                }
                passWordLength = passwordStr.length
                editText.setText("")
                for( i in 1 .. passWordLength){
                    editText.append("*") }
                editText.setSelection(curIndex-1);
            }
        }
        reOrderKeyboard(btnWidth)
    }

    private fun reOrderKeyboard(btnWidth: Int) {
        val keyNumberArr = ArrayList<String>()
        for (i in 0..9) {
            keyNumberArr.add(i.toString())
        }
        var tr: TableRow? = null
        var btn: Button? = null
        var randIndx: Int = 0;
        var random = Random
        for (i in 0..(tableLayout.childCount-1)) {
            tr = tableLayout.getChildAt(i) as TableRow
            for (i in 0..(tr.childCount-1)) {
                btn = tr.getChildAt(i) as Button
                if (btn.id == -1) {
                    randIndx = random.nextInt(keyNumberArr.size)
                    btn.text = keyNumberArr[randIndx]
                    btn.width = btnWidth/3
                    val keyTxt = btn.text
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        keyNumberArr.removeIf { x ->
                            x == keyNumberArr[randIndx]
                        }
                    }
                    btn.setOnClickListener { v: View ->
                        val curIndex = editText.selectionStart
                        var passwordStr = editText.text.toString()
                        val passWordLength = passwordStr.length
                        passwordStr.apply {
                            substring(0, curIndex) + keyTxt + substring(curIndex,passWordLength)
                        }
                        currentText.append(keyTxt)
                        editText.setText("")
                        for (i in 0 until curIndex) {
                            editText.append("*") }
                        editText.append(keyTxt)
                        for (i in curIndex + 1 until passWordLength + 1) {
                            editText.append("*")
                        }
                        editText.setSelection(curIndex + 1)
                        mHandler.sendEmptyMessageDelayed(0,1000)
                    }
                }
            }
        }
    }

    private var mHandler = Handler(){ msg: Message? ->
        val curIndex = editText.selectionStart
        editText.setText("")
        for (i in 0 until curIndex) {
            editText.append("*") }
        editText.setSelection(curIndex)
        false
    }

    private fun getBtnWidth(): Int {
        val displaymetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displaymetrics)
        return displaymetrics.widthPixels
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(flipper.currentView.id == R.id.firstViewFlipper){
                flipper.setDisplayedChild(0) }
            else{
                return super.onKeyDown(keyCode, event)
            }
        }
        return true
    }



}