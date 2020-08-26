package com.example.superrickandmorty.login.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.superrickandmorty.R
import com.example.superrickandmorty.databinding.FragmentLoginBinding
import com.example.superrickandmorty.login.data.remote.FirebaseLoginRepository
import com.example.superrickandmorty.login.domain.LoginUseCase
import com.example.superrickandmorty.login.domain.Usuario
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginUseCase: LoginUseCase
    private lateinit var repository: FirebaseLoginRepository
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginViewModelFactory: LoginViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        binding = FragmentLoginBinding.bind(view)
        setupLiveData()
        setupListener()
    }

    private fun setupLiveData() {
        loginViewModel.getLiveData().observe(
            viewLifecycleOwner,
            Observer { state ->
                handleState(state)
            }
        )
    }

    private fun handleState(state: LoginState?) {
        when (state) {
            is LoginState.LoadingState -> mostrarCargando()
            is LoginState.ContrasenaIncorrecta -> mostrarErrorContrasena()
            is LoginState.Complete -> mostrarLoginCorrecto(state.result)
            is LoginState.Error -> mostrarError()
        }
    }

    private fun mostrarError() {
        Toast.makeText(requireContext(), "Ups, algo ha pasado", Toast.LENGTH_LONG).show()

    }

    private fun mostrarLoginCorrecto(result: Usuario?) {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_loginFragment_to_menuFragment)

    }

    private fun mostrarErrorContrasena() {
        Toast.makeText(requireContext(), "Contraseña incorrecta", Toast.LENGTH_LONG).show()

    }

    private fun mostrarCargando() {
        Toast.makeText(requireContext(), "Cargando", Toast.LENGTH_SHORT).show()
    }

    private fun setupDependencies() {
        repository = FirebaseLoginRepository(FirebaseAuth.getInstance())
        loginUseCase = LoginUseCase(repository)
        loginViewModelFactory = LoginViewModelFactory(loginUseCase)
        loginViewModel =
            ViewModelProvider(this, loginViewModelFactory).get(LoginViewModel::class.java)

    }

    private fun setupListener() {
        binding.apply {
            btnIngresar.setOnClickListener {
                val usuario = etUsuario.text.toString()
                val contrasena = etContrasena.text.toString()
                callLoginViewModel(usuario, contrasena)
            }
        }
    }

    private fun callLoginViewModel(usuario: String, contrasena: String) {
        loginViewModel.doLogin(usuario, contrasena)
    }

}