package com.example.seminariodelenguajes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.content.Intent
import android.widget.Toast

class LoginActivity : AppCompatActivity() {


    lateinit var entradaIngresarUsuario: EditText
    lateinit var entradaIngresarContraseña: EditText
    lateinit var botonIniciarSesion: Button
    lateinit var botonRegistrar: Button
    lateinit var cbRecordarUsuario: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        entradaIngresarUsuario = findViewById(R.id.editIngresarUsuario)
        entradaIngresarContraseña = findViewById(R.id.editIngresarContraseña)
        botonRegistrar= findViewById(R.id.btnRegistrar)
        botonIniciarSesion = findViewById(R.id.btnIniciarSesion)
        cbRecordarUsuario = findViewById(R.id.cbRecordar)

        var preferencias = getSharedPreferences(resources.getString((R.string.sp_credenciales)), MODE_PRIVATE)
        var usuarioGuardado= preferencias.getString(resources.getString(R.string.nombre_usuario), "")
        var passwordGuardado = preferencias.getString(resources.getString(R.string.password_usuario), "")

        if(usuarioGuardado != "" && passwordGuardado != ""){
            startActivity(usuarioGuardado!!)
        }



        botonRegistrar.setOnClickListener {
            var intentTerminos = Intent(this, TerminosYCondicionesActivity::class.java)
            startActivity(intentTerminos)
            finish()
        }


        botonIniciarSesion.setOnClickListener {
            var usuario = entradaIngresarUsuario.text.toString()
            var contraseña = entradaIngresarContraseña.text.toString()

            if(usuario.isEmpty() || contraseña.isEmpty()){

                Toast.makeText(this,"Faltan Datos", Toast.LENGTH_SHORT).show()
            }else{


                if(cbRecordarUsuario.isChecked){
                    var preferencias = getSharedPreferences(resources.getString((R.string.sp_credenciales)), MODE_PRIVATE)
                    preferencias.edit().putString(resources.getString(R.string.nombre_usuario), usuario).apply()
                    preferencias.edit().putString(resources.getString(R.string.password_usuario), contraseña).apply()

                }


                startActivity(usuario);


            }

        }
    }

    private fun startActivity(usuarioGuardado: String) {
        val intentMain = Intent(this, MainActivity::class.java)

        ///Agregamos el dato que queremos pasar a la siguiente pantalla
        intentMain.putExtra(resources.getString(R.string.nombre_usuario), usuarioGuardado)


        ///Cambiamos de pantalla y finalizamos.
        startActivity(intentMain);
        finish()
        
    }
}