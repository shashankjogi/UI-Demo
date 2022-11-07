package com.happiestminds.uidemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.PopupMenu
import androidx.constraintlayout.widget.ConstraintLayout

class MenuDemoActivity : AppCompatActivity() {

    lateinit var demoPopup: PopupMenu
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_demo)

       val btn1 = findViewById<Button>(R.id.button2)
       registerForContextMenu(btn1)

//        findViewById<TextView>(R.id.menuDemo).setOnHoverListener{
//            View


        demoPopUpMenu()

        findViewById<ConstraintLayout>(R.id.layout0).setOnLongClickListener() {
            demoPopup.show()
            true

        }


    }

    private fun demoPopUpMenu() {
        val demoButton = findViewById<Button>(R.id.demo0)
        demoPopup = PopupMenu(this, demoButton)

        demoPopup.menu.add("Radio button")
        demoPopup.menu.add("Check box")
        demoPopup.menu.add("List view")
        demoPopup.menu.add("Dialog")
        demoPopup.menu.add("Notification")

        demoPopup.setOnMenuItemClickListener {
            when (it.title) {
                "Notification" -> {
                    val notificationIntent = Intent(this,NotificationActivity().javaClass)
                    startActivity(notificationIntent)
                    Toast.makeText(this, "Notification selected..", Toast.LENGTH_LONG).show()
                }
                else -> {
                    Toast.makeText(this, "${it.title} is selected", Toast.LENGTH_LONG).show()
                }
            }
            true
        }
    }


    private val MENU_OPTION1 = 1
    private val MENU_OPTION2 = 2
    private val MENU_OPTION3 = 3

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenuInfo?
    )
    {
        menu?.add(0,MENU_OPTION1,0,"OPTION 1")
        menu?.add(0,MENU_OPTION2,0,"OPTION 2")
        menu?.add(0,MENU_OPTION3,0,"OPTION 3")

        val opt4 = menu?.addSubMenu("OPTION 4")
        opt4?.add("connect")
        opt4?.add("disconnect")
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
    when(item.itemId){
        MENU_OPTION1->Toast.makeText(this,"option 1 is selcted",Toast.LENGTH_LONG).show()
        MENU_OPTION2->Toast.makeText(this,"option 2 is selcted",Toast.LENGTH_LONG).show()
        MENU_OPTION3->Toast.makeText(this,"option 3 is selcted",Toast.LENGTH_LONG).show()
    }

        return super.onContextItemSelected(item)
    }
    private val MENU_SETTINGS=1
    private val MENU_CANCEL=2
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //design the menu by few meny items
        menu?.add(0,MENU_SETTINGS,0,"Settings")
        menu?.add(0,MENU_CANCEL,1,"Cancel" )


        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            MENU_SETTINGS->{
                Toast.makeText(this,"settings to be displayed",Toast.LENGTH_LONG).show()

            }
            MENU_CANCEL->{
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun demClickPopup(view: View){

        demoPopup.show()

    }



}

