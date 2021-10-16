package com.papplications.volleyballteam.app.di

import com.papplications.volleyballteam.app.player.viewmodel.PlayerViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val playerModule = module {
    viewModel { PlayerViewModel(application = androidApplication()) }
}