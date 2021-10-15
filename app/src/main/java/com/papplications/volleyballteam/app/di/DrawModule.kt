package com.papplications.volleyballteam.app.di

import com.papplications.volleyballteam.app.feature.draw.viewmodel.DrawViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val drawModule = module {
    viewModel { DrawViewModel() }
}