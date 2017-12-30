package Hbdemo;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class BookTest {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure()	
				.addAnnotatedClass(Book.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		Book b1=new Book("java");
		Book b2=new Book("c");
		Book b3=new Book("Hibernate");
		Book b4=new Book("Spring");
		
		Transaction t = session.beginTransaction();
		
		session.save(b1);
		session.save(b2);
		session.save(b3);
		session.save(b4);
		
		
		
		Query query = session.getNamedQuery("Book.findAll");
		
		
		System.out.println("done");
		
		List<Book> result=query.getResultList();
		
		Iterator<Book> itr=result.iterator();
		
		while(itr.hasNext())
		{
			Book b=(Book) itr.next();
			System.out.println(b);
		}
		
		
		System.out.println("printing by id");
		Query query1 = session.getNamedQuery("Book.findById");
		
		query1.setParameter("id", 3);
		Book bb=(Book) query1.getSingleResult();
		System.out.println(bb);
	
		t.commit();
		session.close();
		
		
		
		
		
	}
}
