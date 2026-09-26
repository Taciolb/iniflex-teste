package iniflex.service;

import iniflex.model.Funcionario;

import java.util.List;

public class FuncionarioService {

    public void removerPorNome(List<Funcionario> funcionarios, String nome) {
        funcionarios.removeIf(f -> f.getNome().equals(nome));
    }
}
