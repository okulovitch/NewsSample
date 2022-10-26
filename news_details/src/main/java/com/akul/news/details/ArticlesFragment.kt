package com.akul.news.details

import android.content.Context
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
import dagger.Lazy
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class ArticlesFragment : Fragment(R.layout.fragment_articles) {

    @Inject
    internal lateinit var articlesViewModelFactory: Lazy<ArticlesViewModel.Factory>

    private val articlesViewModel: ArticlesViewModel by viewModels { articlesViewModelFactory.get() }
    private val componentViewModel: NewsDetailsComponentViewModel by viewModels()
    private var adapter: ArticleAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.newsDetailsComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val articleAdapter = ArticleAdapter()
        this.adapter = articleAdapter

        //todo fix scope end on destroy view (use scope that control lifecycle)
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