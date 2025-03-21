package com.example.appnewnetwork.register.view

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore.Images.Media.getBitmap
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.appnewnetwork.R
import com.example.appnewnetwork.common.base.DependencyInjector
import com.example.appnewnetwork.common.view.CropperImageFragment
import com.example.appnewnetwork.common.view.CustomDialog
import com.example.appnewnetwork.databinding.FragmentRegisterPhotoBinding
import com.example.appnewnetwork.register.presentation.RegisterPhotoPresenter

class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo), RegisterPhoto.View {

    private var binding: FragmentRegisterPhotoBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null
    override lateinit var presenter: RegisterPhoto.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("cropKey") { requestKey, bundle ->  
            val uri = bundle.getParcelable<Uri>(CropperImageFragment.KEY_URI)
            onCropImageResult(uri)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRegisterPhotoBinding.bind(view)

        val repository = DependencyInjector.registerEmailRepository()
        presenter = RegisterPhotoPresenter(this, repository)

        binding?.let {
            with(it){

                registerBtnJump.setOnClickListener{
                    fragmentAttachListener?.goToHomeScreen()
                }

                registerBtnNext.isEnabled = true
                registerBtnNext.setOnClickListener {
                    openDialog()
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener) {
            fragmentAttachListener = context
        }
    }

    override fun showProgress(enable: Boolean) {
        binding?.registerBtnNext?.showProgress(enable)
    }

    override fun onUpdateFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onUpdateSuccess() {
        fragmentAttachListener?.goToHomeScreen()
    }


    private fun openDialog(){
        val customDialog = CustomDialog(requireContext())
        customDialog.addButton(R.string.photo, R.string.gallery) {
            when (it.id) {
                R.string.photo -> {
                    // codigo para permissao da camera, bug fixed de crash por falta de permission
                    if (allPermissionsGranted()) {
                        fragmentAttachListener?.goToCameraScreen()
                    } else {
                        getPermission.launch(REQUIRED_PERMISSION)
                    }
                }
                R.string.gallery -> {
                    fragmentAttachListener?.goToGalleryScreen()
                }
            }
        }
        customDialog.show()
    }

    private val getPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            granted ->
        if (allPermissionsGranted()) {
            fragmentAttachListener?.goToCameraScreen()
        } else Toast.makeText(requireContext(), R.string.permission_camera_danied, Toast.LENGTH_LONG).show()

    }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(requireContext(), REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    companion object {
        private const val REQUIRED_PERMISSION = android.Manifest.permission.CAMERA
    }

    private fun onCropImageResult(uri: Uri?) {
        if (uri != null) {
            val bitmap = if (Build.VERSION.SDK_INT >= 28) {
                val source = ImageDecoder.createSource(requireContext().contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            } else {
                getBitmap(requireContext().contentResolver, uri)
            }
            binding?.registerImgProfile?.setImageBitmap(bitmap)

            presenter.updateUser(uri)
        }
    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

}