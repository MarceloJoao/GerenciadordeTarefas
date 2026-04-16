package br.com.gerenciadordetarefas.repository;

import br.com.gerenciadordetarefas.model.Responsavel;

import java.util.List;

public interface ResponsavelRepository {
    List<Responsavel> listar();
    Responsavel buscarPorId(Long id);
    void salvar(Responsavel responsavel);
}
