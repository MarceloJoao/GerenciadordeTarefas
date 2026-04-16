package br.com.gerenciadordetarefas.repository;

import br.com.gerenciadordetarefas.model.Responsavel;
import br.com.gerenciadordetarefas.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class ResponsavelRepositoryImpl implements ResponsavelRepository {

    @Override
    public List<Responsavel> listar() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("from Responsavel", Responsavel.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Responsavel buscarPorId(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Responsavel.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void salvar(Responsavel responsavel) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(responsavel);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
