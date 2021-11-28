package com.papplications.volleyballteam.app.feature.draw.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.papplications.volleyballteam.app.match.adapters.TeamAdapter
import com.papplications.volleyballteam.app.player.model.Player
import com.papplications.volleyballteam.app.player.viewmodel.PlayerViewModel
import com.papplications.volleyballteam.databinding.FragmentChooseManualyTeammatesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import kotlin.random.Random

class ChooseManuallyTeammatesFragment : Fragment() {

    private val viewModel: PlayerViewModel by inject()
    private val args: ChooseManuallyTeammatesFragmentArgs by navArgs()
    private lateinit var _binding: FragmentChooseManualyTeammatesBinding
    private val binding get() = _binding
    private lateinit var holeTeam: MutableList<Player>
    private lateinit var teamA: MutableList<Player>
    private lateinit var teamB: MutableList<Player>
    private var chooseFirst = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChooseManualyTeammatesBinding.inflate(inflater, container, false)

        val names = args.playersNames.names
        teamA = mutableListOf()
        teamB = mutableListOf()
        holeTeam = mutableListOf()

        val adapterTeamALeader = TeamAdapter()
        val recyclerViewTeamALeader = binding.recyclerViewTeamALeader
        recyclerViewTeamALeader.adapter = adapterTeamALeader
        recyclerViewTeamALeader.layoutManager = LinearLayoutManager(requireContext())

        val adapterTeamBLeader = TeamAdapter()
        val recyclerViewTeamBLeader = binding.recyclerViewTeamBLeader
        recyclerViewTeamBLeader.adapter = adapterTeamBLeader
        recyclerViewTeamBLeader.layoutManager = LinearLayoutManager(requireContext())

        val adapterTeamA = TeamAdapter()
        val recyclerViewTeamA = binding.recyclerViewTeamAChosen
        recyclerViewTeamA.adapter = adapterTeamA
        recyclerViewTeamA.layoutManager = LinearLayoutManager(requireContext())

        val adapterTeamB = TeamAdapter()
        val recyclerViewTeamB = binding.recyclerViewTeamBChosen
        recyclerViewTeamB.adapter = adapterTeamB
        recyclerViewTeamB.layoutManager = LinearLayoutManager(requireContext())

        val adapterHoleTeam = TeamAdapter()
        val recyclerViewHoleTeam = binding.recyclerViewHoleTeam
        recyclerViewHoleTeam.adapter = adapterHoleTeam
        recyclerViewHoleTeam.layoutManager = GridLayoutManager(requireContext(), 3)

        viewModel.updateAdapters.observe(viewLifecycleOwner, {
            adapterTeamALeader.setData(
                teamA, "1", args.showAvatars,
                chosenTeamsLists = false,
                holeTeamList = false,
                adapterTeamA,
                adapterTeamB,
                adapterHoleTeam,
                chooseFirst
            )
            adapterTeamBLeader.setData(
                teamB, "2", args.showAvatars,
                chosenTeamsLists = false,
                holeTeamList = false,
                adapterTeamA,
                adapterTeamB,
                adapterHoleTeam,
                chooseFirst
            )
            adapterTeamA.setData(
                teamA, "1", args.showAvatars,
                chosenTeamsLists = true,
                holeTeamList = false,
                adapterTeamA,
                adapterTeamB,
                adapterHoleTeam,
                chooseFirst
            )
            adapterTeamB.setData(
                teamB, "2", args.showAvatars,
                chosenTeamsLists = true,
                holeTeamList = false,
                adapterTeamA,
                adapterTeamB,
                adapterHoleTeam,
                chooseFirst
            )
            adapterHoleTeam.setData(
                holeTeam, "", args.showAvatars,
                chosenTeamsLists = false,
                holeTeamList = true,
                adapterTeamA,
                adapterTeamB,
                adapterHoleTeam,
                chooseFirst
            )
        })

        binding.buttonDrawChoosingPlayers.setOnClickListener {
            teamA = mutableListOf()
            teamB = mutableListOf()
            holeTeam = mutableListOf()
            val tempTeamOfTwoPlayers: MutableList<Player> = mutableListOf()

            val drawTwoPlayer = names.let { it1 -> viewModel.drawTwoPlayers(it1) }

            CoroutineScope(Dispatchers.IO).launch {
                withContext(Dispatchers.IO) {
                    viewModel.getPlayerByName(drawTwoPlayer[0])?.let { it1 -> teamA.add(it1) }
                    viewModel.getPlayerByName(drawTwoPlayer[1])?.let { it1 -> teamB.add(it1) }
                    drawTwoPlayer.forEach { name ->
                        viewModel.getPlayerByName(name)?.let { tempTeamOfTwoPlayers.add(it) }
                    }
                    names.forEach { name ->
                        viewModel.getPlayerByName(name)?.let { holeTeam.add(it) }
                    }
                    Log.d(holeTeam.toString(), "aaaa")
                    holeTeam =
                        holeTeam.filterNot { tempTeamOfTwoPlayers.contains(it) } as MutableList<Player>

                    chooseFirst = Random.nextBoolean()
                    if (chooseFirst) {
                        binding.textViewTeamOne.setTextColor(Color.parseColor("#00FF00"));
                        binding.textViewTeamTwo.setTextColor(Color.parseColor("#000000"));
                    } else {
                        binding.textViewTeamOne.setTextColor(Color.parseColor("#000000"));
                        binding.textViewTeamTwo.setTextColor(Color.parseColor("#00FF00"));
                    }

                    viewModel.makeUpdateOnAdapters()
                }
            }
        }

        return binding.root
    }

}