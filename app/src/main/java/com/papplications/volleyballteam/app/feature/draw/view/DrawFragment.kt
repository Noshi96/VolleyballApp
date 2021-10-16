package com.papplications.volleyballteam.app.feature.draw.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.papplications.volleyballteam.app.feature.draw.model.ParcelUserInformation
import com.papplications.volleyballteam.app.feature.draw.viewmodel.DrawViewModel
import com.papplications.volleyballteam.databinding.FragmentDrawBinding
import org.koin.android.ext.android.inject


class DrawFragment : Fragment() {

    private val viewModel: DrawViewModel by inject()
    private lateinit var _binding: FragmentDrawBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDrawBinding.inflate(inflater, container, false)

        val names = arguments?.getParcelable<ParcelUserInformation>("BUNDLE_KEY")?.names

        binding.buttonDrawAgain.setOnClickListener {
            binding.textViewTeamOne.text = names?.let { it1 -> viewModel.drawOneTeam(it1).toString() }
        }

        binding.textViewTeamTwo.text = names.toString()


        return binding.root
    }

}