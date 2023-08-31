package br.com.fiap;

import br.com.fiap.domain.entity.Bem;
import br.com.fiap.domain.entity.Departamento;
import br.com.fiap.domain.entity.Inventario;
import br.com.fiap.domain.entity.TipoDeBem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("oracle");
        EntityManager manager = factory.createEntityManager();

        try {
            // Criar e persistir objetos
         //   persistEntities(manager);

            // Consultar inventário pelo identificador
            consultarInventarioPorId(manager, 1L);

            // Consultar todos os bens
            consultarTodosOsBens(manager);

            findById(manager);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            manager.close();
            factory.close();
        }
    }

    public static void persistEntities(EntityManager manager) {
        manager.getTransaction().begin();

        // Criar e persistir as entidades aqui
        Departamento dp = new Departamento(null, "Enterprise");
        TipoDeBem tp = new TipoDeBem(null, "Eletronico");
        manager.persist(dp);
        manager.persist(tp);

        Bem bem = new Bem();
        bem.setId(null)
                .setNome("Macbook")
                .setTipo(tp)
                .setEtiqueta("164986")
                .setLocalizacao(dp)
                .setAquisicao(LocalDate.now());
        manager.persist(bem);

        Inventario inv = new Inventario();
        inv.setId(null)
                .setInicio(LocalDate.now())
                .setDepartamento(dp);
        manager.persist(inv);
        manager.getTransaction().commit();
    }

    public static void consultarInventarioPorId(EntityManager manager, Long id) {
        Inventario inventario = manager.find(Inventario.class, id);
        if (inventario != null) {
            System.out.println("Inventário encontrado:\n" + inventario);
        } else {
            System.out.println("Inventário não encontrado com o ID: " + id);
        }
    }

    public static void consultarTodosOsBens(EntityManager manager) {
        List<Bem> bens = manager.createQuery("SELECT b FROM Bem b", Bem.class).getResultList();
        if (!bens.isEmpty()) {
            System.out.println("Lista de Bens:");
            for (Bem bem : bens) {
                System.out.println(bem);
            }
        } else {
            System.out.println("Nenhum bem encontrado.");
        }
    }
    private static void findById(EntityManager manager) {
        Long id = Long.valueOf(JOptionPane.showInputDialog("Informe o ID do Bem: "));
        Bem bem = manager.find(Bem.class, id);
        if (bem != null) {
            System.out.println("Bem encontrado:\n" + bem);
        } else {
            System.out.println("Bem não encontrado com o ID: " + id);
        }
    }
}
