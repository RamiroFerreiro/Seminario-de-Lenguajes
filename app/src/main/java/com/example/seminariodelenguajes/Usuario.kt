package com.example.seminariodelenguajes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios_tabla")
data class Usuario(
    @ColumnInfo(name = "nombre")var nombre:String,
    @ColumnInfo(name = "contraseña")var contraseña:String){

    @PrimaryKey(autoGenerate = true) var id:Int = 0;

}