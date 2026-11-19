# Bairro Vivo — documento de pitch

**Equipe:** Bairro Vivo  
**Integrante:** Lucas Domingues — RM 553304  
**Curso:** Sistemas de Informação — FIAP  
**Tema:** CitizenTech — Global Solution 2026

## 1. Objetivo e cidadão beneficiado

Facilitar a descoberta de iniciativas comunitárias e de informações de participação em um catálogo simples, disponível offline. O público inicial são moradores e agentes comunitários que orientam pessoas com menor familiaridade digital.

A hipótese de problema é que informações sobre ações de bairro estão dispersas em cartazes, mensagens e canais de diferentes organizações. O aplicativo centraliza exemplos de iniciativas, permite filtrar por necessidade e mostra informações de local e horário. Essa hipótese ainda precisa ser validada no território escolhido.

## 2. Oportunidade e dimensão do contexto

Segundo a PNAD Contínua TIC, **90,5% das pessoas de 10 anos ou mais usaram a internet em 2025**, totalizando **168,7 milhões de pessoas**. A diferença para 100% mostra que o acesso não é universal. Esse dado nacional contextualiza a escolha de um catálogo leve e offline, mas **não mede a demanda pelo Bairro Vivo**, nem prova a dispersão das informações comunitárias.

Fonte primária: [IBGE — proporção de usuários da internet em 2025](https://agenciadenoticias.ibge.gov.br/agencia-noticias/2012-agencia-de-noticias/noticias/47408-proporcao-de-usuarios-da-internet-no-pais-ultrapassou-90-da-populacao-de-10-anos-ou-mais-em-2025), consultada em 18/11/2026.

Para dimensionar o problema local, o próximo passo é entrevistar 10 moradores e 3 representantes de iniciativas, mapear quantas fontes uma pessoa consulta e medir o tempo para encontrar uma atividade adequada. Esses participantes e resultados são um **plano**, não uma pesquisa concluída.

## 3. Benchmarking

| Alternativa | Proposta observada | Escolha do Bairro Vivo |
|---|---|---|
| Atados | Conecta pessoas a vagas de voluntariado em organizações | O MVP privilegia a descoberta de iniciativas para moradores que desejam participar ou acessar uma atividade |
| Portal SP156 | Canal de informações e serviços da Prefeitura de São Paulo | O MVP organiza exemplos de iniciativas comunitárias em um catálogo local |
| Divulgação por cartazes e mensagens | Alternativa de divulgação local, a verificar nas entrevistas | Reunir informações padronizadas, com busca e categorias |

Fontes: [Atados — quero ser voluntário](https://atados.com.br/queroservoluntario) e [Portal SP156](https://sp156.prefeitura.sp.gov.br/portal), consultadas em 18/11/2026. A comparação considera a finalidade apresentada por cada serviço; não afirma que esses produtos carecem de funcionalidades específicas. Não há vínculo ou integração com eles.

## 4. Escopo entregue

O aplicativo possui oito iniciativas fictícias, seis categorias e uma opção Todas. A tela inicial reúne busca, filtros, três destaques e uma lista dinâmica. Os detalhes exibem descrição, local, horário e condições de acesso. A busca ignora acentos e combina texto com categoria. Um estado vazio ajuda a refazer a consulta.

O MVP funciona sem login e sem internet após a instalação. As ilustrações estão incluídas no aplicativo. Os dados não representam serviços em operação, e o app não faz reservas, doações ou inscrições reais.

Requisitos não funcionais adotados: ausência de permissões de rede/localização, uso de dados exclusivamente locais, componentes Material 3, conteúdo com rolagem e largura limitada em telas grandes. Acessibilidade avançada e validação com usuários continuam no plano de evolução.

## 5. Arquitetura e tecnologias

```text
MainActivity → HomeScreen / DetailScreen
                   ↓
         Componentes Compose reutilizáveis
                   ↑
       IniciativaRepository → List<Iniciativa>
                               └── List<Categoria> + destaque
```

Android nativo com Kotlin e Jetpack Compose; Material Design 3 para componentes; Gradle para build. O repositório em memória concentra dados e filtros. O estado da interface é gerenciado por `remember` e `mutableStateOf`. A recomposição atualiza os resultados ao alterar categoria ou consulta. Não há infraestrutura de servidor nesta versão.

Testes: JUnit para regras do repositório; Compose UI Test para fluxos da aplicação; Android Lint para análise estática. Os resultados de execução estão em `VALIDACAO.md`.

## 6. Demonstração da aplicação

1. Apresentar a tela inicial e o aviso de dados demonstrativos.
2. Percorrer os três destaques horizontalmente.
3. Selecionar Alimentação: a lista principal mostra duas iniciativas.
4. Voltar a Todas e buscar `geracoes`: o resultado é Conecta gerações.
5. Abrir uma iniciativa e mostrar descrição, local e horário.
6. Buscar um termo inexistente e usar Limpar filtros.
7. Explicar que o catálogo e as imagens funcionam offline.

Os prints da aplicação estão em `screenshots/` e no README.

## 7. Viabilidade e evolução

Uma implantação piloto poderia envolver uma associação de bairro responsável por validar as informações e representantes das iniciativas para revisar alterações. Antes do lançamento real, seria necessário obter autorização dos responsáveis, padronizar atualizações e definir como retirar informações desatualizadas.

O modelo proposto é acesso gratuito ao cidadão e manutenção por parcerias com associações, organizações sociais ou patrocinadores locais. Custos e disposição desses parceiros para financiar o serviço ainda precisam ser apurados. Não há receita ou parceria contratada.

Próximas etapas: entrevistas, piloto com catálogo validado, definição de responsáveis e atualização offline com data de revisão. Um painel de curadoria e sincronização poderia ser implementado após validação. Nenhum desses recursos é apresentado como entregue.

## 8. Impacto e ODS

ODS 4: descoberta de oportunidades educativas. ODS 10: informação sobre inclusão e acessibilidade. ODS 11: fortalecimento do acesso a iniciativas comunitárias.

Plano de medição: comparar tempo para encontrar uma iniciativa antes/depois do protótipo; medir conclusão de tarefas sem ajuda; contar iniciativas com informações revisadas. Meta inicial de usabilidade proposta: pelo menos 8 de 10 participantes encontrarem uma iniciativa em até 60 segundos. É uma meta de teste, não um resultado obtido. A participação real nas iniciativas exigiria validação posterior com as organizações.

## 9. Conclusão

O Bairro Vivo demonstra uma aplicação CitizenTech pequena e funcional: informações comunitárias organizadas, categorias para reduzir a busca e detalhes claros. O MVP comprova o funcionamento técnico do catálogo; a necessidade local, a curadoria e o impacto devem ser validados antes de uma implantação real.

## Roteiro de vídeo — 4min50s

| Tempo | Conteúdo |
|---|---|
| 0:00–0:25 | Aparecer em câmera, dizer nome, RM, nome do projeto e tema CitizenTech |
| 0:25–0:55 | Explicar cidadão beneficiado, hipótese do problema e dado do IBGE com fonte |
| 0:55–1:15 | Explicar alternativas, diferença de foco e funcionamento offline |
| 1:15–4:00 | Demonstrar o app: catálogo, carrosséis, filtro, busca, detalhes, retorno e estado vazio |
| 4:00–4:25 | Mostrar rapidamente modelos, enum e repositório; explicar arquitetura e testes |
| 4:25–4:50 | Apresentar ODS, plano de validação e conclusão |

São **2min45s de demonstração do app**, além da apresentação técnica, atendendo à orientação de dedicar pelo menos metade do vídeo ao que foi produzido. Gravar na horizontal, no mínimo em HD, com imagem e voz do aluno; narrar pessoalmente e publicar no YouTube com acesso público.

**Link do vídeo:** pendente de gravação e publicação pelo aluno.  
**Link GitHub:** pendente de publicação na conta do aluno.
