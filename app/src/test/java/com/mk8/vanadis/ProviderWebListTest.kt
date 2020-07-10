package com.mk8.vanadis

import android.os.Build
import com.mk8.domain.cache.CacheWebList
import com.mk8.domain.provider.ProviderWebList
import com.mk8.domain.repository.RepositoryWebList
import io.mockk.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class ProviderWebListTest {

    private val repositoryWebList = mockk<RepositoryWebList>()
    private val cacheWebList = mockk<CacheWebList>()
    private lateinit var providerWebList: ProviderWebList

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        providerWebList = ProviderWebList(repositoryWebList, cacheWebList)
    }

    @Test
    fun getCacheData() {
        every { cacheWebList.load() } returns listOf()

        coVerify {
            repositoryWebList.getWebList() wasNot Called
        }
        assert(cacheWebList.load() != null)
    }

    @Test
    fun getRepositoryData() {
        every { cacheWebList.load() } returns null

        coVerify {
            repositoryWebList.getWebList()
            cacheWebList.save(listOf())
        }
    }

    @After
    fun after() {
        stopKoin()
    }
}