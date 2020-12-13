package nh.hackaton.forkids.di

import nh.hackaton.forkids.ui.viewmodel.AccountViewModel
import nh.hackaton.forkids.ui.viewmodel.EduViewModel
import nh.hackaton.forkids.ui.viewmodel.SharedViewModel
import nh.hackaton.forkids.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UserViewModel(get()) }
    viewModel { AccountViewModel(get()) }
    viewModel { EduViewModel(get()) }
    viewModel { SharedViewModel() }
}