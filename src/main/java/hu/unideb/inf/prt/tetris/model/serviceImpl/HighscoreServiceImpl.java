package hu.unideb.inf.prt.tetris.model.serviceImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hu.unideb.inf.prt.tetris.model.entity.Highscore;
import hu.unideb.inf.prt.tetris.model.service.HighscoreService;

/**
 * Implementation of the Highscore service interface..
 * 
 * @author Atka
 */
public class HighscoreServiceImpl implements HighscoreService {

	/**
	 * The logger of the object.
	 */
	private static Logger logger = LoggerFactory.getLogger(HighscoreServiceImpl.class);

	/**
	 * The entity-manager corresponding for the persisting, retrieving and
	 * removing from the table.
	 */
	EntityManager entityManager;

	/**
	 * The constructor to initialize our entity-manager.
	 * 
	 * @param entityManager
	 *            the entity-manager to set
	 */
	public HighscoreServiceImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Adds the parameter entity to the database.
	 * 
	 * @param highscore
	 *            the highscore entity to persist
	 */
	@Override
	public void add(Highscore highscore) {
		logger.info("New score added to HIGHSCORE table");
		entityManager.persist(highscore);
	}

	/**
	 * Returns every highscore entity in the database.
	 * 
	 * @return the list of entities in the database
	 */
	@Override
	public List<Highscore> getAll() {
		logger.info("Getting all score from HIGHSCORE table");
		return entityManager
				.createQuery("SELECT h from hu.unideb.inf.prt.tetris.model.entity.Highscore h", Highscore.class)
				.getResultList();
	}

	/**
	 * Returns a highscore entity by its ID.
	 * 
	 * @param id
	 *            the ID of the entity
	 * @return the entity with the ID
	 */
	@Override
	public Highscore getById(int id) {
		logger.info("Getting a single score from HIGHSCORE table with the id: " + id);
		return entityManager
				.createQuery("SELECT h from hu.unideb.inf.prt.tetris.model.entity.Highscore h WHERE h.id='" + id + "'",
						Highscore.class)
				.getSingleResult();
	}

	/**
	 * Removes a highscore entity by its ID from the database.
	 * 
	 * @param id
	 *            the ID of the entity to delete
	 */
	@Override
	public void removeById(int id) {
		logger.info("Deleting a single score from HIGHSCORE table with the id: " + id);
		entityManager.getTransaction().begin();
		Query query = entityManager
				.createNativeQuery("Delete from hu.unideb.inf.prt.tetris.model.entity.Highscore where id=?");
		query.setParameter(1, id);
		query.executeUpdate();
		entityManager.getTransaction().commit();
	}

}
