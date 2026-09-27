package iniflex;

import iniflex.model.Funcionario;
import iniflex.service.FuncionarioService;
import iniflex.util.FormatUtils;
import iniflex.util.TableUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Principal {

    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static void main(String[] args) {
        FuncionarioService service = new FuncionarioService();

        // 3.1 - Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 - Remover o funcionário "João"
        service.removerPorNome(funcionarios, "João");

        // 3.3 - Imprimir todos os funcionários
        titulo("Funcionários");
        imprimirLista(funcionarios);

        // 3.4 - Aumento de 10%
        service.aplicarAumento(funcionarios, new BigDecimal("10"));
        titulo("Funcionários após aumento de 10%");
        imprimirLista(funcionarios);

        // 3.5 e 3.6 - Agrupar por função e imprimir
        Map<String, List<Funcionario>> porFuncao = service.agruparPorFuncao(funcionarios);
        titulo("Funcionários agrupados por função");
        porFuncao.forEach((funcao, lista) -> {
            System.out.println("\n>> " + funcao);
            imprimirLista(lista);
        });

        // 3.8 - Aniversariantes dos meses 10 e 12
        titulo("Aniversariantes dos meses 10 e 12");
        imprimirLista(service.aniversariantesNosMeses(funcionarios, Set.of(10, 12)));

        // 3.9 - Funcionário com maior idade
        Funcionario maiorIdade = service.maiorIdade(funcionarios);
        int idade = Period.between(maiorIdade.getDataNascimento(), LocalDate.now()).getYears();
        titulo("Funcionário com maior idade");
        List<String[]> linhaIdade = new ArrayList<>();
        linhaIdade.add(new String[]{maiorIdade.getNome(), idade + " anos"});
        TableUtils.imprimir(new String[]{"Nome", "Idade"}, linhaIdade, new boolean[]{false, true});

        // 3.10 - Ordem alfabética
        titulo("Funcionários em ordem alfabética");
        imprimirLista(service.ordenarPorNome(funcionarios));

        // 3.11 - Total dos salários
        titulo("Total dos salários");
        List<String[]> linhaTotal = new ArrayList<>();
        linhaTotal.add(new String[]{"R$ " + FormatUtils.valor(service.totalSalarios(funcionarios))});
        TableUtils.imprimir(new String[]{"Total"}, linhaTotal, new boolean[]{true});

        // 3.12 - Quantidade de salários mínimos (do maior para o menor salário)
        titulo("Salários mínimos por funcionário");
        List<String[]> linhasMinimos = new ArrayList<>();
        for (Funcionario f : service.ordenarPorSalarioDecrescente(funcionarios)) {
            BigDecimal qtd = service.quantidadeSalariosMinimos(f, SALARIO_MINIMO);
            linhasMinimos.add(new String[]{f.getNome(), FormatUtils.valor(f.getSalario()), FormatUtils.valor(qtd)});
        }
        TableUtils.imprimir(new String[]{"Nome", "Salário", "Salários mínimos"}, linhasMinimos,
                new boolean[]{false, true, true});
    }

    private static void titulo(String texto) {
        System.out.println("\n========== " + texto + " ==========");
    }

    private static void imprimirLista(List<Funcionario> lista) {
        List<String[]> linhas = new ArrayList<>();
        for (Funcionario f : lista) {
            linhas.add(new String[]{
                    f.getNome(),
                    FormatUtils.data(f.getDataNascimento()),
                    FormatUtils.valor(f.getSalario()),
                    f.getFuncao()
            });
        }
        TableUtils.imprimir(new String[]{"Nome", "Nascimento", "Salário", "Função"}, linhas,
                new boolean[]{false, false, true, false});
    }
}