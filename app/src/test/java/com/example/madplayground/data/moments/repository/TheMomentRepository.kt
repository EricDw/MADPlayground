package com.example.madplayground.data.moments.repository

import com.example.madplayground.cache.moments.fakes.FakeMomentDao
import com.example.madplayground.cache.moments.source.MomentCacheMapperImpl
import com.example.madplayground.cache.moments.source.LocalMomentDataSourceImpl
import com.example.madplayground.data.moments.fakes.FakeMoment
import com.example.madplayground.domain.moments.models.Moment
import com.example.madplayground.domain.moments.repository.MomentRepository
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

        repository = MomentRepositoryImpl(
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
            val expected = listOf<Moment>(
                FakeMoment.MOMENT_1
            )

            val actual: List<Moment>?

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
            val expected = listOf<Moment>(
                FakeMoment.MOMENT_1
            )

            val actual: List<Moment>?

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