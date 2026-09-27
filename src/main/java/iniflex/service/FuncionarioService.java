package iniflex.service;

import iniflex.model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Comparator;

public class FuncionarioService {

    public void removerPorNome(List<Funcionario> funcionarios, String nome) {
        funcionarios.removeIf(f -> f.getNome().equals(nome));
    }

    public void aplicarAumento(List<Funcionario> funcionarios, BigDecimal percentual) {
        BigDecimal fator = BigDecimal.ONE.add(percentual.divide(new BigDecimal("100")));
        for (Funcionario f : funcionarios) {
            BigDecimal novoSalario = f.getSalario()
                    .multiply(fator)
                    .setScale(2, RoundingMode.HALF_UP);
            f.setSalario(novoSalario);
        }
    }

    public Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao, TreeMap::new, Collectors.toList()));
    }

    public List<Funcionario> aniversariantesNosMeses(List<Funcionario> funcionarios, Set<Integer> meses) {
        return funcionarios.stream()
                .filter(f -> meses.contains(f.getDataNascimento().getMonthValue()))
                .collect(Collectors.toList());
    }

    public Funcionario maiorIdade(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElseThrow();

    }

    public List<Funcionario> ordenarPorNome(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .collect(Collectors.toList());
    }

    public BigDecimal totalSalarios(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Funcionario> ordenarPorSalarioDecrescente(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getSalario).reversed())
                .collect(Collectors.toList());
    }

    public BigDecimal quantidadeSalariosMinimos(Funcionario funcionario, BigDecimal salariominimo) {
        return funcionario.getSalario().divide(salariominimo, 2, RoundingMode.HALF_UP);
    }

}
