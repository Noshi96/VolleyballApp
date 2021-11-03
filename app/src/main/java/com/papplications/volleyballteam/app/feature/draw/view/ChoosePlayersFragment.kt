package com.papplications.volleyballteam.app.feature.draw.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.papplications.volleyballteam.R
import com.papplications.volleyballteam.app.feature.draw.model.ParcelUserInformation
import com.papplications.volleyballteam.app.feature.draw.viewmodel.DrawViewModel
import com.papplications.volleyballteam.app.match.viewmodel.MatchViewModel
import com.papplications.volleyballteam.app.player.viewmodel.PlayerViewModel
import com.papplications.volleyballteam.databinding.FragmentChoosePlayersBinding
import org.koin.android.ext.android.inject


class ChoosePlayersFragment : Fragment() {

    private val viewModel: DrawViewModel by inject()
    private val matchViewModel: MatchViewModel by inject()
    private val playerViewModel: PlayerViewModel by inject()
    private lateinit var _binding: FragmentChoosePlayersBinding
    private val binding get() = _binding
    private lateinit var userInformationToSend: ParcelUserInformation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChoosePlayersBinding.inflate(inflater, container, false)

        val playersList = mutableListOf<String>()
        playerViewModel.getAllPlayers.observe(viewLifecycleOwner, { players ->
            playersList.clear()
            players.forEach { player ->
                playersList.add(player.name)
            }
            setChipsForChipCategories(container, playersList)
        })

        viewModel.checkedChipsNamesList.observe(viewLifecycleOwner) {
            binding.textViewCurrentCheckedNamesSize.text = it.size.toString()
            val userInformation = viewModel.checkedChipsNamesList.value?.let {
                ParcelUserInformation(
                    names = it,
                    tempNames = it
                )
            }
            if (userInformation != null) {
                userInformationToSend = userInformation
            }
        }

        binding.buttonDraw.setOnClickListener {
            val passWithBundle = bundleOf("BUNDLE_KEY" to userInformationToSend)
            findNavController().navigate(
                R.id.action_choosePlayersFragment_to_drawFragment,
                passWithBundle
            )
        }

        binding.floatingActionButton.setOnClickListener {
            val action =
                ChoosePlayersFragmentDirections.actionChoosePlayersFragmentToAddPlayerFragment()
            findNavController().navigate(action)
        }

        binding.buttonListWithAllPlayers.setOnClickListener {
            val action =
                ChoosePlayersFragmentDirections.actionChoosePlayersFragmentToPlayersListFragment()
            findNavController().navigate(action)
        }

        binding.buttonAddMatchAndDraw.setOnClickListener{
            val action =
                ChoosePlayersFragmentDirections.actionChoosePlayersFragmentToDrawPlayersFragment(userInformationToSend)
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun addChipsToViewAndLoadChipsList(
        playersList: List<String>,
        container: ViewGroup?,
        chipsList: MutableList<Chip>
    ) {
        binding.chipGroup.removeAllViews()
        playersList.forEach { playerName ->
            val chip = layoutInflater.inflate(R.layout.chip_item, container, false) as Chip
            chip.text = playerName
            chip.chipIcon = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_baseline_person_24
            )
            chip.id = View.generateViewId()
            chipsList.add(chip)
            binding.chipGroup.addView(chip)
        }
    }

    private fun setChipsForChipCategories(
        container: ViewGroup?,
        playersList: MutableList<String>
    ) {
        val chipsList = mutableListOf<Chip>()
        addChipsToViewAndLoadChipsList(playersList, container, chipsList)

        binding.buttonChooseAll.setOnClickListener {
            setAllChips(chipsList)
        }

        setChipsListenerAndUpdateViewModel(chipsList)
    }

    private fun setChipsListenerAndUpdateViewModel(chipsList: MutableList<Chip>) {
        chipsList.forEach { chip ->
            chip.setOnClickListener {
                val currentCheckedNames = mutableListOf<String>()
                binding.chipGroup.checkedChipIds.forEach { chipId ->
                    currentCheckedNames.add(binding.chipGroup.findViewById<Chip>(chipId).text.toString())
                }
                viewModel.onSelectedChipChangesSendToViewModel(
                    binding.chipGroup.checkedChipIds,
                    currentCheckedNames
                )
            }
        }
    }

    private fun setAllChips(chipsList: MutableList<Chip>) {
        chipsList.forEach { chip ->
            chip.isChecked = true
        }
        val currentCheckedNames = mutableListOf<String>()
        binding.chipGroup.checkedChipIds.forEach { chipId ->
            currentCheckedNames.add(binding.chipGroup.findViewById<Chip>(chipId).text.toString())
        }
        viewModel.onSelectedChipChangesSendToViewModel(
            binding.chipGroup.checkedChipIds,
            currentCheckedNames
        )
    }
}