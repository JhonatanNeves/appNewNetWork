package com.example.appnewnetwork.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appnewnetwork.R
import com.example.appnewnetwork.common.base.BaseFragment
import com.example.appnewnetwork.common.base.DependencyInjector
import com.example.appnewnetwork.common.model.Post
import com.example.appnewnetwork.databinding.FragmentHomeBinding
import com.example.appnewnetwork.home.Home
import com.example.appnewnetwork.home.presenter.HomePresenter

class HomeFragment : BaseFragment<FragmentHomeBinding, Home.Presenter>(
    R.layout.fragment_home, FragmentHomeBinding::bind
), Home.View
{

    override lateinit var presenter: Home.Presenter
    private var adapter = FeedAdapter()
    override fun setupViews() {
        binding?.homeRv?.layoutManager = LinearLayoutManager(context)
        binding?.homeRv?.adapter = adapter
        presenter.fetchFeed()
    }

    override fun setUpPresenter() {
        presenter = HomePresenter(this, DependencyInjector.homeRepository())
    }

    override fun getMenu() = R.menu.menu_profile

    override fun showProgress(enabled: Boolean) {
        binding?.homeProgress?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun displayRequestFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun displayEmptyPost() {
        binding?.homeTxtEmpty?.visibility = View.VISIBLE
        binding?.homeRv?.visibility = View.GONE
    }

    override fun displayFullPosts(posts: List<Post>) {
        binding?.homeRv?.visibility = View.VISIBLE
        binding?.homeTxtEmpty?.visibility = View.GONE
        adapter.notifyDataSetChanged()
        adapter.items = posts
    }

}