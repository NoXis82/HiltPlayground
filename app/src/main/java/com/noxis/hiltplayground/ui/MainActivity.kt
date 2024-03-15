package com.noxis.hiltplayground.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.noxis.hiltplayground.R
import com.noxis.hiltplayground.model.Blog
import com.noxis.hiltplayground.until.DataState
import com.noxis.hiltplayground.viewmodel.MainStateEvent
import com.noxis.hiltplayground.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private var text: TextView? = null
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        subscribeObserver()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)
    }

    private fun init() {
        text = findViewById(R.id.text)
        progressBar = findViewById(R.id.progress_bar)
    }

    private fun subscribeObserver() {
        viewModel.dataState.observe(this) { dataState ->
            when (dataState) {
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    appendBlogTitles(dataState.data)
                }

                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }

                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        }
    }

    private fun displayError(message: String?) {
        text?.text = message ?: "Unknown error"
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        progressBar?.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun appendBlogTitles(blogs: List<Blog>) {
        text?.text = buildString {
            blogs.forEach {
                this.append(it.title)
                this.append("\n")
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}