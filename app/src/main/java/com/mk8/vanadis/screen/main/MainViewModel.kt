package com.mk8.vanadis.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mk8.data.MainState
import com.mk8.data.ScreenState
import com.mk8.data.fold
import com.mk8.domain.usecase.GetWebListUseCase
import com.mk8.vanadis.screen.base.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val getWebListUseCase: GetWebListUseCase
) : BaseViewModel() {

    private val _mainState = MutableLiveData<ScreenState<MainState>>()
    val mainState: LiveData<ScreenState<MainState>>
        get() = _mainState

    override fun onCreate() {
        super.onCreate()
        provideWebList()
    }

    fun provideWebList() {

        _mainState.postValue(ScreenState.Loading)

        uiScope.launch {

            val result = uiScope.async { withContext(ioContext) { getWebListUseCase.execute() } }

            result.await().fold(
                { message ->
                    _mainState.postValue(ScreenState.Render(MainState.ShowErrorMessage(message)))
                },
                { webList ->
                    _mainState.postValue(ScreenState.Render(MainState.DrawItems(webList)))
                }
            )
        }
    }
}