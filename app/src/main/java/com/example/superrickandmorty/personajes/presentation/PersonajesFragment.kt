package com.example.superrickandmorty.personajes.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.superrickandmorty.R
import com.example.superrickandmorty.databinding.FragmentPersonajesBinding
import com.example.superrickandmorty.network.RetrofitHandler
import com.example.superrickandmorty.personajes.data.remote.PersonajeMapper
import com.example.superrickandmorty.personajes.data.remote.PersonajesApi
import com.example.superrickandmorty.personajes.data.remote.PersonajesModel
import com.example.superrickandmorty.personajes.data.remote.RemotePersonajeRepository
import com.example.superrickandmorty.personajes.domain.ObtenerPersonajesUseCase
import com.example.superrickandmorty.personajes.domain.Personajes

class PersonajesFragment : Fragment(R.layout.fragment_personajes) {

    private lateinit var binding: FragmentPersonajesBinding
    private lateinit var viewModel: PersonajesViewModel
    private lateinit var viewModelFactory: PersonajesViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDependencies()
        binding = FragmentPersonajesBinding.bind(view)
        setupLiveData()
    }

    private fun setupDependencies() {
        viewModelFactory = PersonajesViewModelFactory(
            ObtenerPersonajesUseCase(
                RemotePersonajeRepository(
                    RetrofitHandler.getPersonajesApi(),
                    PersonajeMapper()
                )
            )
        )

        viewModel = ViewModelProvider(this, viewModelFactory).get(PersonajesViewModel::class.java)
    }

    private fun setupLiveData() {
        viewModel.getLiveData().observe(
            viewLifecycleOwner,
            Observer { state -> state?.let { handleState(it) } }
        )
        viewModel.obtenerPersonajes()
    }

    private fun handleState(state: PersonajesState) {
        when(state){
            is PersonajesState.LoadingPersonajesState -> showLoading()
            is PersonajesState.LoadPersonajesState -> loadPersonajes(state.result)
            is PersonajesState.EmptyListPersonajesState -> showEmptyList()
            is PersonajesState.ErrorServerPersonajesState -> showErrorServer()
            is PersonajesState.NotInternetPersonajesState -> showSinInternetMessage()
        }
    }

    private fun showSinInternetMessage() {

    }

    private fun showErrorServer() {

    }

    private fun showEmptyList() {

    }

    private fun showLoading() {

    }

    private fun loadPersonajes(result: Personajes) {
        Toast.makeText(context, "result ${result.result.size}", Toast.LENGTH_SHORT).show()
    }

}