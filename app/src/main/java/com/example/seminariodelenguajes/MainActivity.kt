package com.example.seminariodelenguajes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import android.content.Intent

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.tituloAB)

        saludarUsuario()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.item_listado){
            val intentListado = Intent(this, ListadoSuperheroesActivity::class.java)
            startActivity(intentListado)
            finish()

        }
        return super.onOptionsItemSelected(item)
    }

    private fun saludarUsuario(){

        //Obtengo los datos que me enviaron
        var bundle: Bundle? = intent.extras

        //Reviso que no sea nulo y que tenga  datos
        if(bundle != null){

            //obtengo el dato especifico y lo meto en una variabel
            var usuario = bundle?.getString(resources.getString(R.string.nombre_usuario))

            //saludo al usuario que ingreso correctamente
            Toast.makeText(this,"Bienvenido $usuario", Toast.LENGTH_SHORT).show()
        }
    }
}