package com.example.androidbasesdedatos

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var editTextCodigo: EditText
    private lateinit var editTextDescripcion: EditText
    private lateinit var editTextPrecio: EditText
    private lateinit var botonAlta: Button
    private lateinit var botonConsulta: Button
    private lateinit var botonBaja: Button
    private lateinit var botonModificacion: Button
    private lateinit var botonLimpiar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextCodigo = findViewById(R.id.editTextCodigo)
        editTextDescripcion = findViewById(R.id.editTextDescripcion)
        editTextPrecio = findViewById(R.id.editTextPrecio)
        botonAlta = findViewById(R.id.botonAlta)
        botonConsulta = findViewById(R.id.botonConsulta)
        botonBaja = findViewById(R.id.botonBaja)
        botonModificacion = findViewById(R.id.botonModificacion)
        botonLimpiar = findViewById(R.id.botonLimpiar)

        botonAlta.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues().apply {
                put("codigo", editTextCodigo.text.toString())
                put("descripcion", editTextDescripcion.text.toString())
                put("precio", editTextPrecio.text.toString())
            }
            bd.insert("articulos", null, registro)
            bd.close()
            limpiarCampos()
            Toast.makeText(this, "Se cargaron los datos del artículo", Toast.LENGTH_SHORT).show()
        }

        botonConsulta.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val fila = bd.rawQuery("select descripcion,precio from articulos where codigo=${editTextCodigo.text.toString()}", null)
            if (fila.moveToFirst()) {
                editTextDescripcion.setText(fila.getString(0))
                editTextPrecio.setText(fila.getString(1))
            } else {
                Toast.makeText(this, "No existe un artículo con el código ingresado", Toast.LENGTH_SHORT).show()
            }
            bd.close()
        }

        botonBaja.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val cant = bd.delete("articulos", "codigo=${editTextCodigo.text.toString()}", null)
            bd.close()
            limpiarCampos()
            if (cant == 1) {
                Toast.makeText(this, "Se borró el artículo con ese código", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No existe un artículo con el código ingresado", Toast.LENGTH_SHORT).show()
            }
        }

        botonModificacion.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "administracion", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues().apply {
                put("descripcion", editTextDescripcion.text.toString())
                put("precio", editTextPrecio.text.toString())
            }
            val cant = bd.update("articulos", registro, "codigo=${editTextCodigo.text.toString()}", null)
            bd.close()
            if (cant == 1) {
                Toast.makeText(this, "Se modificaron los datos", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "No existe un artículo con el código ingresado", Toast.LENGTH_SHORT).show()
            }
        }

        botonLimpiar.setOnClickListener {
            limpiarCampos()
        }
    }

    private fun limpiarCampos() {
        editTextCodigo.setText("")
        editTextDescripcion.setText("")
        editTextPrecio.setText("")
    }
}