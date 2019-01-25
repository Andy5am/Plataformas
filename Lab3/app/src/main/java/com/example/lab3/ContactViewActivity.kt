package com.example.lab3

import android.Manifest
import android.Manifest.permission.*
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Button
import android.widget.TextView
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


        //No funciona el permission, no esta usando lo que se importo
        /**currentNum.setOnClickListener(View.OnClickListener {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:${currentNum.text}")

            if (ActivityCompat.checkSelfPermission(
                    this@ContactViewActivity,
                    CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return@OnClickListener
            }
            startActivity(callIntent)
        })**/
        currentNum.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:"+currentNum.text.toString())
            startActivity(intent)
        }



        currentMail.setOnClickListener { val intent = Intent(this, MailActivity::class.java)
        startActivity(intent)}


    }

    /**protected fun sendEmail() {
        Log.i("Send email", "")
        val TO = arrayOf("")
        val CC = arrayOf("")
        val emailIntent = Intent(Intent.ACTION_SEND)

        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO)
        emailIntent.putExtra(Intent.EXTRA_CC, CC)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject")
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here")

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."))
            finish()
            Log.i("Finished sending email.", "")
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(this@ContactViewActivity, "There is no email client installed.", Toast.LENGTH_SHORT).show()
        }

    }**/

}
