package com.papplications.volleyballteam.app.player.view.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.papplications.volleyballteam.app.player.model.Player
import com.papplications.volleyballteam.app.player.viewmodel.MatchViewModel
import com.papplications.volleyballteam.databinding.FragmentUpdatePlayerBinding
import org.koin.android.ext.android.inject

class UpdatePlayerFragment : Fragment() {

    private val viewModel: MatchViewModel by inject()
    private val args: UpdatePlayerFragmentArgs by navArgs()
    private lateinit var _binding: FragmentUpdatePlayerBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdatePlayerBinding.inflate(inflater, container, false)

        setValuesInForm()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonUpdatePlayer.setOnClickListener {
            updateData()
        }
    }

    private fun updateData() {
        var name = binding.editTextNameUpdate.editText?.text.toString()

        val updatePlayer = Player(id=args.currentPlayer.id, name=name, img="")
        viewModel.updatePlayer(updatePlayer)

        val action = UpdatePlayerFragmentDirections.actionUpdatePlayerFragmentToChoosePlayersFragment()
        findNavController().navigate(action)
    }

    private fun checkInput(name: String, nick: String): Boolean{
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(nick))
    }

    private fun setValuesInForm(){
        binding.editTextNameUpdateInput.setText(args.currentPlayer.name)
    }
}