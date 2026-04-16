package br.com.gerenciadordetarefas.service;

import br.com.gerenciadordetarefas.model.StatusTarefa;
import br.com.gerenciadordetarefas.model.Tarefa;
import br.com.gerenciadordetarefas.repository.TarefaRepository;

import java.util.List;

public class TarefaService {

    private final TarefaRepository repository;

    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    public void salvar(Tarefa tarefa) {
        if (tarefa.getStatus() == null) {
            tarefa.setStatus(StatusTarefa.EM_ANDAMENTO);
        }
        repository.salvar(tarefa);
    }

    public void atualizar(Tarefa tarefa) {
        repository.atualizar(tarefa);
    }

    public Tarefa buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public List<Tarefa> listarEmAndamento() {
        return repository.listarPorStatus(StatusTarefa.EM_ANDAMENTO);
    }

    public List<Tarefa> listarConcluidas() {
        return repository.listarPorStatus(StatusTarefa.CONCLUIDA);
    }

    public List<Tarefa> listarPorStatus(StatusTarefa status) {
        return repository.listarPorStatus(status);
    }

    public List<Tarefa> filtrar(Long numero,
                                String texto,
                                Long responsavelId,
                                StatusTarefa status) {
        return repository.filtrar(numero, texto, responsavelId, status);
    }

    public void excluir(Long id) {
        Tarefa tarefa = repository.buscarPorId(id);
        if (tarefa != null) {
            repository.excluir(tarefa);
        }
    }

    public void concluir(Long id) {
        Tarefa tarefa = repository.buscarPorId(id);
        if (tarefa != null) {
            tarefa.setStatus(StatusTarefa.CONCLUIDA);
            repository.atualizar(tarefa);
        }
    }
}