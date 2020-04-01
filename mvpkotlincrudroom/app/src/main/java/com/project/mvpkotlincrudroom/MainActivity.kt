package com.project.mvpkotlincrudroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.project.mvpkotlincrudroom.ui.Createuser.CreateUser
import com.project.mvpkotlincrudroom.ui.Readuser.ReadUser

class MainActivity : AppCompatActivity() {

    lateinit var tambahuser : Button
    lateinit var lihatlistuser : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tambahuser = findViewById(R.id.tambahuser)
        lihatlistuser = findViewById(R.id.lihatlistusers)

        tambahuser.setOnClickListener {view ->
            val intent = Intent(this, CreateUser::class.java)
            startActivity(intent)
        }

        lihatlistuser.setOnClickListener { view ->
            val intent = Intent(this, ReadUser::class.java)
            startActivity(intent)
        }

    }
}
