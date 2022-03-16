package com.praktikum.perpus

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(private val listBook: ArrayList<Book>) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class BookViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        var tvBookName: TextView = itemView.findViewById(R.id.tv_book_name)
        var imgCover: ImageView = itemView.findViewById(R.id.img_book_cover)
        var tvCount: TextView = itemView.findViewById(R.id.tv_book_count)
        val btnDetail: Button = itemView.findViewById(R.id.btn_detail_book)

//        var tvText: TextView = itemView.findViewById(R.id.tv_text)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BookViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_book, viewGroup, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = listBook[position]

//        holder.tvText.text = text
//        if (book.sampul == "0") {
//            holder.imgCover.setImageResource(R.drawable.ic_book)
//        } else {
//            holder.imgCover.setImageResource(book.sampul.toInt())
//        }
        holder.tvBookName.text = book.title
        holder.tvCount.text = book.jml

        holder.btnDetail.setOnClickListener{
            onItemClickCallback.onItemClicked(listBook[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return return listBook.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Book)
    }
}