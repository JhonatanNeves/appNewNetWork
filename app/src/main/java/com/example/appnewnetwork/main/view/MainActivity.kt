package com.example.appnewnetwork.main.view


import android.os.Bundle
import android.view.MenuItem
import android.view.WindowInsetsController
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import com.example.appnewnetwork.R
import com.example.appnewnetwork.post.view.AddFragment
import com.example.appnewnetwork.common.extension.replaceFragment
import com.example.appnewnetwork.databinding.ActivityMainBinding
import com.example.appnewnetwork.home.view.HomeFragment
import com.example.appnewnetwork.profile.view.ProfileFragment
import com.example.appnewnetwork.search.view.SearchFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener , AddFragment.AddListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var homeFragment: HomeFragment
    private lateinit var searchFragment: Fragment
    private lateinit var addFragment: Fragment
    private lateinit var profileFragment: ProfileFragment
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        window.insetsController?.setSystemBarsAppearance(
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )

        setSupportActionBar(binding.mainToolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_insta_camera)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = ""

        homeFragment = HomeFragment()
        searchFragment = SearchFragment()
        addFragment = AddFragment()
        profileFragment = ProfileFragment()

//        currentFragment = homeFragment

        binding.mainBottomNav.setOnNavigationItemSelectedListener(this)
        binding.mainBottomNav.selectedItemId = R.id.menu_bottom_home

    }

    private fun setScrollToobarEnabled(enabled: Boolean) {
        val params = binding.mainToolbar.layoutParams as AppBarLayout.LayoutParams
        val coordinatorParams = binding.mainAppbar.layoutParams as CoordinatorLayout.LayoutParams
        if (enabled) {
            params.scrollFlags = AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
            coordinatorParams.behavior = AppBarLayout.Behavior()
        } else {
            params.scrollFlags = 0
            coordinatorParams.behavior = null
        }
        binding.mainAppbar.layoutParams = coordinatorParams
    }

    //concertar bug da toolbar , desativando quando o fragmento de camera abre
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var scrollToolbarEnabled = true
        when(item.itemId){
            R.id.menu_bottom_home -> {
                if (currentFragment == homeFragment) return false
                currentFragment = homeFragment
                scrollToolbarEnabled = true
            }
            R.id.menu_bottom_search -> {
                if (currentFragment == searchFragment) return false
                currentFragment = searchFragment
                scrollToolbarEnabled = true
            }
            R.id.menu_bottom_more -> {
                if (currentFragment == addFragment) return false
                currentFragment = addFragment
                scrollToolbarEnabled = false

            }
            R.id.menu_bottom_profile -> {
                if (currentFragment == profileFragment) return false
                currentFragment = profileFragment
                scrollToolbarEnabled = true
            }
        }

        setScrollToobarEnabled(scrollToolbarEnabled)

        currentFragment?.let {
            replaceFragment(R.id.main_fragment, it)
        }

        return true
    }

    override fun onPostCreated() {
        homeFragment.presenter.clear()

        if (supportFragmentManager.findFragmentByTag(profileFragment.javaClass.simpleName) != null )
            profileFragment.presenter.clear()

        binding.mainBottomNav.selectedItemId = R.id.menu_bottom_home
    }

}