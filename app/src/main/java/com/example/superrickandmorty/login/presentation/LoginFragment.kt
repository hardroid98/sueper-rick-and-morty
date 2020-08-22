package com.example.superrickandmorty.login.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.superrickandmorty.R
import com.example.superrickandmorty.databinding.FragmentLoginBinding
import com.example.superrickandmorty.login.data.local.LocalLoginRepository
import com.example.superrickandmorty.login.domain.LoginRepository
import com.example.superrickandmorty.login.domain.LoginUseCase
import com.example.superrickandmorty.login.domain.Usuario
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginUseCase: LoginUseCase
    private lateinit var repository: LoginRepository
    private val compositeDisposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        binding = FragmentLoginBinding.bind(view)
        setupListener()
    }

    private fun setupDependencies() {
        repository = LocalLoginRepository()
        loginUseCase = LoginUseCase(repository)
    }

    private fun setupListener() {
        binding.apply {
            btnIngresar.setOnClickListener {
                val usuario = etUsuario.text.toString()
                val contrasena = etContrasena.text.toString()
                callUseCase(usuario, contrasena)
            }
        }
    }

    private fun callUseCase(usuario: String, contrasena: String) {
        compositeDisposable.add(
            loginUseCase.execute(usuario, contrasena)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {result -> handleResult(result)},
                    {error -> handleError(error)}
                )
        )
    }

    private fun handleResult(result: Usuario) {

    }

    private fun handleError(error: Throwable) {
        Toast.makeText(requireContext(), "Error: {${error.message}", Toast.LENGTH_LONG).show()
    }



}