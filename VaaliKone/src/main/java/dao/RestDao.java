package dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import data.Question;
import data.Users;

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
	public List<Question> addQuestion(Question question) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(question);//The actual insertion line
		em.getTransaction().commit();		
		List<Question> list=readQuestion();		
		return list;
	}
	
	@GET
	@Path("/readtoupdatequestion/{id}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public Question readToUpdateQuestion(@PathParam("id") int id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Question q=em.find(Question.class, id);
		em.getTransaction().commit();
		return q;
	}	
	
	@PUT
	@Path("/updatequestion")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public List<Question> updateQuestion(Question question) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Question q=em.find(Question.class, question.getId());
		if (q!=null) {
			em.merge(question);//The actual update line
		}
		em.getTransaction().commit();
		//Calling the method readQuestion() of this service
		List<Question> list=readQuestion();		
		return list;
	}	
	
	@GET
	@Path("/deleteQuestion/{id}")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=UTF-8")
	@Consumes(MediaType.APPLICATION_JSON+ ";charset=UTF-8")
	
	public List<Question> deleteQuestion(@PathParam("id") int id,
			@Context HttpServletRequest request,
			@Context HttpServletResponse response
			) {
			
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		Question q=em.find(Question.class, id);
			if (q!=null) {
				em.remove(q);
			}
		em.getTransaction().commit();
		
		List<Question> list=readQuestion();
		return list;				
	}
	
	@GET
	@Path("/readusers")
	@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
	public List<Users> readUsers() {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		List<Users> list=em.createQuery("select x from Users x").getResultList();		
		em.getTransaction().commit();
		return list;
	}
	
	@POST
	@Path("/adduser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void addFish(Users user) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);//The actual insertion line
		em.getTransaction().commit();		
	}	
	

}
