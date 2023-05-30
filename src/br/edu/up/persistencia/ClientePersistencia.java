package br.edu.up.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.edu.up.entidades.Cliente;

public class ClientePersistencia {
    public static boolean incluir(Cliente cliente) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(cliente);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean alterar(Cliente cliente) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.merge(cliente);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean excluir(Cliente cliente) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.remove(manager.merge(cliente));
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Cliente procurarPorCPF(Cliente cliente) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Cliente where cpf = :param");
        consulta.setParameter("param", cliente.getCpf());
        List<Cliente> clientes = consulta.getResultList();
        if (!clientes.isEmpty()) {
            return clientes.get(0);
        }
        return null;
    }

    public static List<Cliente> getClientes(Cliente cliente) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Cliente where nome like :param");
        consulta.setParameter("param", "%" + cliente.getNome() + "%");
        List<Cliente> clientes = consulta.getResultList();
        return clientes;
    }
}
