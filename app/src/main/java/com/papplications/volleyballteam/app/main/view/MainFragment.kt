package com.papplications.volleyballteam.app.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.papplications.volleyballteam.R
import com.papplications.volleyballteam.app.main.viewmodel.MainViewModel
import com.papplications.volleyballteam.app.player.viewmodel.PlayerViewModel
import com.papplications.volleyballteam.databinding.FragmentMainBinding
import org.koin.android.ext.android.inject

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by inject()
    private val playerViewModel: PlayerViewModel by inject()
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

        binding.buttonLoadDatabase.setOnClickListener {
            playerViewModel.fillDataBase()
            Toast.makeText(activity, "Done", Toast.LENGTH_LONG).show();
        }

    }


}