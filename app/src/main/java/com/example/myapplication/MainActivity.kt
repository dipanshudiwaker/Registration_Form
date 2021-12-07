package com.example.myapplication

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.graphics.BitmapFactory
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream


class MainActivity : AppCompatActivity() {
      private var radioGroup: RadioGroup? = null
    private lateinit var radioButton: RadioButton
    lateinit var C1: CheckBox
    lateinit var C2: CheckBox
    lateinit var C3: CheckBox
    lateinit var C4: CheckBox
    private  val CAMERA_PERMISSION_CODE = 100
    val REQUEST_CODE=1
    private lateinit var  btnimage:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.title = "  Registraion | Action Bar"
        actionBar.subtitle = "  Cse Studets Form "
        actionBar.setDisplayUseLogoEnabled(true)
        actionBar.setDisplayShowHomeEnabled(true)

        btnimage=findViewById(R.id.btnimage)
        C1 = findViewById(R.id.c1)
        C2 = findViewById(R.id.c2)
        C3 = findViewById(R.id.c3)
        C4 = findViewById(R.id.c4)

        btnimage.setOnClickListener {
            checkPermission(
                Manifest.permission.CAMERA,
                CAMERA_PERMISSION_CODE)
        }

        radioGroup = findViewById(R.id.radiogroup1)
        val user=findViewById<EditText>(R.id.e1)
        val email=findViewById<EditText>(R.id.e2)
        val btnsubmit:Button=findViewById(R.id.btnsubmit)

        btnsubmit.setOnClickListener {

            val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
            radioButton = findViewById(intSelectButton)
            val result = StringBuilder()
           //result.append(radioButton)
            if (C1.isChecked) {
                result.append("\nPythone")

            }
            if (C2.isChecked) {
                result.append("\nJava")

            }
            if (C3.isChecked) {
                result.append("\n C++")

            }
            if(C4.isChecked){
                result.append("\n C")
            }
            intent= Intent(this,MainActivity2::class.java)
            intent.putExtra("Name",user.text.toString())
            intent.putExtra("Email",email.text.toString())
            intent.putExtra("Domain",radioButton.text)
           intent.putExtra("Programming_Lannguage",result.toString())
            val snack= Snackbar.make(it,"User Data is Transfered Sucessfully", Snackbar.LENGTH_LONG)
            snack.show()
            startActivity(intent)

        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
            R.id.refresh -> Toast.makeText(this, "Refresh Clicked", Toast.LENGTH_SHORT).show()

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            btnimage.setImageBitmap(data.extras?.get("data") as Bitmap)
        }
    }

    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(this@MainActivity, permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permission), requestCode)
        } else {


            val cameraIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
             startActivityForResult(cameraIntent,REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this@MainActivity, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
                val cameraIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent,REQUEST_CODE)
            } else {
                Toast.makeText(this@MainActivity, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

}


