package com.example.seminariodelenguajes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import android.view.Menu
import android.content.Intent


class ListadoSuperheroesActivity : AppCompatActivity() {
    lateinit var rvSuperheroes: RecyclerView
    lateinit var superheroesAdapter: SuperheroeAdapter
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_superheroes)


        rvSuperheroes = findViewById(R.id.rvSupeheroes)
        superheroesAdapter = SuperheroeAdapter(getSuperheroe(), this)
        rvSuperheroes.adapter =superheroesAdapter

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = resources.getString(R.string.tituloAB)




    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_listado_superheroes, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.item_volver){
            val intent = Intent(this, MainActivity ::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }


    private fun getSuperheroe(): MutableList<Superheroe>{
        var superheroes: MutableList<Superheroe> = ArrayList()

        superheroes.add(Superheroe(1, "HULK"))
        superheroes.add(Superheroe(2, "THOR"))
        superheroes.add(Superheroe(3, "VIUDA NEGRA"))
        superheroes.add(Superheroe(4, "CAPITÁN AMERICA"))
        superheroes.add(Superheroe(5, "OJO DE HALCÓN"))
        superheroes.add(Superheroe(6, "IRON MAN"))


        return superheroes
    }
}