package iniflex;

import iniflex.model.Funcionario;
import iniflex.service.FuncionarioService;
import iniflex.util.FormatUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Principal {

    public static void main(String[] args) {
        FuncionarioService service = new FuncionarioService();

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

        service.removerPorNome(funcionarios, "João");

        titulo("Funcionários");
        imprimirLista(funcionarios);


        service.aplicarAumento(funcionarios, new BigDecimal("10"));
        titulo("Funcionários após aumento de 10%");
        imprimirLista(funcionarios);
    }

    private static void titulo(String texto) {
        System.out.println("\n========== " + texto + " ==========");
    }

    private static void imprimirLista(List<Funcionario> lista) {
        System.out.printf("%-10s %-12s %12s %-15s%n", "Nome", "Nascimento", "Salário", "Função");
        for (Funcionario f : lista) {
            System.out.printf("%-10s %-12s %12s %-15s%n",
                    f.getNome(),
                    FormatUtils.data(f.getDataNascimento()),
                    FormatUtils.valor(f.getSalario()),
                    f.getFuncao());


        }
    }
}
