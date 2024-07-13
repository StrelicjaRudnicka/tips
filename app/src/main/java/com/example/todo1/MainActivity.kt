package com.example.todo1

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var editText1: EditText
    private lateinit var checkBox: CheckBox
    private lateinit var checkBox1: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)
        editText1 = findViewById(R.id.editText1)
        checkBox = findViewById(R.id.checkBox)
        checkBox1 = findViewById(R.id.checkBox1)
        // tutaj dac cos co sprawia, ze ten durny tekst znika XD
        editText.alpha = 1.0f
        editText1.alpha = 1.0f



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}