package com.example.appnewnetwork.register.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.appnewnetwork.R
import com.example.appnewnetwork.databinding.FragmentRegisterEmailBinding

class RegisterEmailFragment : Fragment(R.layout.fragment_register_email) {
    private var binding : FragmentRegisterEmailBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterEmailBinding.bind(view)
    }

}