package ru.vdh.cleanarch.navigation

sealed class Screen(val route:String){
    object ToDoListScreen: Screen("ToDoListScreen")
    object ToDoTaskScreen: Screen("ToDoTaskScreen")
    object SecondFeatureNavigationRoute: Screen("SecondFeatureNavigation")
}
