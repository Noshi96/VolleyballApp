package com.papplications.volleyballteam.app.player.view.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.papplications.volleyballteam.app.match.viewmodel.MatchViewModel
import com.papplications.volleyballteam.app.match.model.Player
import com.papplications.volleyballteam.databinding.FragmentAddPlayerBinding
import org.koin.android.ext.android.inject

class AddPlayerFragment : Fragment() {

    private val viewModel: MatchViewModel by inject()
    private lateinit var _binding: FragmentAddPlayerBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAddPlayer.setOnClickListener {
            insertDataToDatabase()
        }
    }

    private fun insertDataToDatabase() {
        var name = binding.editTextNameInsert.editText?.text.toString()
        val nick = binding.editTextNickInsert.editText?.text.toString()

        if (nick.isNotEmpty()) {
            name = "$name($nick)"
        }
        val player = Player(playerId = 0, name = name, img = "")
        viewModel.addPlayer(player)

        val action = AddPlayerFragmentDirections.actionAddPlayerFragmentToChoosePlayersFragment()
        findNavController().navigate(action)
    }

    private fun checkInput(name: String, nick: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(nick))
    }
}