package br.com.gerenciadordetarefas.repository;

import br.com.gerenciadordetarefas.model.Tarefa;

import java.util.List;

public interface TarefaRepository{
    void salvar(Tarefa tarefa);

    List<Tarefa> listar();

    void atualizar(Tarefa tarefa);

    void excluir(Long id);
}
