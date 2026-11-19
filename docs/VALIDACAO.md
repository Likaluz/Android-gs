# Validação executada

**Data:** 18/11/2026, horário de Brasília.  
**Responsável pelo projeto:** Lucas Domingues — RM 553304.

## Resultado

| Verificação | Resultado |
|---|---|
| `assembleDebug` | Aprovado; APK gerado |
| `testDebugUnitTest` | 3 testes, 0 falhas |
| `lintDebug` | 0 erros; 7 avisos |
| `connectedDebugAndroidTest` em tela padrão | 4 testes, 0 falhas |
| `connectedDebugAndroidTest` em tela pequena e fonte ampliada | 4 testes, 0 falhas |
| Inspeção visual dos prints | Realizada em tela padrão e pequena |
| Documento de pitch em PDF | 5 páginas renderizadas e inspecionadas |
| ZIP | Integridade e presença de arquivos essenciais verificadas pelo empacotador |

## Ambiente

- Windows, JDK 21.0.10, Gradle 8.11.1 e AGP 8.9.2.
- SDK Android 35 para compilação, minSdk 26 e targetSdk 35.
- Emulador isolado Android 16/API 36, Google APIs, x86_64.
- Tela padrão: 1080 × 2400 pixels, densidade 420 dpi, fonte 100%.
- Tela pequena: 720 × 1280 pixels, densidade 320 dpi, largura lógica de 360 dp, fonte 130%.

Na revisão visual da tela pequena foi identificado corte em um subtítulo do cabeçalho. O subtítulo foi removido e os testes/capturas foram refeitos. A versão final possui título simples no `TopAppBar`.

## O que os testes verificam

### Repositório

Quantidade mínima de registros e destaques, IDs únicos, categorias com resultados, uso de múltiplas categorias no mesmo registro, retorno de Todas e busca combinada com categoria, ignorando acentos e maiúsculas.

### Interface

Exibição inicial, lista completa com oito registros, seleção de Alimentação com dois resultados, abertura de detalhes e retorno, busca sem resultados e recuperação da lista ao limpar os filtros. Os testes percorrem a lista usando a rolagem de `LazyColumn`.

## Relatórios e evidências

- [Testes unitários](relatorios/testes-unitarios.xml).
- [Testes de interface em tela padrão](relatorios/testes-interface.xml).
- [Testes de interface em tela pequena](relatorios/testes-interface-tela-pequena.xml).
- [Relatório do lint](relatorios/lint.txt).
- [Capturas padrão](screenshots/).
- [Capturas com fonte ampliada](screenshots-small/).

Os sete avisos do lint dizem respeito ao targetSdk fixado em 35, versões mais recentes de dependências e regras de extração de backup. A aplicação não armazena cadastro ou informações pessoais e usa `allowBackup=false`. Não foi alterado o baseline para esconder erros; o lint conclui com zero erros.

## Limites da verificação

Não foram testados todos os modelos de aparelhos nem todas as versões Android suportadas. Não houve sessão de usabilidade com moradores, auditoria completa de acessibilidade ou validação do impacto social. A orientação paisagem e recursos avançados de acessibilidade não tiveram teste específico. Os dados são fictícios, e a validação de funcionalidades não equivale à validação de serviços reais.

## Entrega externa

O pacote não inclui URL de GitHub nem vídeo publicado. Essas etapas exigem publicação na conta do aluno e a gravação pessoal prevista no enunciado. O documento de pitch contém um roteiro e não substitui o vídeo. Não foi feita submissão ao portal FIAP.
