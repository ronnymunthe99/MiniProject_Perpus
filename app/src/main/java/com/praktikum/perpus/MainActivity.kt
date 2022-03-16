package com.praktikum.perpus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        val btnListBook : Button = findViewById(R.id.btn_see_book)
        btnListBook.setOnClickListener(this)

        val btnAddBook : Button = findViewById(R.id.btn_add_book)
        btnAddBook.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_see_book -> {
                val listBookIntent = Intent(this, ListBookActivity::class.java)
                startActivity(listBookIntent)
            }

            R.id.btn_add_book -> {
                val addBooKIntent = Intent(this, AddBookActivity::class.java)
                startActivity(addBooKIntent)
            }
        }
    }


}