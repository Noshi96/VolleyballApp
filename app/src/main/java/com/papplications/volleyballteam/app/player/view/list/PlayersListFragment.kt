package com.papplications.volleyballteam.app.player.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.papplications.volleyballteam.app.player.adapters.ListAdapter
import com.papplications.volleyballteam.app.player.viewmodel.PlayerViewModel
import com.papplications.volleyballteam.databinding.FragmentPlayersListBinding
import org.koin.android.ext.android.inject

class PlayersListFragment : Fragment() {

    private val viewModel: PlayerViewModel by inject()
    private lateinit var _binding: FragmentPlayersListBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayersListBinding.inflate(inflater, container, false)

        val adapter = ListAdapter()
        val recyclerView = binding.recyclerViewPlayersChoose
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getAllPlayers.observe(viewLifecycleOwner, { players ->
            adapter.setData(players)
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}