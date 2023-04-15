package ru.vdh.todocompose.di

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class NavigationModule {

    @Provides
    fun providesSupportFragmentManager(activity: Activity) =
        (activity as AppCompatActivity).supportFragmentManager

    @Provides
    fun providesNavController(navHostFragment: NavHostFragment) =
        navHostFragment.navController
}
