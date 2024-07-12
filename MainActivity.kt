package com.example.tipsystem1

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private const val TAG = "MainActivity"
private const val INITIAL_TIP_PERCENT = 15
class MainActivity : AppCompatActivity() {
    private lateinit var BaseAmount: EditText
    private lateinit var seekBarTip: SeekBar
    private lateinit var TipPercent: TextView
    private lateinit var TipAmount: TextView
    private lateinit var TotalAmount: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        BaseAmount = findViewById(R.id.BaseAmount)
        seekBarTip = findViewById(R.id.seekBarTip)
        TipPercent = findViewById(R.id.TipPercent)
        TipAmount = findViewById(R.id.TipAmount)
        TotalAmount = findViewById(R.id.TotalAmount)


        seekBarTip.progress = INITIAL_TIP_PERCENT
        TipPercent.text = "$INITIAL_TIP_PERCENT%"
        seekBarTip.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.i(TAG, "onProgressChanged $progress")
                TipPercent.text = "$progress%"
                computeTipAndTotal()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
        BaseAmount.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG,"afterTextChanged $s")
                computeTipAndTotal()
            }

        } )

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.LabelView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    @SuppressLint("SetTextI18n")
    private fun computeTipAndTotal() {
       if (BaseAmount.text.isEmpty()) {
           TipAmount.text = ""
           TotalAmount.text = ""
           return
       }
       val baseAmount = BaseAmount.text.toString().toDouble()
       val tipPercent = seekBarTip.progress
       val tipAmount = baseAmount * tipPercent / 100
       val totalAmount = baseAmount + tipAmount
       TipAmount.text = "%.2f".format(tipAmount)
       TotalAmount.text = "%.2f".format(totalAmount)
    }
}