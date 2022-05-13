package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

import data.Question;
import data.Users;


/**
 * Date: 7.5.2022
 * This is the RestDAO class that is responsible for accessing and modifying the database.
 * @author Arsi Arola, Ari-Jussi Ahonen, Oskari Ahoniemi
 * @version 1.0
 * 
 */

@Path("/restdao")
public class RestDao {
	
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikone");
	
	/**
	 * This method reads all the questions from the database
	 * @return returns an arraylist of question-objects
	 */
	@GET
	@Path("/readallquestions")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public List<Question> readQuestion() {
		List<Question> list = null;
		try {
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			list = em.createQuery("select x from Question x").getResultList();		
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * this method adds a new question to the database
	 * @param question is a Question-type object which is added to the database
	 * @return returns an updated array-list of all Question-objects in the database
	 */
	@POST
	@Path("/addquestion")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public List<Question> addQuestion(Question question) {
		try {
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(question);//The actual insertion line
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		List<Question> list=readQuestion();		
		return list;
	}
	
	
	@GET
	@Path("/readtoupdatequestion/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Question readToUpdateQuestion(@PathParam("id") int id) {
		Question q = null;
		try {
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			q = em.find(Question.class, id);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return q;
	}	
	
	@PUT
	@Path("/updatequestion")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public List<Question> updateQuestion(Question question) {
		try {
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			Question q=em.find(Question.class, question.getId());
			if (q!=null) {
				em.merge(question);//The actual update line
			} else {
				Response.status(HttpStatus.SC_NOT_FOUND); //maybe returns 404 http status code if the question id is null
			}
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Calling the method readQuestion() of this service
		List<Question> list=readQuestion();		
		return list;
	}	
	
	
	
	/**
	 * This method receives a question id and based on it, will delete the question from the database.
	 * @param id of the question sent for deletion
	 * @return returns a revised list of questions
	 */
	@DELETE
	@Path("/deleteQuestion/{id}")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=UTF-8")
	@Consumes(MediaType.APPLICATION_JSON+ ";charset=UTF-8")
	public List<Question> deleteQuestion(@PathParam("id") int id) {	
		try {
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			Question q=em.find(Question.class, id);
				if (q!=null) {
					em.remove(q);
				} 
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Question> list=readQuestion();
		return list;				
	}
	
	/**
	 * this method reads all users and their passwords from the database
	 * @return returns an array-list of all Users-objects in the database
	 */
	@GET
	@Path("/readusers")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public List<Users> readUsers() {
		List<Users> list = null;
		try {
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			list = em.createQuery("select x from Users x").getResultList();		
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * this method adds a new user and password to the database
	 * @param user is a Users-type object which is added to the database
	 */
	@POST
	@Path("/adduser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addUser(Users user) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);//The actual insertion line
		em.getTransaction().commit();		
	}	
	

}
