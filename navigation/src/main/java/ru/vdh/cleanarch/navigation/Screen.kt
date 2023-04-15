package ru.vdh.cleanarch.navigation

sealed class Screen(val route:String){
    object NewFeatureScreen: Screen("NewFeatureScreen")
    object SecondFeatureScreen: Screen("SecondFeatureScreen?info={info}")
    object SecondFeatureNavigationRoute: Screen("SecondFeatureNavigation")
}
