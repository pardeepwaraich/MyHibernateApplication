package trng.imcs.jpa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SessionTest {

	public static void main(String[] args) {
		SessionFactory sf = SessionFactoryBuilder.getSessionFactory();
		System.out.println("Session Factory created");
		Session session = sf.openSession();
		
		session.close();
		System.out.println("Session Opend and closed");
	}

}
