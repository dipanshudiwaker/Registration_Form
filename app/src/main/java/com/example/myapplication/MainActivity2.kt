package com.example.myapplication

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val message1 = intent.getStringExtra("Name")
        val messageTextView1: TextView = findViewById(R.id.tex1)
        messageTextView1.text = message1

        val message2 = intent.getStringExtra("Email")
        val messageTextView2: TextView = findViewById(R.id.tex2)
        messageTextView2.text = message2

        val message3 = intent.getStringExtra("Domain")
        val messageTextView3: TextView = findViewById(R.id.tex3)
        messageTextView3.text = message3

        val message4 = intent.getStringExtra("Programming_Lannguage")
        val messageTextView4: TextView = findViewById(R.id.tex4)
        messageTextView4.text = message4

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        return when (id) {
            R.id.Setting -> {
                Toast.makeText(this, "Under Process ", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.Exit -> {

                    val builder = AlertDialog.Builder(this)
                    builder.setTitle(R.string.dialogTitle)
                    builder.setMessage(R.string.dialogMessage)
                    builder.setIcon(android.R.drawable.ic_dialog_alert)

                    builder.setPositiveButton("Yes"){dialogInterface, which ->
                        finishAffinity()
                    }
                    builder.setNeutralButton("Cancel"){dialogInterface , which ->
                        Toast.makeText(applicationContext,"clicked cancel\n operation cancel",Toast.LENGTH_LONG).show()
                    }
                    builder.setNegativeButton("No"){dialogInterface, which ->
                        Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
                    }

                    val alertDialog: AlertDialog = builder.create()
                    alertDialog.setCancelable(false)
                    alertDialog.show()

               true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}


