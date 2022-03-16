package com.praktikum.perpus

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailBookActivity : AppCompatActivity() {
    private val listBook = ArrayList<Book>()

    companion object {
        const val EXTRA_POS = "extra_pos"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_book)

        val tvIdBuku: TextView = findViewById(R.id.tv_id_book)
        val imgCover: ImageView = findViewById(R.id.img_cover)
        val tvTitle: TextView = findViewById(R.id.tv_title)
        val tvISBN: TextView = findViewById(R.id.tv_isbn)
        val tvPenerbit: TextView = findViewById(R.id.tv_penerbit)
        val tvPengarang: TextView = findViewById(R.id.tv_pengarang)
        val tvTahun: TextView = findViewById(R.id.tv_tahun_buku)

        val pos = intent.getIntExtra(EXTRA_POS, 0)

        RetrofitClient.instance.getBook().enqueue(object: Callback<ArrayList<Book>> {
            override fun onResponse(call: Call<ArrayList<Book>>, response: Response<ArrayList<Book>>) {
                response.body()?.let { listBook.addAll(it) }
                val book = listBook[pos]

                imgCover.setImageResource(R.drawable.ic_book)
                tvIdBuku.text = book.buku_id
                tvTitle.text = book.title
                tvISBN.text = book.isbn
                tvPenerbit.text = book.penerbit
                tvPengarang.text = book.pengarang
                tvTahun.text = book.thn_buku

                Log.d("Retrofit Get", "Buku yg dipilih: " + book.title)
            }

            override fun onFailure(call: Call<ArrayList<Book>>, t: Throwable) {
                Log.e("Retrofit Get", t.toString());
            }
        })

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}