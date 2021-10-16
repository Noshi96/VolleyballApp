package com.papplications.volleyballteam.app.feature.draw.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.papplications.volleyballteam.app.feature.draw.viewmodel.DrawViewModel
import com.papplications.volleyballteam.databinding.FragmentDrawMethodBinding
import org.koin.android.ext.android.inject


class DrawMethodFragment : Fragment() {

    private val viewModel: DrawViewModel by inject()
    private lateinit var _binding: FragmentDrawMethodBinding
    private val binding get() = _binding

    companion object {
        fun newInstance() = DrawMethodFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDrawMethodBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAutoDraw.setOnClickListener {
            val action = DrawMethodFragmentDirections.actionDrawMethodFragmentToChoosePlayersFragment()
            findNavController().navigate(action)
        }
    }

}