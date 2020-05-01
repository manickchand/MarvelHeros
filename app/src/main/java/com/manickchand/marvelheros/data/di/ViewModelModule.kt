package com.manickchand.marvelheros.data.di

import com.manickchand.marvelheros.heros.HerosViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HerosViewModel(get()) }
}