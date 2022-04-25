package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.Question;

@Path("/restdao")
public class RestDao {
	
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikone");
	
	@GET
	@Path("/readallquestions")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public List<Question> readQuestion() {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		List<Question> list=em.createQuery("select x from Question x").getResultList();		
		em.getTransaction().commit();
		return list;
	}
	
	@POST
	@Path("/addquestion")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public List<Question> addFish(Question question) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(question);//The actual insertion line
		em.getTransaction().commit();		
		List<Question> list=readQuestion();		
		return list;
	}	

}
