package com.papplications.volleyballteam.app.match.view.add

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.papplications.volleyballteam.databinding.FragmentAddPlayerBinding

class AddMatchFragment : Fragment() {

    private lateinit var _binding: FragmentAddPlayerBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddPlayerBinding.inflate(inflater, container, false)

        return binding.root
    }

}