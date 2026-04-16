package br.com.gerenciadordetarefas.repository;

import br.com.gerenciadordetarefas.model.StatusTarefa;
import br.com.gerenciadordetarefas.model.Tarefa;

import java.util.List;

public interface TarefaRepository {

    void salvar(Tarefa tarefa);

    List<Tarefa> listar();

    Tarefa buscarPorId(Long id);

    void atualizar(Tarefa tarefa);

    void excluir(Tarefa tarefa);

    List<Tarefa> listarPorStatus(StatusTarefa status);

    List<Tarefa> filtrar(Long numero,
                         String texto,
                         Long responsavelId,
                         StatusTarefa status);
}
