package com.mk8.vanadis

import android.os.Build
import com.mk8.data.*
import com.mk8.domain.usecase.GetWebListUseCase
import com.mk8.vanadis.screen.main.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class MainViewModelTest {

    private val getWebListUseCase = mockk<GetWebListUseCase>()
    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(getWebListUseCase)
        mainViewModel.init()
    }

    @Test
    fun testGetWebList_Positive() {
        coEvery { getWebListUseCase.execute() } returns value(
            listOf(
                ItemWeb("As", "www.as.com"),
                ItemWeb("Marca", "www.marca.com"),
                ItemWeb("Amazon", "www.amazon.com")
            )
        )
        mainViewModel.mainState.observeForever { }
        mainViewModel.provideWebList()

        coVerify {
            (mainViewModel.mainState.value == ScreenState.Loading)
            getWebListUseCase.execute()
            (mainViewModel.mainState.value == ScreenState.Render(MainState.DrawItems(listOf())))
        }
    }

    @Test
    fun testGetWebList_Negative() {
        coEvery { getWebListUseCase.execute() } returns error("")
        mainViewModel.mainState.observeForever { }
        mainViewModel.provideWebList()

        coVerify {
            (mainViewModel.mainState.value == ScreenState.Render(MainState.DrawItems(listOf())))
            getWebListUseCase.execute()
            (mainViewModel.mainState.value == ScreenState.Render(MainState.ShowErrorMessage("")))

        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }
}