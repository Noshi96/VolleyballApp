package com.papplications.volleyballteam.app.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.papplications.volleyballteam.app.main.viewmodel.MainViewModel
import com.papplications.volleyballteam.databinding.FragmentMainBinding
import org.koin.android.ext.android.inject
import androidx.navigation.fragment.findNavController
import com.papplications.volleyballteam.R

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by inject()
    private lateinit var _binding: FragmentMainBinding
    private val binding get() = _binding

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonDrawTeams.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_drawMethodFragment)
        }

    }


}