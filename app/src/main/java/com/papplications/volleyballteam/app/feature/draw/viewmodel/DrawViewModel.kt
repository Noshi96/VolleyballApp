package com.papplications.volleyballteam.app.feature.draw.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DrawViewModel(): ViewModel() {

    private val _chipCategories = MutableLiveData<MutableList<String>>()
    val chipCategories: LiveData<MutableList<String>> = _chipCategories

    private val _checkedChipsIdsList = MutableLiveData<List<Int>>()
    var checkedChipsIdsList: LiveData<List<Int>> = _checkedChipsIdsList

    private val _checkedChipsNamesList = MutableLiveData<List<String>>()
    var checkedChipsNamesList: LiveData<List<String>> = _checkedChipsNamesList


    fun onSelectedChipChangesSendToViewModel(
        checkedChipIds: List<Int>,
        currentCheckedNames: MutableList<String>
    ) {
        _checkedChipsIdsList.value = checkedChipIds
        _checkedChipsNamesList.value = currentCheckedNames
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun drawOneTeam(names: List<String>): MutableList<String>{
        val list : MutableList<String> = ArrayList()
        names.toMutableSet().let { it1 -> list.addAll(it1) }
        val randomElements2 = list.asSequence().shuffled().take((names.size.div(2))).toList()
        list.removeIf { i -> randomElements2.contains(i) }
        return list
    }
}