package com.praktikum.perpus

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListBookActivity : AppCompatActivity() {
    private lateinit var rvBook: RecyclerView
    private var list = ArrayList<Book>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_book)

        rvBook = findViewById(R.id.rv_book)
        rvBook.setHasFixedSize(true)

        showRecyclerList()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun showRecyclerList() {
        rvBook.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getBook().enqueue(object: Callback<ArrayList<Book>>{
            override fun onResponse(call: Call<ArrayList<Book>>, response: Response<ArrayList<Book>>) {
                response.body()?.let { list.addAll(it) }
                val listBookAdapter = BookAdapter(list)
                rvBook.adapter = listBookAdapter

                Log.d("Retrofit Get", "Jumlah data Buku: " + list.size.toString())

                listBookAdapter.setOnItemClickCallback(object: BookAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: Book) {
                        showSelectedBook(data)
                    }
                })
            }

            override fun onFailure(call: Call<ArrayList<Book>>, t: Throwable) {
                Log.e("Retrofit Get", t.toString());
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showSelectedBook(book: Book) {
        Toast.makeText(this, "Kamu memilih ${book.title}", Toast.LENGTH_SHORT).show()
        val moveDetailBook = Intent(this, DetailBookActivity::class.java)
        moveDetailBook.putExtra(DetailBookActivity.EXTRA_POS, list.indexOf(book))
        startActivity(moveDetailBook)
    }
}