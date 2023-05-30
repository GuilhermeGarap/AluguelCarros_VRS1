package br.edu.up.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.edu.up.entidades.Aluguel;
import javax.persistence.TypedQuery;

public class AluguelPersistencia {
    public static boolean incluir(Aluguel aluguel) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(aluguel);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean alterar(Aluguel aluguel) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.merge(aluguel);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean excluir(Aluguel aluguel) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.remove(manager.merge(aluguel));
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Aluguel procurarPorId(Aluguel aluguel) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Aluguel where id = :param");
        consulta.setParameter("param", aluguel.getId());
        List<Aluguel> alugueis = consulta.getResultList();
        if (!alugueis.isEmpty()) {
            return alugueis.get(0);
        }
        return null;
    }
    

    public static List<Aluguel> getAlugueis() {
        EntityManager manager = EntityManagerFactory.getInstance();
        TypedQuery<Aluguel> query = manager.createQuery("SELECT a FROM Aluguel a", Aluguel.class);
        List<Aluguel> alugueis = query.getResultList();
        return alugueis;
    }

   


}
