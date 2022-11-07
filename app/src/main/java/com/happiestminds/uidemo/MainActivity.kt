package com.happiestminds.uidemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.PopupMenu
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timer().schedule(10000,){
            runOnUiThread(){
                val i = Intent(this@MainActivity,MenuDemoActivity().javaClass)
                startActivity(i)
            }
        }
    }

    fun continueClick(view: View) {
        Toast.makeText(this,"continuing...",Toast.LENGTH_LONG).show()


        val menuDemoIntent= Intent(this,MenuDemoActivity().javaClass)
        startActivity(menuDemoIntent)
    }

    private val MENU_EXIT=12
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //design the menu by few meny items
        menu?.add(0,MENU_EXIT,0,"Exit")



        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }


}
