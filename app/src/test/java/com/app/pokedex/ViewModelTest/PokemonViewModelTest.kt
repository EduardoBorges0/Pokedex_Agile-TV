package com.app.pokedex.ViewModelTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.pokedex.data.model.entities.PokemonsEntities
import com.app.pokedex.data.model.entities.Results
import com.app.pokedex.data.repositories.RepositoriesPokemons
import com.app.pokedex.presentation.viewmodel.PokemonsViewModel
import io.mockk.*
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PokemonsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule() // Permite testes com LiveData no ambiente de teste da JVM

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var mockRepositories: RepositoriesPokemons
    private lateinit var viewModel: PokemonsViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockRepositories = mockk(relaxed = true) // Relaxado para evitar erro de mock
        viewModel = PokemonsViewModel(mockRepositories)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getAllPokemons should update pokemonsLive`() = runTest {
        // Arrange
        val fakeResponse = PokemonsEntities(
            count = 5,
            next = null,
            previous = null,
            results = listOf(
                Results("pikachu", "uri1"),
                Results("bulbasaur", "uri2"),
                Results("charmander", "uri3"),
                Results("charizard", "uri4"),
                Results("squirtle", "uri5")
            )
        )

        coEvery { mockRepositories.getAllPokemons() } returns fakeResponse

        // Act
        viewModel.getAllPokemons()
        advanceUntilIdle()

        // Assert
        assertEquals(fakeResponse, viewModel.pokemonsLive.value)
    }

    @Test
    fun `searchPokemons should filter results based on search value`() = runTest {
        // Arrange
        val fakeResponse = PokemonsEntities(
            count = 5,
            next = null,
            previous = null,
            results = listOf(
                Results("pikachu", "uri1"),
                Results("bulbasaur", "uri2"),
                Results("charmander", "uri3"),
                Results("charizard", "uri4"),
                Results("squirtle", "uri5")
            )
        )
        coEvery { mockRepositories.getAllPokemons() } returns fakeResponse

        // Act
        viewModel.getAllPokemons()
        advanceUntilIdle() // Aguarda a conclusão da corrotina e a atualização do LiveData

        // Agora, o LiveData deve estar atualizado
        viewModel.searchPokemons("pikachu")

        // Assert
        assertEquals(1, viewModel.searchResults.value?.size)
        assertEquals("pikachu", viewModel.searchResults.value?.first()?.name)
    }

    @Test
    fun `searchPokemons should return empty list if no match is found`() {
        // Arrange
        val fakeResponse = PokemonsEntities(
            count = 5,
            next = null,
            previous = null,
            results = listOf(
                Results("pikachu", "uri1"),
                Results("bulbasaur", "uri2"),
                Results("charmander", "uri3"),
                Results("charizard", "uri4"),
                Results("squirtle", "uri5")
            )
        )
        coEvery { mockRepositories.getAllPokemons() } returns fakeResponse
        viewModel.getAllPokemons()

        // Act
        viewModel.searchPokemons("mewtwo")

        // Assert
        assertTrue(viewModel.searchResults.value?.isEmpty() == true)
    }

}
