Tecnologias usadas no projeto: 
 - Jetpack Compose
 - RetroFit
 - LiveData
 - JUnit
 - Mockk
 - Navigation
 - MediaPlayer
   
Arquitetura usada: MVVM

O design do projeto teve o intuito de trazer a energia dos jogos que eram feitos ali nos anos 2000, anos 90, desde a música, até os efeitos de apertar nos botões.

Esses botões tem cada um, uma função:

HomeScreen:
- Seta pra cima: Ir para detailsScreen, lá você verá os detalhes do pokemon que você selecionou na HomeScreen.
- Seta para baixo: Ir para SearchPokemons.
- Seta para esquerda: Voltar para ver um Pokemon.
- Seta para Direita: Avançar para ver mais Pokemons.
  
SearchPokemon:
- Seta pra cima: Ir para HomeScreen.
- Seta para baixo: Ir para DetailScreen, lá você verá os detalhes do seu pokemon escolhido, caso não pesquisa nenhum, ele irá mostrar o primeiro da lista, nesse caso o bubassauro.
- Seta para esquerda: Voltar para ver um Pokemon.
- Seta para Direita: Avançar para ver mais Pokemons.

DetailsPokemon:
- Seta pra cima: Ir para SearchPokemon.
- Seta para baixo: Ir para HomeScreen.
- Seta para esquerda: Sem função.
- Seta para Direita: Sem função.

PDF apresentando cada tela do aplicativo, no total são apenas 3: 
- [Apresentação das Telas do Aplicativo Pokedex.pdf](https://github.com/user-attachments/files/18726779/Apresentacao.das.Telas.do.Aplicativo.Pokedex.pdf)




