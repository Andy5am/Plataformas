package com.example.lab3

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Button
import android.widget.TextView
import java.util.jar.Manifest
import android.Manifest.permission
import android.Manifest.permission.CALL_PHONE
import android.R.attr.button
import android.view.View


class ContactViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_view)

        val contactos = (this.application as MyApplication).getContacts()

        val currentName = findViewById<TextView>(R.id.nameText)
        val currentNum = findViewById<TextView>(R.id.phoneText)
        val currentMail = findViewById<TextView>(R.id.mailText)

        currentName.text = contactos.currentContact.nombre
        currentMail.text = contactos.currentContact.correo
        currentNum.text = contactos.currentContact.telefono

        val botonRegresar = findViewById<Button>(R.id.buttonBack)
        botonRegresar.setOnClickListener { val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)}

        val botonllamar = findViewById<Button>(R.id.buttonLlamada)

        botonllamar.setOnClickListener(View.OnClickListener {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:0377778888")

            if (ActivityCompat.checkSelfPermission(
                    this@ContactViewActivity,
                    android.Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return@OnClickListener
            }
            startActivity(callIntent)
        })
    }
}
