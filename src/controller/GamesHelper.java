package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Games;

/**
 * @author Snipe - iwertz
 * CIS175 - Spring 2021
 * Feb 15, 2021
 */
public class GamesHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Week3Assessment");
	
	public void insertItem(Games li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Games> showAllItems(){
		EntityManager em = emfactory.createEntityManager();
		List<Games> allItems = em.createQuery("SELECT i FROM Games i").getResultList();
		return allItems;
	}
	
	public void deleteItem(Games toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Games> typedQuery = em.createQuery("select li from Games li where li.game = :selectedGame and li.rating = :selectedRating", Games.class);

		//Substitute parameter with actual data from the toDelete item
		typedQuery.setParameter("selectedGame", toDelete.getGame());
		typedQuery.setParameter("selectedRating", toDelete.getRating());

		//we only want one result
		typedQuery.setMaxResults(1);

		//get the result and save it into a new list item
		Games result = typedQuery.getSingleResult();

		//remove it
		em.remove(result);
		em.getTransaction().commit();
		em.close();
		
	}
	
	/**
	 * @param toEdit
	 */
	public void updateItem(Games toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * @param idToEdit
	 * @return
	 */
	public Games searchForItemById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Games found = em.find(Games.class, idToEdit);
		em.close();
		return found;
	}
	
	/**
	 * @param storeName
	 * @return
	 */
	public List<Games> searchForItemByStore(String gameName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Games> typedQuery = em.createQuery("select li from Games li where li.game = :selectedGame", Games.class);
		typedQuery.setParameter("selectedGame", gameName);

		List<Games> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	/**
	 * @param itemName
	 * @return
	 */
	public List<Games> searchForItemByItem(String ratingNum) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Games> typedQuery = em.createQuery("select li from Games li where li.rating = :selectedRating", Games.class);
		typedQuery.setParameter("selectedItem", ratingNum);

		List<Games> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
		}
	
	public void cleanUp(){
		emfactory.close();
	}
	
}
