/*
 * Student ID: 2024eb02437
 */

package com.example.bookcollection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookcollection.databinding.ItemBookBinding

class BookAdapter(
    private var books: List<Book>
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    fun updateBooks(books: List<Book>) {
        this.books = books
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size

    class BookViewHolder(
        private val binding: ItemBookBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.tvBookTitle.text = book.title
            binding.tvBookAuthor.text = book.author
        }
    }
}
