package com.example.appnewnetwork.register.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.WindowInsetsController
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.appnewnetwork.R
import com.example.appnewnetwork.common.extension.hideKeyboard
import com.example.appnewnetwork.common.extension.replaceFragment
import com.example.appnewnetwork.common.view.CropperImageFragment
import com.example.appnewnetwork.common.view.CropperImageFragment.Companion.KEY_URI
import com.example.appnewnetwork.databinding.ActivityRegisterBinding
import com.example.appnewnetwork.main.view.MainActivity
import com.example.appnewnetwork.register.view.RegisterNamePasswordFragment.Companion.KEY_EMAIL
import com.example.appnewnetwork.register.view.RegisterWelcomeFragment.Companion.KEY_NAME
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RegisterActivity : AppCompatActivity(), FragmentAttachListener {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var currentPhoto: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.insetsController?.setSystemBarsAppearance(
            WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )
        window.statusBarColor = ContextCompat.getColor(this, R.color.blue_primary)

        val fragment = RegisterEmailFragment()
        replaceFragment(fragment)

    }

    private fun replaceFragment(fragment: Fragment) {
        replaceFragment(R.id.register_fragment, fragment)
        hideKeyboard()
    }

    override fun goToNameAndPassWordScreen(email: String) {
        val fragment = RegisterNamePasswordFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_EMAIL, email)
            }
        }
        replaceFragment(fragment)
    }

    override fun goToWelcomeScreen(name: String) {
        val fragment = RegisterWelcomeFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_NAME, name)
            }
        }
        replaceFragment(fragment)
    }

    override fun goToPhotoScreen() {
        val fragment = RegisterPhotoFragment()
        replaceFragment(fragment)
    }

    override fun goToHomeScreen() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { openImageCropper(it) }
    }
    override fun goToGalleryScreen() {
        getContent.launch("image/*")
    }

    private val getCamera = registerForActivityResult(ActivityResultContracts.TakePicture()) { saved ->
        if (saved) {
            openImageCropper(currentPhoto)
        }
    }

    override fun goToCameraScreen() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            val photoFile: File? = try {
                createImageFile()
            } catch (e: IOException) {
                Log.e("RegisterActivity", e.message, e)
                null
            }

            photoFile?.also {
                val photoUri = FileProvider.getUriForFile(this, "com.example.appnewnetwork.fileprovider", it)
                currentPhoto = photoUri

                getCamera.launch(photoUri)
            }

        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timestamp}_", ".jpeg", dir)
    }

    private fun openImageCropper(uri: Uri){
        val fragment = CropperImageFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_URI, uri)
            }
        }
        replaceFragment(fragment)
    }
}