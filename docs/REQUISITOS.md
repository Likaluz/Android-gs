# Rastreabilidade da atividade Android

Desenvolvedor: Lucas Domingues — RM 553304.

| Critério do enunciado | Implementação |
|---|---|
| Kotlin, Compose e Material 3 | Módulo `app`, plugin Compose e tema Material 3 |
| Lista vertical com pelo menos 5 registros | 8 objetos em `IniciativaRepository`; `LazyColumn.items` em `HomeScreen` |
| Título, ícone/imagem e 2 informações extras | `IniciativaCard`: imagem, título, resumo, local e horário |
| LazyRow de categorias, 5 temáticas e Todas | 6 categorias temáticas e `TODAS`, definidas em `Categoria` |
| Categoria selecionada destacada | `CategoriaChip` usa `FilterChip(selected)` |
| Filtro automático e tipado | `repository.filtrar`, comparação por enum e `categoria in categorias` |
| remember e mutableStateOf | Estado de categoria e busca em `HomeScreen` |
| 3 destaques com imagem, título e complemento | `DestaqueCard` e `repository.obterDestaques()` |
| Destaques derivados dos dados existentes | `registros.filter { it.destaque }` |
| data class e construtor primário | `Iniciativa` |
| Registro em uma ou mais categorias | `categorias: List<Categoria>` |
| Booleano de destaque | `destaque: Boolean` |
| Classe de repositório, dados em memória | `IniciativaRepository` |
| Scaffold e TopAppBar | Telas `HomeScreen` e `DetailScreen` |
| Column, Row, Card, Spacer, padding, Text, Icon, Image | Telas e componentes do pacote `ui` |
| Pelo menos 3 composables reutilizáveis separados | `IniciativaCard`, `CategoriaChip`, `DestaqueCard` e `InfoLine` |
| Separação de responsabilidades | Packages `model`, `data`, `ui/screens`, `ui/components`, `ui/theme` |
| README com nome completo e RM | Lucas Domingues, RM 553304 |
| Prints reais | `docs/screenshots` |
| ZIP e projeto completo | Pacote em `output` |
| Link GitHub | Depende de publicação na conta do aluno |
| Vídeo pitch | Roteiro preparado; gravação e publicação pelo aluno |

## Tema adotado

CitizenTech, conforme briefing geral. O MVP escolhido é um catálogo offline de iniciativas comunitárias para moradores e agentes locais. A proposta responde ao acesso à informação e à inclusão, com conexão aos ODS 4, 10 e 11.

## Limites da demonstração

Os registros são fictícios. Não existe integração com serviços públicos, geolocalização, validação de estabelecimentos, API, banco remoto ou envio de inscrições. Não foram realizados testes de impacto social com moradores. A medição de impacto e a atualização do catálogo são propostas de evolução descritas no pitch.
