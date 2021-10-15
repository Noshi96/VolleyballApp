package com.papplications.volleyballteam.app.di

import com.papplications.volleyballteam.app.player.viewmodel.MatchViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val matchModule = module {
    viewModel { MatchViewModel(application = androidApplication()) }
}