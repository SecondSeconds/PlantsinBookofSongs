package com.example.plantsinbookofsongs.destination

sealed class FoundDestination(val route: String) {
    data object Found : FoundDestination("found")
    data object Detail : FoundDestination("detail/{categoryId}") {
        fun createRoute(categoryId: Int) = "detail/$categoryId"
    }
    data object Poem: FoundDestination("poem/{categoryId}/{poemId}"){
        fun createRoute(poemId: Int,categoryId: Int)="poem/$categoryId/$poemId"
    }
}
