package br.com.bairrovivo.data

import br.com.bairrovivo.R
import br.com.bairrovivo.model.Categoria
import br.com.bairrovivo.model.Categoria.*
import br.com.bairrovivo.model.Iniciativa

/** Base fictícia para demonstração acadêmica. Não representa serviços em operação. */
class IniciativaRepository {
    private val registros = listOf(
        Iniciativa(1, "Mesa compartilhada", "Alimento bom encontra quem precisa.",
            "Uma rede comunitária para aproximar doações de alimentos e famílias. O projeto propõe pontos de retirada e orientação sobre aproveitamento integral. Nesta demonstração, nenhuma reserva ou doação real é realizada.",
            "Centro comunitário • Vila Aurora", "Seg a sex • 11h às 14h", "Gratuito • por ordem de chegada",
            listOf(ALIMENTACAO, BEM_ESTAR), true, R.drawable.arte_alimento),
        Iniciativa(2, "Conecta gerações", "Mais autonomia no mundo digital.",
            "Oficinas presenciais para pessoas idosas aprenderem a usar o celular, reconhecer golpes e acessar serviços digitais com autonomia. As atividades usam linguagem simples e apoio individual.",
            "Biblioteca comunitária • Vila Aurora", "Ter e qui • 14h às 16h", "Gratuito • leve seu celular, se tiver",
            listOf(INCLUSAO_DIGITAL, EDUCACAO), true, R.drawable.arte_digital),
        Iniciativa(3, "Caminhos para todos", "Um bairro que inclui cada pessoa.",
            "Encontros para identificar barreiras nas calçadas e compartilhar propostas de acessibilidade. O catálogo explica a iniciativa; não calcula rotas nem certifica a acessibilidade de vias.",
            "Praça das Flores • Vila Aurora", "Sábados • 9h às 11h", "Aberto à comunidade • ponto de encontro sinalizado",
            listOf(ACESSIBILIDADE), true, R.drawable.arte_acesso),
        Iniciativa(4, "Horta de vizinhança", "Cultivar juntos, colher melhor.",
            "Uma horta educativa para aproximar moradores da produção de alimentos, da compostagem e do cuidado coletivo. Os encontros incluem atividades de plantio e troca de conhecimentos.",
            "Espaço verde • Jardim União", "Sábados • 8h às 10h", "Gratuito • ferramentas compartilhadas",
            listOf(MEIO_AMBIENTE, ALIMENTACAO, EDUCACAO), false, R.drawable.arte_ambiente),
        Iniciativa(5, "Aprender em rede", "Conhecimento cresce quando é dividido.",
            "Rodas de estudo e reforço escolar com voluntários, voltadas a estudantes da comunidade. O objetivo é criar um espaço acolhedor para aprender e trocar experiências.",
            "Sala comunitária • Jardim União", "Seg e qua • 16h às 18h", "Gratuito • estudantes a partir de 12 anos",
            listOf(EDUCACAO, INCLUSAO_DIGITAL), false, R.drawable.arte_educacao),
        Iniciativa(6, "Recomeço circular", "Dar outro destino também é cuidar.",
            "Mutirões de troca, reparo e educação ambiental para reduzir o descarte de objetos. A proposta incentiva o reaproveitamento e a colaboração entre moradores.",
            "Galpão comunitário • Vila Aurora", "Primeiro sábado do mês • 10h às 15h", "Gratuito • leve objetos limpos e conservados",
            listOf(MEIO_AMBIENTE), false, R.drawable.arte_ambiente),
        Iniciativa(7, "Movimento na praça", "Bem-estar com espaço para todos.",
            "Atividades leves de convivência e movimento em grupo. A proposta incentiva vínculos comunitários e respeita diferentes ritmos e necessidades dos participantes.",
            "Praça do Encontro • Jardim União", "Ter e qui • 8h às 9h", "Gratuito • participação conforme sua condição",
            listOf(BEM_ESTAR, ACESSIBILIDADE), false, R.drawable.arte_bemestar),
        Iniciativa(8, "Primeiro clique", "O primeiro passo para se conectar.",
            "Introdução gratuita ao uso de computadores, pesquisa na internet e criação de currículos. A iniciativa foi pensada para quem tem pouco acesso a equipamentos e conectividade.",
            "Laboratório comunitário • Jardim União", "Sextas • 14h às 17h", "Gratuito • computadores disponíveis no local",
            listOf(INCLUSAO_DIGITAL, EDUCACAO), false, R.drawable.arte_digital)
    )

    fun obterTodas(): List<Iniciativa> = registros.toList()
    fun filtrar(categoria: Categoria, consulta: String = ""): List<Iniciativa> {
        val termo = normalizar(consulta.trim())
        return registros.filter {
            (categoria == TODAS || categoria in it.categorias) &&
                (termo.isEmpty() || normalizar("${it.titulo} ${it.resumo} ${it.local}").contains(termo))
        }
    }
    fun obterDestaques(): List<Iniciativa> = registros.filter { it.destaque }

    private fun normalizar(texto: String): String = java.text.Normalizer
        .normalize(texto, java.text.Normalizer.Form.NFD)
        .replace("\\p{M}+".toRegex(), "").lowercase(java.util.Locale.ROOT)
}
