package com.akul.news.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.akul.news.details.databinding.FragmentArticlesBinding

/**
 * A fragment representing a list of Items.
 */
class ArticlesFragment : Fragment(R.layout.fragment_articles) {

    private val articlesViewModel: ArticlesViewModel by viewModels()
    private var adapter: ArticleAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val articleAdapter = ArticleAdapter()
        this.adapter = articleAdapter

        lifecycleScope.launchWhenStarted {
            articlesViewModel.articles.collect { articles ->
                adapter?.submitList(articles)
            }
        }

        val binding = FragmentArticlesBinding.bind(view)
        with(binding.list) {
            layoutManager = LinearLayoutManager(context)
            this.adapter = ArticleAdapter()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter = null
    }
}