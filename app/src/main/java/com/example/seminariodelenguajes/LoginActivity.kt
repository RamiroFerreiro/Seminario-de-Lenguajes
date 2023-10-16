package com.example.seminariodelenguajes

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.lifecycleScope
import com.example.seminariodelenguajes.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {



    private val canalNombre = "Ramiro"
    private val canalId = "canalId"
    private val notificationiD = 0

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

                Toast.makeText(this@LoginActivity,"Faltan Datos", Toast.LENGTH_SHORT).show()
            }else{


                if(cbRecordarUsuario.isChecked){
                    var preferencias = getSharedPreferences(resources.getString((R.string.sp_credenciales)), MODE_PRIVATE)
                    preferencias.edit().putString(resources.getString(R.string.nombre_usuario), usuario).apply()
                    preferencias.edit().putString(resources.getString(R.string.password_usuario), contraseña).apply()

                    crearCanalNotificacion()
                    crearNotificacion()

                }



                val checkUsuario = loginCorrutina(usuario) // devuelve un objeto de tipo Usuario

                if (checkUsuario == null){
                    Toast.makeText(this@LoginActivity, "Usuario no registrado", Toast.LENGTH_SHORT).show()
                }else{
                    if (!checkUsuario.contraseña.equals(contraseña)) { // valida el atributo contraseña del objeto Usuario, definido en Usuario.kt
                        Toast.makeText(this@LoginActivity, "contraseña incorrecta", Toast.LENGTH_SHORT).show()
                    }else{
                        startActivity(usuario);
                    }
                }
            }
        }





    }

    private fun loginCorrutina(usuario: String): Usuario? {
        var checkUser: Usuario?
        runBlocking(Dispatchers.IO) {
            checkUser = AppDataBase.getDatabase(this@LoginActivity).usuarioDao().getNombre(usuario)
        }
        return checkUser
    }



    private fun crearCanalNotificacion(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val canalImportancia = NotificationManager.IMPORTANCE_HIGH
            val canal= NotificationChannel(canalId, canalNombre, canalImportancia)

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(canal)

        }
    }

    private fun crearNotificacion(){

        val resultIntent = Intent(applicationContext, MainActivity::class.java)
        val resultPendingIntent = TaskStackBuilder.create(applicationContext).run{

            addNextIntentWithParentStack(resultIntent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }


        val notificacion= NotificationCompat.Builder(this, canalId).also{
            it.setContentTitle("USUARIO GUARDADO")
            it.setContentText("La proxima vez no tendrás que iniciar sesión")
            it.setSmallIcon(R.drawable.icon_mensaje)
            it.priority = NotificationCompat.PRIORITY_HIGH
            it.setContentIntent(resultPendingIntent)
            it.setAutoCancel(true)

        }.build()

        val notificationManager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        notificationManager.notify(notificationiD, notificacion)
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