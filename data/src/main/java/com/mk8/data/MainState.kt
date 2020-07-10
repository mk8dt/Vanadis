package com.mk8.data

sealed class MainState {
    class DrawItems(val items: List<ItemWeb>) : MainState()
    class ShowErrorMessage(val message: String) : MainState()
}