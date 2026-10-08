# Livro de Receitas 🍰

App Android desenvolvido para a **Parcial de Programação Mobile 1** (Engenharia de Software, 6º semestre).

## Objetivo

Um livro de receitas simples: o usuário vê a lista de receitas, abre uma delas para ver
ingredientes e modo de preparo e marca a receita como **concluída** depois de prepará-la.
A marcação aparece de volta na lista com o selo "✓ Concluída".

## Telas

| Tela | Activity / Layout | O que tem |
|---|---|---|
| Lista de receitas | `ListaReceitasActivity` / `activity_lista_receitas.xml` + `item_receita.xml` | Título + `RecyclerView` com cards (imagem, nome, tempo de preparo e selo de concluída) |
| Detalhe da receita | `DetalheReceitaActivity` / `activity_detalhe_receita.xml` | Cabeçalho com selos, card de ingredientes, card de modo de preparo, card de dica (opcional) e card com o `CheckBox` "Receita concluída" |

## Como os requisitos da Parcial foram atendidos

- **Duas telas em Views XML:** `LinearLayout`, `ScrollView`, `RecyclerView`, `TextView`, `ImageView`, `MaterialDivider` e `CheckBox`, com `include` e `dimen.xml` para reaproveitar estilos.
- **Navegação por Intent explícita:** ao tocar num item, o adapter cria `Intent(context, DetalheReceitaActivity::class.java)` e passa o id da receita em `EXTRA_RECEITA_ID`.
- **ViewBinding:** ativado em `app/build.gradle.kts` (`buildFeatures { viewBinding = true }`) e usado nas Activities, no Adapter e nos componentes inflados. O projeto não usa `findViewById` (item opcional).
- **Interação que atualiza a UI:** marcar ou desmarcar o `CheckBox` troca o texto e a cor do status. Ao voltar, a lista mostra ou esconde o selo "✓ Concluída" (`onResume` + `notifyItemChanged`).
- **Data classes imutáveis:** `Receita` e `Ingrediente` usam só `val`, e `Receita` valida os dados no `init` com `require`. Para alterar uma receita é criada uma cópia com `copy(concluida = ...)`, que substitui a antiga na lista mock.
- **Componentes XML reutilizáveis (opcional):** `selo_layout.xml` é usado com `<include>` no item da lista e inflado pelo código no detalhe (`addSeloView`). `ingrediente_layout.xml` é inflado uma vez por ingrediente (`addIngredienteView`).
- **Valores opcionais:** `Receita.dica: String?` e `Receita.imagemRes: Int?`. Quando a dica é `null`, o bloco "Dica" fica escondido. Quando não há imagem, é usado um ícone padrão (`?:`). O `getStringExtra` (que retorna `String?`) também é tratado: se a receita não for encontrada, a tela fecha com um aviso.
- **Dados mockados:** lista `receitas` em `data/ReceitasMock.kt`, sem API nem banco de dados.

## Como rodar

**Pré-requisitos:** Android Studio recente (compatível com Android Gradle Plugin 9.3) e Android SDK 37 instalado.
O JDK é resolvido automaticamente pelo Gradle (toolchain).

1. Clone o repositório:
   ```bash
   git clone <url-deste-repositorio>
   ```
2. No Android Studio: **File → Open** e selecione a pasta do projeto.
3. Aguarde o **Gradle Sync** terminar (na primeira vez ele baixa as dependências).
4. Escolha um emulador ou um celular com depuração USB ativada e clique em **Run ▶**.

Também é possível gerar o APK pela linha de comando:

```bash
./gradlew assembleDebug
```

O APK fica em `app/build/outputs/apk/debug/app-debug.apk`.

## Como testar o fluxo

1. Ao abrir o app aparece a lista com 6 receitas.
2. Toque em **Bolo de Cenoura**: a tela de detalhe abre com ingredientes, passos e a dica.
3. Marque **Receita concluída**: o status muda para a mensagem verde.
4. Volte (seta da barra ou botão voltar): o card do bolo agora mostra **✓ Concluída**.
5. Abra **Pão de Queijo**: essa receita não tem dica, então a seção "Dica" não aparece.

## Bibliotecas usadas

| Biblioteca | Para quê |
|---|---|
| `androidx.appcompat` | `AppCompatActivity` |
| `androidx.activity-ktx` | `enableEdgeToEdge()` |
| `androidx.core-ktx` | `ViewCompat`/`WindowInsetsCompat` (margens das barras do sistema) e `ContextCompat` |
| `com.google.android.material` | Tema Material 3, `MaterialDivider`, `MaterialCheckBox` e o `RecyclerView` (que vem junto com a biblioteca) |

## Estrutura

```
app/src/main/java/com/example/prova_parcial_mobile/
├── ListaReceitasActivity.kt   # Tela 1: lista + ReceitaListAdapter (RecyclerView)
├── DetalheReceitaActivity.kt  # Tela 2: detalhe + CheckBox
├── data/ReceitasMock.kt       # Dados mockados
└── model/
    ├── Ingrediente.kt
    └── Receita.kt
```
