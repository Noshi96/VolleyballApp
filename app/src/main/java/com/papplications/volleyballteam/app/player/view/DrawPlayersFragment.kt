package com.papplications.volleyballteam.app.player.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.papplications.volleyballteam.app.match.adapters.TeamAdapter
import com.papplications.volleyballteam.app.player.model.Player
import com.papplications.volleyballteam.app.player.viewmodel.PlayerViewModel
import com.papplications.volleyballteam.databinding.FragmentDrawPlayersBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class DrawPlayersFragment : Fragment() {

    private val viewModel: PlayerViewModel by inject()
    private val args: DrawPlayersFragmentArgs by navArgs()
    private lateinit var _binding: FragmentDrawPlayersBinding
    private val binding get() = _binding
    private lateinit var holeTeam: MutableList<Player>
    private lateinit var teamA: MutableList<Player>
    private lateinit var teamB: MutableList<Player>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDrawPlayersBinding.inflate(inflater, container, false)

        val names = args.playersNames.names
        teamA = mutableListOf()
        teamB = mutableListOf()

        val adapterTeamA = TeamAdapter()
        val recyclerViewTeamA = binding.recyclerViewTeamA
        recyclerViewTeamA.adapter = adapterTeamA
        recyclerViewTeamA.layoutManager = LinearLayoutManager(requireContext())

        val adapterTeamB = TeamAdapter()
        val recyclerViewTeamB = binding.recyclerViewTeamB
        recyclerViewTeamB.adapter = adapterTeamB
        recyclerViewTeamB.layoutManager = LinearLayoutManager(requireContext())

        viewModel.updateAdapters.observe(viewLifecycleOwner, {
            adapterTeamA.setData(
                teamA, "1", args.showAvatars,
                chosenTeamsLists = false,
                holeTeamList = false,
                adapterTeamA,
                adapterTeamB,
                adapterTeamB,
                false
            )
            adapterTeamB.setData(
                teamB, "2", args.showAvatars,
                chosenTeamsLists = false,
                holeTeamList = false,
                adapterTeamA,
                adapterTeamB,
                adapterTeamB,
                false
            )
        })

        CoroutineScope(Dispatchers.IO).launch {
            holeTeam = mutableListOf()
            withContext(Dispatchers.IO) {
                names.forEach { name ->
                    viewModel.getPlayerByName(name)?.let { holeTeam.add(it) }
                    Log.d("duo${viewModel.getPlayerByName(name)}", "observe")
                }
            }
        }

        binding.buttonDrawAgain.setOnClickListener {
            teamA = mutableListOf()
            teamB = mutableListOf()
            val drawNames = names.let { it1 -> viewModel.drawOneTeam(it1) }
            binding.textViewTeamOne.text = "Team One"//drawNames.toString()

            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.IO) {
                    drawNames.forEach { name ->
                        viewModel.getPlayerByName(name)?.let { teamA.add(it) }
                    }
                    teamB = holeTeam.filterNot { teamA.contains(it) } as MutableList<Player>
                    // Really hot fix xd
                    viewModel.getPlayerByName("Pati")
                    teamA.forEach {
                        Log.d("e_${it}", "TeamAAA")
                    }
                    teamB.forEach {
                        Log.d("e_${it}", "TeamBBB")
                    }
                }
            }
        }

        binding.textViewTeamTwo.text = "Team Two"//names.toString()

        return binding.root
    }

}