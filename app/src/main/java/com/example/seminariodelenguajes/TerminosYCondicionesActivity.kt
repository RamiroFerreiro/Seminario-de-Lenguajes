package com.example.seminariodelenguajes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class TerminosYCondicionesActivity : AppCompatActivity() {

    lateinit var botonAceptarTerminos: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terminos_ycondiciones)


        botonAceptarTerminos = findViewById(R.id.btnAceptarTerminos)

        botonAceptarTerminos.setOnClickListener {
            val intentRegistro = Intent(this, RegistroActivity::class.java)
            startActivity(intentRegistro)
            finish()
        }
    }
}