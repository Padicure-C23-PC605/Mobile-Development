package com.captsone.padicure.view.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.captsone.padicure.R
import com.captsone.padicure.databinding.FragmentLoginBinding
import com.captsone.padicure.view.HomeActivity
import com.captsone.padicure.view.Screen
import com.captsone.padicure.view.viewmodel.auth.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(), Screen {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var loadingBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        navController = this.findNavController()
        binding.registerTextButton.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }
        loadingBar = binding.loadingBar
        return (view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.materialButton.setOnClickListener {
            viewModel.login(
                binding.emailInput.text.toString(),
                binding.passwordInput.text.toString()
            )
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            showMessage(it)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        viewModel.dataUser.observe(viewLifecycleOwner) {
            Intent(activity, HomeActivity::class.java).also {
                startActivity(it)
                activity?.finish()
            }
        }
    }

    override fun showLoading(isLoading: Boolean) {
        loadingBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}