package iniflex.service;

import iniflex.model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

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

}
