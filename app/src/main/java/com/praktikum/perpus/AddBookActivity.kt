package com.praktikum.perpus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddBookActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtIdBook: EditText
    private lateinit var edtTitle: EditText
    private lateinit var edtIsbn: EditText
    private lateinit var edtPenerbit: EditText
    private lateinit var edtPengarang: EditText
    private lateinit var edtTahun: EditText
    private lateinit var btnTambah: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)

        edtIdBook = findViewById(R.id.edt_id_book)
        edtTitle = findViewById(R.id.edt_title_book)
        edtIsbn = findViewById(R.id.edt_isbn_book)
        edtPenerbit = findViewById(R.id.edt_penerbit_book)
        edtPengarang = findViewById(R.id.edt_pengarang_book)
        edtTahun = findViewById(R.id.edt_tahun_book)
        btnTambah = findViewById(R.id.btn_add_book)

        btnTambah.setOnClickListener(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_add_book) {
            createBook()
        }
    }

    private fun createBook() {
        val inputIdBook = edtIdBook.text.toString()
        val inputTitle = edtTitle.text.toString()
        val inputIsbn = edtIsbn.text.toString()
        val inputPenerbit = edtPenerbit.text.toString()
        val inputPengarang = edtPengarang.text.toString()
        val inputTahun = edtTahun.text.toString()

        if (inputIdBook.equals("") || inputTitle.equals("") || inputIsbn.equals("") ||
            inputPenerbit.equals("") || inputPengarang.equals("") || inputTahun.equals("")) {
            Toast.makeText(this, "Semua data harus diisi!", Toast.LENGTH_SHORT).show()
        } else {
            RetrofitClient.instance.createBook(
                0,
                buku_id = inputIdBook,
                1,
                1,
                title = inputTitle,
                isbn = inputIsbn,
                penerbit = inputPenerbit,
                pengarang = inputPengarang,
                thn_buku = inputTahun,
                13
            ).enqueue(object : Callback<Book> {
                override fun onResponse(call: Call<Book>, response: Response<Book>) {
                    Toast.makeText(this@AddBookActivity, "${inputTitle} berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                    Log.d("Retrofit Post", "Buku ${inputTitle} berhasil ditambahkan")
                }

                override fun onFailure(call: Call<Book>, t: Throwable) {
                    Toast.makeText(this@AddBookActivity, "Tambah buku gagal!", Toast.LENGTH_SHORT).show()
                    Log.e("Retrofit Post", t.toString())
                }
            })
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return true
    }

}