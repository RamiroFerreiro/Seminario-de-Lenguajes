package com.example.seminariodelenguajes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegistroActivity : AppCompatActivity() {

    lateinit var entradaCrearUsuario: EditText
    lateinit var entradaCrearContraseña: EditText
    lateinit var entradaRepetirConstraseña: EditText
    lateinit var botonRegistrarse: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        entradaCrearUsuario = findViewById(R.id.editCrearUsuario)
        entradaCrearContraseña = findViewById(R.id.editCrearContraseña)
        entradaRepetirConstraseña = findViewById(R.id.editRepetirContraseña)
        botonRegistrarse = findViewById(R.id.registrarse)

        botonRegistrarse.setOnClickListener {
            var nuevoUsuario = entradaCrearUsuario.text.toString()
            var contraseña1 =  entradaCrearContraseña.text.toString()
            var constraseña2 = entradaRepetirConstraseña.text.toString()

            if(nuevoUsuario.isEmpty() || contraseña1.isEmpty() || constraseña2.isEmpty()){
                Toast.makeText(this,"Faltan Datos", Toast.LENGTH_SHORT).show()
            }else{
                if(contraseña1 != constraseña2){
                    Toast.makeText(this,"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }else{


                    //var nuevoUsuario = Usuario(nuevoUsuario,contraseña1)
                    //AppDataBase.getDatabase(this).usuarioDao().insertUsuario(nuevoUsuario)

                    val intentLogin = Intent(this, LoginActivity::class.java)
                    startActivity(intentLogin)
                    finish()

                }
            }
        }
    }
}