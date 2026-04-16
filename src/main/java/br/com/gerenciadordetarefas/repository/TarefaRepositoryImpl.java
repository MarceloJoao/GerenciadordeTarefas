package br.com.gerenciadordetarefas.repository;

import br.com.gerenciadordetarefas.model.Prioridade;
import br.com.gerenciadordetarefas.model.StatusTarefa;
import br.com.gerenciadordetarefas.model.Tarefa;
import br.com.gerenciadordetarefas.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TarefaRepositoryImpl implements TarefaRepository {

    @Override
    public void salvar(Tarefa tarefa) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(tarefa);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Tarefa> listar() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("from Tarefa", Tarefa.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Tarefa> listarPorStatus(StatusTarefa status) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery(
                            "from Tarefa t where t.status = :status", Tarefa.class)
                    .setParameter("status", status)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Tarefa buscarPorId(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Tarefa.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void atualizar(Tarefa tarefa) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(tarefa);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void excluir(Tarefa tarefa) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Tarefa tarefaGerenciada = em.merge(tarefa);
            em.remove(tarefaGerenciada);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Tarefa> filtrar(Long numero, String texto, Long responsavelId, StatusTarefa status) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            String jpql = "select t from Tarefa t where 1=1";

            if (numero != null) {
                jpql += " and t.id = :numero";
            }

            if (texto != null && !texto.trim().isEmpty()) {
                jpql += " and (lower(t.titulo) like :texto or lower(t.descricao) like :texto)";
            }

            if (responsavelId != null) {
                jpql += " and t.responsavel.id = :responsavelId";
            }

            if (status != null) {
                jpql += " and t.status = :status";
            }

            TypedQuery<Tarefa> query = em.createQuery(jpql, Tarefa.class);

            if (numero != null) {
                query.setParameter("numero", numero);
            }

            if (texto != null && !texto.trim().isEmpty()) {
                query.setParameter("texto", "%" + texto.toLowerCase() + "%");
            }

            if (responsavelId != null) {
                query.setParameter("responsavelId", responsavelId);
            }

            if (status != null) {
                query.setParameter("status", status);
            }

            return query.getResultList();
        } finally {
            em.close();
        }
    }
}