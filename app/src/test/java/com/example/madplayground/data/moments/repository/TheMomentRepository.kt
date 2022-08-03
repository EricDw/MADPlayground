package com.example.madplayground.data.moments.repository

import com.example.cache.moments.source.LocalMomentDataSourceImpl
import com.example.cache.moments.source.MomentCacheMapperImpl
import com.example.core.moments.repository.MomentRepository
import com.example.madplayground.cache.moments.fakes.FakeMomentDao
import com.example.madplayground.data.moments.fakes.FakeMoment
import com.example.madplayground.helpers.runUnconfinedTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TheMomentRepository {

    private lateinit var repository: MomentRepository

    @Before
    fun setUp() {

        val dao = FakeMomentDao()

        val cache = LocalMomentDataSourceImpl(
            mapper = MomentCacheMapperImpl(),
            dao = dao
        )

        repository = com.example.data.moments.repository.MomentRepositoryImpl(
            localRepository = cache
        )

    }

    @After
    fun tearDown() {
        /* no-op */
    }

    @Test
    fun `Can add Moments`() =
        runUnconfinedTest {

            // Arrange
            val expected = listOf<com.example.core.moments.models.Moment>(
                FakeMoment.MOMENT_1
            )

            val actual: List<com.example.core.moments.models.Moment>?

            // Act
            repository.addMoment(FakeMoment.MOMENT_1)

            actual = repository.retrieveAllMoments()

            // Assert
            Assert.assertEquals(
                "Expected non empty list",
                expected,
                actual
            )

        }

    @Test
    fun `Overwrites previously added Moments`() =
        runUnconfinedTest {

            // Arrange
            val expected = listOf<com.example.core.moments.models.Moment>(
                FakeMoment.MOMENT_1
            )

            val actual: List<com.example.core.moments.models.Moment>?

            // Act
            repository.addMoment(FakeMoment.MOMENT_1)
            repository.addMoment(FakeMoment.MOMENT_1)

            actual = repository.retrieveAllMoments()

            // Assert
            Assert.assertEquals(
                "Expected non empty list",
                expected,
                actual
            )

        }

}