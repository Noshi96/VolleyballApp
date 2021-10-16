package com.papplications.volleyballteam.app.player.view.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.papplications.volleyballteam.R
import com.papplications.volleyballteam.app.player.model.Player
import com.papplications.volleyballteam.app.player.viewmodel.PlayerViewModel
import com.papplications.volleyballteam.databinding.FragmentUpdatePlayerBinding
import org.koin.android.ext.android.inject

class UpdatePlayerFragment : Fragment() {

    private val viewModel: PlayerViewModel by inject()
    private val args: UpdatePlayerFragmentArgs by navArgs()
    private lateinit var _binding: FragmentUpdatePlayerBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentUpdatePlayerBinding.inflate(inflater, container, false)

        setValuesInForm()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.buttonUpdatePlayer.setOnClickListener {
            updateData()
        }
    }

    private fun updateData() {
        val name = binding.editTextNameUpdate.editText?.text.toString()

        val updatePlayer = Player(id = args.currentPlayer.id, name = name, img = "")
        viewModel.updatePlayer(updatePlayer)

        val action =
            UpdatePlayerFragmentDirections.actionUpdatePlayerFragmentToChoosePlayersFragment()
        findNavController().navigate(action)
    }

    private fun checkInput(name: String, nick: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(nick))
    }

    private fun setValuesInForm() {
        binding.editTextNameUpdateInput.setText(args.currentPlayer.name)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deletePlayer()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deletePlayer() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deletePlayer(args.currentPlayer)
            val action =
                UpdatePlayerFragmentDirections.actionUpdatePlayerFragmentToChoosePlayersFragment()
            findNavController().navigate(action)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentPlayer.name}?")
        builder.setMessage("Are you sure you want delete ${args.currentPlayer.name}?")
        builder.create().show()
    }
}