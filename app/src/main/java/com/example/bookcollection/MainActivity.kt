/*
 * Student ID: 2024eb02437
 */

package com.example.bookcollection

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookcollection.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseHelper = DatabaseHelper(this)

        binding.btnSaveBook.setOnClickListener { saveBook() }
        binding.btnViewBooks.setOnClickListener { viewBooks() }
    }

    private fun saveBook() {
        val title = binding.etBookTitle.text.toString().trim()
        val author = binding.etAuthorName.text.toString().trim()

        if (title.isEmpty()) {
            binding.tilTitle.error = "Book title is required"
            binding.etBookTitle.requestFocus()
            return
        }
        if (author.isEmpty()) {
            binding.tilAuthor.error = "Author name is required"
            binding.etAuthorName.requestFocus()
            return
        }

        binding.tilTitle.error = null
        binding.tilAuthor.error = null

        val result = databaseHelper.insertBook(title, author)
        if (result > 0) {
            Toast.makeText(this, "Book saved successfully!", Toast.LENGTH_SHORT).show()
            binding.etBookTitle.text?.clear()
            binding.etAuthorName.text?.clear()
        } else {
            Toast.makeText(this, "Failed to save book", Toast.LENGTH_SHORT).show()
        }
    }

    private fun viewBooks() {
        val intent = Intent(this, BookListActivity::class.java)
        startActivity(intent)
    }
}
