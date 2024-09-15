package model;
import java.util.*;

import javax.persistence.*;

public class SupplierDAO {
   public List<Supplier> getAll(){
	   List<Supplier> data=new ArrayList<>();
	   EntityManagerFactory factory=Persistence.createEntityManagerFactory("mvrsjpa0309");
	   EntityManager mgr=factory.createEntityManager();
	   Query q=mgr.createNamedQuery("Supplier.findAll");
	   data=q.getResultList();  
	   
	   return data;
   }
   public List<Supplier> addSupplier(Supplier s) {
	   EntityManagerFactory factory=Persistence.createEntityManagerFactory("mvrsjpa0309");
	   EntityManager mgr=factory.createEntityManager();
	   mgr.getTransaction().begin();
	   mgr.persist(s);
	   mgr.getTransaction().commit();
	   
	   Query q=mgr.createNamedQuery("Supplier.findAll");	   
	   return q.getResultList();
   }
   public Supplier searchById(int id) {
	   Supplier s=null;
	   EntityManagerFactory factory=Persistence.createEntityManagerFactory("mvrsjpa0309");
	   EntityManager mgr=factory.createEntityManager();
	   String jpql = "SELECT e FROM Supplier e WHERE e.supId = :arg1";
	   Query qry=mgr.createQuery(jpql);
	   qry.setParameter("arg1", id);
	   s=(Supplier)qry.getResultList().get(0);
	   return s;
   }
   
   public boolean updateSupplier(int id , Supplier sp) {
	   Supplier s=null;
	   EntityManagerFactory factory=Persistence.createEntityManagerFactory("mvrsjpa0309");
	   EntityManager mgr=factory.createEntityManager();
	   s=mgr.find(Supplier.class, id);
	   if(s!=null) {
		   mgr.getTransaction().begin();
		   mgr.merge(sp);
		   mgr.getTransaction().commit();
		   return true;
	   }
	   return false;
   }
}
