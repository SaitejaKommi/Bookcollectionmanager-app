/*
 * Student ID: 2024eb02437
 */

package com.example.bookcollection

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookcollection.databinding.ActivityBookListBinding

class BookListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookListBinding
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var adapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        setupToolbar()
        setupRecyclerView()
        loadBooks()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { finish() }
    }

    private fun setupRecyclerView() {
        adapter = BookAdapter(emptyList())
        binding.rvBooks.apply {
            layoutManager = LinearLayoutManager(this@BookListActivity)
            adapter = this@BookListActivity.adapter
        }
    }

    private fun loadBooks() {
        val books = databaseHelper.getAllBooks()
        adapter.updateBooks(books)
        if (books.isEmpty()) {
            binding.emptyState.visibility = android.view.View.VISIBLE
            binding.rvBooks.visibility = android.view.View.GONE
        } else {
            binding.emptyState.visibility = android.view.View.GONE
            binding.rvBooks.visibility = android.view.View.VISIBLE
        }
    }
}
