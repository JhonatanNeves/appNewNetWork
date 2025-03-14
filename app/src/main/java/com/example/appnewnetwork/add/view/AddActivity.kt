package com.example.appnewnetwork.add.view

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appnewnetwork.R
import com.example.appnewnetwork.add.Add
import com.example.appnewnetwork.add.presentation.AddPresenter
import com.example.appnewnetwork.common.base.DependencyInjector
import com.example.appnewnetwork.databinding.ActivityAddBinding
import com.example.appnewnetwork.databinding.FragmentAddBinding
import java.net.URI

class AddActivity : AppCompatActivity(), Add.View {

    private lateinit var binding: ActivityAddBinding
    private lateinit var uri: Uri
    override fun showProgress(enabled: Boolean) {
        binding.addProgress.visibility = if (enabled) View.VISIBLE else View.GONE
    }

    override fun displayRequestSuccess() {
        finish()
    }

    override fun displayRequestFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override lateinit var presenter: Add.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        window.insetsController?.setSystemBarsAppearance(
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )

        setSupportActionBar(binding.addToolbar)

        val drawable = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back)
        supportActionBar?.setHomeAsUpIndicator(drawable)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        uri = intent.extras?.getParcelable("photoUri") ?: throw RuntimeException("Photo not found!")

        binding.addImgCapition.setImageURI(uri)

        val repository = DependencyInjector.addRepository()
        presenter = AddPresenter(this, repository)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_share, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.action_share -> {
                presenter.createPost(uri, binding.addEditCapition.text.toString())
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

}