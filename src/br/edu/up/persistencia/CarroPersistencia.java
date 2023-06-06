package br.edu.up.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.up.entidades.Aluguel;
import br.edu.up.entidades.Carro;
import br.edu.up.entidades.Cliente;

public class CarroPersistencia {
    public static boolean incluir(Carro carro) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(carro);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean alterar(Carro carro) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.merge(carro);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean excluir(Carro carro) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.remove(manager.merge(carro));
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Carro procurarPorModelo(Carro carro) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Carro where modelo = :param");
        consulta.setParameter("param", carro.getModelo());
        List<Carro> carros = consulta.getResultList();
        if (!carros.isEmpty()) {
            return carros.get(0);
        }
        return null;
    }
    
    public static List<Carro> getCarros() {
        EntityManager manager = EntityManagerFactory.getInstance();
        TypedQuery<Carro> query = manager.createQuery("SELECT c FROM Carro c", Carro.class);
        List<Carro> carros = query.getResultList();
        return carros;
    }
}
