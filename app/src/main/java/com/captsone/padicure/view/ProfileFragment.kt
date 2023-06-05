package com.captsone.padicure.view

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.captsone.padicure.MainActivity
import com.captsone.padicure.R
import com.captsone.padicure.databinding.FragmentLoginBinding
import com.captsone.padicure.databinding.FragmentProfileBinding
import com.captsone.padicure.view.viewmodel.ProfileViewModel
import com.captsone.padicure.view.viewmodel.auth.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(), Screen {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var loadingBar: ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        loadingBar = binding.loadingBar
        return (view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.userData.observe(viewLifecycleOwner){
            binding.profileName.text = it.name
            binding.profileEmail.text = it.email
            Glide.with(this).load(it.photoUrl).into(binding.profilePict)
        }

        binding.cardLogOut.setOnClickListener {
            viewModel.logout()
            Intent(activity, MainActivity::class.java).also {
                startActivity(it)
                activity?.finish()
            }
        }
        viewModel.message.observe(viewLifecycleOwner){
            showMessage(it)
        }
        binding.cardSettings.setOnClickListener {
            showMessage(resources.getString(R.string.not_available))
        }
        binding.cardTerms.setOnClickListener {
            showMessage(resources.getString(R.string.not_available))
        }
        binding.editButton.setOnClickListener {
            showMessage(resources.getString(R.string.not_available))
        }
    }

    override fun showLoading(isLoading: Boolean) {
        loadingBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}