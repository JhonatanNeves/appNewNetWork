package com.example.appnewnetwork.profile.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appnewnetwork.R
import com.example.appnewnetwork.common.base.BaseFragment
import com.example.appnewnetwork.common.base.DependencyInjector
import com.example.appnewnetwork.common.model.Post
import com.example.appnewnetwork.common.model.UserAuth
import com.example.appnewnetwork.databinding.ActivityLoginBinding.bind
import com.example.appnewnetwork.databinding.FragmentProfileBinding
import com.example.appnewnetwork.profile.Profile
import com.example.appnewnetwork.profile.presenter.ProfilePresenter

class ProfileFragment : BaseFragment<FragmentProfileBinding, Profile.Presenter>(
    R.layout.fragment_profile,
        FragmentProfileBinding::bind
    ), Profile.View {

    override lateinit var presenter: Profile.Presenter

    private val adapter = PostAdapter()
    override fun setUpPresenter() {
        val repository = DependencyInjector.profileReposutory()
        presenter = ProfilePresenter(this, repository)
    }
    override fun setupViews(){
        binding?.profileRv?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.profileRv?.adapter = adapter

        presenter.fetchuserProfile()
    }

    override fun showProgress(enabled: Boolean) {
        binding?.profileProgress?.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun displayUserProfile(userAuth: UserAuth) {
        binding?.profileTxtPostsCount?.text = userAuth.postCount.toString()
        binding?.profileTxtFollowingCount?.text = userAuth.followingCount.toString()
        binding?.profileTxtFollowersCount?.text = userAuth.followersCount.toString()
        binding?.profileTxtUsername?.text = userAuth.name
        binding?.profileTxtBio?.text = "Minas Gerais\\n Software Engineer"
        presenter.fetchuserPosts()
    }

    override fun displayRequestFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun displayEmptyPost() {
        binding?.profileTxtEmpty?.visibility = View.VISIBLE
        binding?.profileRv?.visibility = View.GONE
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun displayFullPosts(posts: List<Post>) {
        binding?.profileTxtEmpty?.visibility = View.GONE
        binding?.profileRv?.visibility = View.VISIBLE
        adapter.notifyDataSetChanged()
    }
    @MenuRes
    override fun getMenu(): Int {
        return R.menu.menu_profile
    }

}