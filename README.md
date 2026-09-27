# Teste Prático Iniflex

## 📋 Sobre o Projeto

O projeto foi desenvolvido seguindo boas práticas de mercado: orientação a objetos com herança e encapsulamento,
separação em camadas (model, service, util), valores monetários com `BigDecimal`, datas com a API `java.time`, streams
para consultas e fluxo de trabalho com Git Flow (main, develop e feature branches integradas por Pull Request).

## 🏗️ Arquitetura

```
┌──────────────────────────────────────────────────────────────┐
│                     Principal (main)                         │
│   Cria os dados · Orquestra os requisitos · Imprime a saída  │
└──────────────┬───────────────────────────────┬───────────────┘
               │                               │
┌──────────────▼───────────────┐  ┌────────────▼───────────────┐
│     FuncionarioService       │  │           util             │
│  Regras de negócio:          │  │  FormatUtils               │
│  remover, aumentar, agrupar, │  │   data dd/MM/yyyy          │
│  filtrar, ordenar, somar,    │  │   valor 1.234,56 (pt-BR)   │
│  calcular salários mínimos   │  │  TableUtils                │
└──────────────┬───────────────┘  │   tabelas no console       │
               │                  └────────────────────────────┘
┌──────────────▼───────────────┐
│            model             │
│  Pessoa ◄── Funcionario      │
│  (nome,     (salário,        │
│   nascim.)   função)         │
└──────────────────────────────┘
```

### Por que essa arquitetura?

| Decisão                                    | Motivo                                                      |
|--------------------------------------------|-------------------------------------------------------------|
| Camadas model / service / util             | Cada classe com uma única responsabilidade                  |
| Principal só orquestra                     | Regras isoladas no service, fáceis de testar e reaproveitar |
| Herança Funcionario → Pessoa               | Relação "é um", sem repetir atributos                       |
| Métodos com parâmetros (percentual, meses) | Métodos reutilizáveis, sem valores fixos no código          |
| Utilitários `final` com construtor privado | Não podem ser instanciados nem estendidos                   |
| Java puro, sem framework                   | Escopo de console, sem banco nem API;                       |

## 🛠️ Stack Tecnológica

| Camada             | Tecnologia                                         |
|--------------------|----------------------------------------------------|
| Linguagem          | Java 17 (LTS)                                      |
| Build              | Maven (sem dependências externas)                  |
| Datas              | `java.time` (LocalDate, Period, DateTimeFormatter) |
| Valores monetários | BigDecimal + RoundingMode                          |
| Coleções           | ArrayList, TreeMap, Streams e Collectors           |
| IDE                | IntelliJ IDEA                                      |
| Versionamento      | Git + GitHub (Git Flow)                            |

## 📦 Estrutura do Projeto

```
iniflex-teste/
├── pom.xml                              # Java 17 + UTF-8
├── README.md
└── src/main/java/iniflex/
    ├── Principal.java                   # Executa os requisitos 3.1 a 3.12
    ├── model/
    │   ├── Pessoa.java                  # nome e data de nascimento
    │   └── Funcionario.java             # estende Pessoa: salário e função
    ├── service/
    │   └── FuncionarioService.java      # regras de negócio
    └── util/
        ├── FormatUtils.java             # formatação de data e valores
        └── TableUtils.java              # impressão em tabela no console
```

## 📋 Requisitos Implementados

| Item | Requisito                                              | Implementação                                           |
|------|--------------------------------------------------------|---------------------------------------------------------|
| 1    | Classe Pessoa (nome, data de nascimento)               | `model/Pessoa`                                          |
| 2    | Classe Funcionario estendendo Pessoa (salário, função) | `model/Funcionario`                                     |
| 3.1  | Inserir funcionários na ordem da tabela                | `ArrayList` na `Principal`                              |
| 3.2  | Remover o funcionário "João"                           | `removerPorNome` com `removeIf`                         |
| 3.3  | Imprimir com data dd/mm/aaaa e valor 1.234,56          | `FormatUtils` + `TableUtils`                            |
| 3.4  | Aumento de 10% nos salários                            | `aplicarAumento` com `setScale(2, HALF_UP)`             |
| 3.5  | Agrupar por função em um Map                           | `agruparPorFuncao` com `groupingBy` + `TreeMap`         |
| 3.6  | Imprimir agrupados por função                          | `forEach` no Map                                        |
| 3.8  | Aniversariantes dos meses 10 e 12                      | `aniversariantesNosMeses` com `filter`                  |
| 3.9  | Funcionário com maior idade (nome e idade)             | `maiorIdade` com `min` + `Period.between`               |
| 3.10 | Ordem alfabética                                       | `ordenarPorNome` com `sorted`                           |
| 3.11 | Total dos salários                                     | `totalSalarios` com `map` + `reduce`                    |
| 3.12 | Salários mínimos por funcionário (R$ 1.212,00)         | `quantidadeSalariosMinimos` com `divide(…, 2, HALF_UP)` |

## 🖥️ Exemplo de Saída

Funcionários (item 3.3):

```
+---------+------------+-----------+---------------+
| Nome    | Nascimento | Salário   | Função        |
+---------+------------+-----------+---------------+
| Maria   | 18/10/2000 |  2.009,44 | Operador      |
| Caio    | 02/05/1961 |  9.836,14 | Coordenador   |
| Miguel  | 14/10/1988 | 19.119,88 | Diretor       |
| Alice   | 05/01/1995 |  2.234,68 | Recepcionista |
| Heitor  | 19/11/1999 |  1.582,72 | Operador      |
| Arthur  | 31/03/1993 |  4.071,84 | Contador      |
| Laura   | 08/07/1994 |  3.017,45 | Gerente       |
| Heloísa | 24/05/2003 |  1.606,85 | Eletricista   |
| Helena  | 02/09/1996 |  2.799,93 | Gerente       |
+---------+------------+-----------+---------------+
```

Salários mínimos por funcionário (item 3.12):

```
+---------+-----------+------------------+
| Nome    | Salário   | Salários mínimos |
+---------+-----------+------------------+
| Miguel  | 21.031,87 |            17,35 |
| Caio    | 10.819,75 |             8,93 |
| Arthur  |  4.479,02 |             3,70 |
| Laura   |  3.319,20 |             2,74 |
| Helena  |  3.079,92 |             2,54 |
| Alice   |  2.458,15 |             2,03 |
| Maria   |  2.210,38 |             1,82 |
| Heloísa |  1.767,54 |             1,46 |
| Heitor  |  1.740,99 |             1,44 |
+---------+-----------+------------------+
```

## 🌿 Fluxo de Trabalho (Git Flow)

| Branch                   | Uso                                        |
|--------------------------|--------------------------------------------|
| `main`                   | Versão estável e final                     |
| `develop`                | Integração das funcionalidades             |
| `feature/modelo`         | Classes Pessoa e Funcionario               |
| `feature/formatacao`     | Utilitário de formatação                   |
| `feature/requisitos`     | Requisitos 3.1 a 3.12 (um commit por item) |
| `feature/tabela-console` | Impressão em tabela no console             |
| `docs/readme`            | Documentação                               |

Cada feature foi integrada à `develop` por Pull Request, e a `develop` à `main` ao final.

## 🗺️ Roadmap

- [x] Modelagem (Pessoa e Funcionario)
- [x] Formatação de datas e valores (pt-BR)
- [x] Requisitos 3.1 a 3.12
- [x] Impressão em tabela no console
- [ ] Testes unitários com JUnit 5 para o FuncionarioService
- [ ] Ordenação com `Collator` pt-BR para nomes iniciados com acento
- [ ] Evolução para API REST com Spring Boot, reaproveitando o service

## 👨‍💻 Autor

Taciano Lucio Belarmino — [@Taciolb](https://github.com/Taciolb)