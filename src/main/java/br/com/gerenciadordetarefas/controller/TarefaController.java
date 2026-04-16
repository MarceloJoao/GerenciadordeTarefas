package br.com.gerenciadordetarefas.controller;

import br.com.gerenciadordetarefas.model.Prioridade;
import br.com.gerenciadordetarefas.model.Responsavel;
import br.com.gerenciadordetarefas.model.StatusTarefa;
import br.com.gerenciadordetarefas.model.Tarefa;
import br.com.gerenciadordetarefas.repository.ResponsavelRepository;
import br.com.gerenciadordetarefas.repository.ResponsavelRepositoryImpl;
import br.com.gerenciadordetarefas.repository.TarefaRepository;
import br.com.gerenciadordetarefas.repository.TarefaRepositoryImpl;
import br.com.gerenciadordetarefas.service.TarefaService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ComponentSystemEvent;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
public class TarefaController implements Serializable {

    private static final long serialVersionUID = 1L;

    private Tarefa tarefa = new Tarefa();
    private Responsavel responsavel = new Responsavel();

    private Long responsavelId;
    private Long idEdicao;

    private Long filtroNumero;
    private String filtroTexto;
    private Long filtroResponsavelId;
    private StatusTarefa filtroStatus = StatusTarefa.EM_ANDAMENTO;

    private final TarefaRepository tarefaRepository = new TarefaRepositoryImpl();
    private final TarefaService tarefaService = new TarefaService(tarefaRepository);

    private final ResponsavelRepository responsavelRepository = new ResponsavelRepositoryImpl();

    public void salvarResponsavel() {
        responsavelRepository.salvar(responsavel);
        responsavel = new Responsavel();
    }

    public void salvar() {
        if (responsavelId != null) {
            Responsavel responsavelSelecionado = responsavelRepository.buscarPorId(responsavelId);
            tarefa.setResponsavel(responsavelSelecionado);
        }

        if (tarefa.getId() == null) {
            tarefaService.salvar(tarefa);
        } else {
            tarefaService.atualizar(tarefa);
        }

        tarefa = new Tarefa();
        responsavelId = null;
        idEdicao = null;
    }

    public void concluir(Long id) {
        tarefaService.concluir(id);
    }

    public void excluir(Long id) {
        tarefaService.excluir(id);
    }

    public String prepararEdicao(Tarefa tarefa) {
        return "/CadastroTarefas.xhtml?faces-redirect=true&idEdicao=" + tarefa.getId();
    }

    public void carregarTarefaParaEdicao(ComponentSystemEvent event) {
        System.out.println("ID EDICAO: " + idEdicao);

        if (idEdicao != null && tarefa.getId() == null) {
            tarefa = tarefaService.buscarPorId(idEdicao);

            if (tarefa != null && tarefa.getResponsavel() != null) {
                responsavelId = tarefa.getResponsavel().getId();
            }
        }
    }

    public void filtrar() {
    }

    public void limparFiltros() {
        filtroNumero = null;
        filtroTexto = null;
        filtroResponsavelId = null;
        filtroStatus = StatusTarefa.EM_ANDAMENTO;
    }

    public List<Tarefa> getTarefasFiltradas() {
        return tarefaService.filtrar(filtroNumero, filtroTexto, filtroResponsavelId, filtroStatus);
    }

    public List<Responsavel> getResponsaveis() {
        return responsavelRepository.listar();
    }

    public Prioridade[] getPrioridades() {
        return Prioridade.values();
    }

    public StatusTarefa[] getStatusTarefas() {
        return StatusTarefa.values();
    }

    public Tarefa getTarefa() {
        return tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public Long getResponsavelId() {
        return responsavelId;
    }

    public void setResponsavelId(Long responsavelId) {
        this.responsavelId = responsavelId;
    }

    public Long getIdEdicao() {
        return idEdicao;
    }

    public void setIdEdicao(Long idEdicao) {
        this.idEdicao = idEdicao;
    }

    public Long getFiltroNumero() {
        return filtroNumero;
    }

    public void setFiltroNumero(Long filtroNumero) {
        this.filtroNumero = filtroNumero;
    }

    public String getFiltroTexto() {
        return filtroTexto;
    }

    public void setFiltroTexto(String filtroTexto) {
        this.filtroTexto = filtroTexto;
    }

    public Long getFiltroResponsavelId() {
        return filtroResponsavelId;
    }

    public void setFiltroResponsavelId(Long filtroResponsavelId) {
        this.filtroResponsavelId = filtroResponsavelId;
    }

    public StatusTarefa getFiltroStatus() {
        return filtroStatus;
    }

    public void setFiltroStatus(StatusTarefa filtroStatus) {
        this.filtroStatus = filtroStatus;
    }
}