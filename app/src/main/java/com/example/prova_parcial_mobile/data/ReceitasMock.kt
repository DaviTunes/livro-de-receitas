package com.example.prova_parcial_mobile.data

import com.example.prova_parcial_mobile.R
import com.example.prova_parcial_mobile.model.Ingrediente
import com.example.prova_parcial_mobile.model.Receita

// Dados mockados: substituem uma API/banco nesta etapa do projeto.
// É "var" porque, quando uma receita é marcada como concluída, a lista inteira é trocada
// por uma nova (as receitas continuam imutáveis, só criamos cópias com copy()).
var receitas: List<Receita> = listOf(
    Receita(
        id = "bolo-cenoura",
        nome = "Bolo de Cenoura",
        tempoPreparoMinutos = 50,
        ingredientes = listOf(
            Ingrediente("1", "Cenoura média", "3 unidades"),
            Ingrediente("2", "Ovos", "4 unidades"),
            Ingrediente("3", "Óleo", "1 xícara"),
            Ingrediente("4", "Açúcar", "2 xícaras"),
            Ingrediente("5", "Farinha de trigo", "2 xícaras"),
            Ingrediente("6", "Fermento em pó", "1 colher de sopa")
        ),
        modoPreparo = listOf(
            "Bata no liquidificador a cenoura, os ovos e o óleo.",
            "Despeje em uma tigela e misture o açúcar e a farinha.",
            "Acrescente o fermento e misture delicadamente.",
            "Asse em forma untada a 180 °C por cerca de 40 minutos."
        ),
        dica = "Para a cobertura, derreta 4 colheres de chocolate em pó com 1 xícara de açúcar, 1 colher de manteiga e 5 colheres de leite.",
        imagemRes = R.drawable.bolo_cenoura
    ),
    Receita(
        id = "brigadeiro",
        nome = "Brigadeiro",
        tempoPreparoMinutos = 25,
        ingredientes = listOf(
            Ingrediente("1", "Leite condensado", "1 lata"),
            Ingrediente("2", "Chocolate em pó", "2 colheres de sopa"),
            Ingrediente("3", "Manteiga", "1 colher de sopa"),
            Ingrediente("4", "Chocolate granulado", "a gosto")
        ),
        modoPreparo = listOf(
            "Leve ao fogo baixo o leite condensado, o chocolate e a manteiga.",
            "Mexa sem parar até desgrudar do fundo da panela.",
            "Deixe esfriar, enrole bolinhas e passe no granulado."
        ),
        dica = "Unte as mãos com manteiga antes de enrolar para não grudar.",
        imagemRes = R.drawable.brigadeiro
    ),
    Receita(
        id = "pao-de-queijo",
        nome = "Pão de Queijo",
        tempoPreparoMinutos = 45,
        ingredientes = listOf(
            Ingrediente("1", "Polvilho azedo", "500 g"),
            Ingrediente("2", "Leite", "1 xícara"),
            Ingrediente("3", "Óleo", "1/2 xícara"),
            Ingrediente("4", "Ovos", "2 unidades"),
            Ingrediente("5", "Queijo meia cura ralado", "200 g"),
            Ingrediente("6", "Sal", "1 colher de chá")
        ),
        modoPreparo = listOf(
            "Ferva o leite com o óleo e o sal e escalde o polvilho.",
            "Espere amornar e acrescente os ovos, um de cada vez.",
            "Misture o queijo e sove até a massa ficar lisa.",
            "Faça bolinhas e asse a 200 °C por 25 minutos."
        ),
        // Sem dica: demonstra o campo opcional como null
        imagemRes = R.drawable.pao_de_queijo
    ),
    Receita(
        id = "strogonoff-frango",
        nome = "Strogonoff de Frango",
        tempoPreparoMinutos = 40,
        ingredientes = listOf(
            Ingrediente("1", "Peito de frango em cubos", "500 g"),
            Ingrediente("2", "Cebola picada", "1 unidade"),
            Ingrediente("3", "Alho picado", "2 dentes"),
            Ingrediente("4", "Ketchup", "3 colheres de sopa"),
            Ingrediente("5", "Mostarda", "1 colher de sopa"),
            Ingrediente("6", "Creme de leite", "1 caixa"),
            Ingrediente("7", "Champignon", "1 vidro")
        ),
        modoPreparo = listOf(
            "Doure o frango temperado com sal e pimenta.",
            "Junte a cebola e o alho e refogue até murchar.",
            "Adicione ketchup, mostarda e champignon.",
            "Desligue o fogo e misture o creme de leite."
        ),
        dica = "Sirva com arroz branco e batata palha.",
        imagemRes = R.drawable.strogonoff_frango
    ),
    Receita(
        id = "pudim-leite",
        nome = "Pudim de Leite",
        tempoPreparoMinutos = 90,
        ingredientes = listOf(
            Ingrediente("1", "Leite condensado", "1 lata"),
            Ingrediente("2", "Leite", "a mesma medida da lata"),
            Ingrediente("3", "Ovos", "3 unidades"),
            Ingrediente("4", "Açúcar (calda)", "1 xícara")
        ),
        modoPreparo = listOf(
            "Derreta o açúcar até virar caramelo e espalhe na forma.",
            "Bata no liquidificador o leite condensado, o leite e os ovos.",
            "Despeje na forma e asse em banho-maria a 180 °C por 1 hora.",
            "Leve à geladeira por pelo menos 4 horas antes de desenformar."
        ),
        imagemRes = R.drawable.pudim_leite
    ),
    Receita(
        id = "omelete",
        nome = "Omelete Simples",
        tempoPreparoMinutos = 10,
        ingredientes = listOf(
            Ingrediente("1", "Ovos", "2 unidades"),
            Ingrediente("2", "Sal", "1 pitada"),
            Ingrediente("3", "Queijo ralado", "2 colheres de sopa"),
            Ingrediente("4", "Manteiga", "1 colher de chá")
        ),
        modoPreparo = listOf(
            "Bata os ovos com o sal.",
            "Derreta a manteiga em frigideira antiaderente.",
            "Despeje os ovos, salpique o queijo e dobre ao meio quando firmar."
        ),
        dica = "Fogo baixo deixa a omelete macia por dentro.",
        imagemRes = R.drawable.omelete
    )
)
