package com.app.pokedex.DI

import com.app.pokedex.data.repositories.RepositoriesPokemonDetails
import com.app.pokedex.data.repositories.RepositoriesPokemons
import com.app.pokedex.data.network.GETPokemonDetails
import com.app.pokedex.data.network.GETPokemons
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePokemonApi(retrofit: Retrofit): GETPokemons {
        return retrofit.create(GETPokemons::class.java)
    }

    @Provides
    @Singleton
    fun providePokemonDetailsApi(retrofit: Retrofit): GETPokemonDetails {
        return retrofit.create(GETPokemonDetails::class.java)
    }

    @Provides
    @Singleton
    fun provideRepositoriesPokemons(api: GETPokemons): RepositoriesPokemons {
        return RepositoriesPokemons(api)
    }

    @Provides
    @Singleton
    fun provideRepositoriesPokemonDetails(api: GETPokemonDetails): RepositoriesPokemonDetails {
        return RepositoriesPokemonDetails(api)
    }
}
