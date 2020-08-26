package com.example.superrickandmorty.registro.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.superrickandmorty.R
import com.example.superrickandmorty.databinding.FragmentRegistraUsuarioBinding
import com.example.superrickandmorty.registro.data.remote.FirebaseRegistroRepository
import com.example.superrickandmorty.registro.domain.RegistraUsuarioUseCase
import com.example.superrickandmorty.registro.domain.RegistroRepository
import com.google.firebase.auth.FirebaseAuth

class RegistraUsuarioFragment : Fragment(R.layout.fragment_registra_usuario) {

    lateinit var binding : FragmentRegistraUsuarioBinding
    private lateinit var registraUsuarioUseCase: RegistraUsuarioUseCase
    private lateinit var registraUsuarioViewModel: RegistroViewModel
    private lateinit var registraUsuarioViewModelFactory: RegistroViewModelFactory
    private lateinit var registroRepository: RegistroRepository


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        setupLiveData()
        binding = FragmentRegistraUsuarioBinding.bind(view)
        setupClickListener()
    }

    private fun setupLiveData() {
        registraUsuarioViewModel.getLiveData().observe(
            viewLifecycleOwner,
            Observer {
                state -> state?.let { handleState(state) }
            }
        )
    }

    private fun handleState(state: RegistroUiState) {
        when(state){
            is RegistroUiState.LoadingRegistroUiState -> showLoading()
            is RegistroUiState.UsuarioNoDisponibleRegistroUiState -> showUsuarioNoDisponible()
            is RegistroUiState.SuccessRegistroUiState -> showSuccessRegister(state.result)
            is RegistroUiState.ErrorRegistroUiState -> showErrorMessage(state.error)
        }
    }

    private fun showErrorMessage(error: Throwable) {
        Toast.makeText(context, "Error ${error.message}", Toast.LENGTH_SHORT).show()
    }

    private fun showSuccessRegister(result: Boolean) {
        Toast.makeText(context, "Resultado $result", Toast.LENGTH_SHORT).show()
    }

    private fun showUsuarioNoDisponible() {
        Toast.makeText(context, "Usuario no disponible", Toast.LENGTH_SHORT).show()
    }

    private fun showLoading() {
        Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
    }

    private fun setupClickListener() {
        binding.btnRegistrarUsuario.setOnClickListener {
            registraUsuarioViewModel.registraUsuario()
        }
    }

    private fun setupDependencies() {
        registroRepository = FirebaseRegistroRepository(FirebaseAuth.getInstance())
        registraUsuarioUseCase = RegistraUsuarioUseCase(registroRepository)
        registraUsuarioViewModelFactory = RegistroViewModelFactory(registraUsuarioUseCase)
        registraUsuarioViewModel = ViewModelProvider(
            this, registraUsuarioViewModelFactory
        ).get(RegistroViewModel::class.java)
    }
}