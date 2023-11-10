import java.util.*;

import javax.naming.*;
import dao.IDao;
import entities.*;


public class TestEmploye {
	public static IDao<Student> lookUpStudentRemote() throws NamingException {
		Hashtable<String, String> jndiProperties = new Hashtable<>();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");

		Context context = new InitialContext(jndiProperties);
		return (IDao<Student>) context.lookup("ejb:/G4Serveur/StudentService!dao.IDao");

	}
	
	public static IDao<Role> lookUpRoleRemote() throws NamingException{
		
		Hashtable<String, String> jndiProperties = new Hashtable<>();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		Context context = new InitialContext(jndiProperties);
		return (IDao<Role>) context.lookup("ejb:/G4Serveur/RoleService!dao.IDao");
		
	}
	
public static IDao<Filiere> lookUpFilierRemote() throws NamingException{
		
	Hashtable<String, String> jndiProperties = new Hashtable<>();
	jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
	jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
	Context context = new InitialContext(jndiProperties);
		return (IDao<Filiere>) context.lookup("ejb:/G4Serveur/FiliereService!dao.IDao");
		
	}

	public static void main(String[] args) {
		try {
			
			System.out.println("afsasfsaf");
			IDao<Student> st = TestEmploye.lookUpStudentRemote();
			IDao<Role> roleDao = TestEmploye.lookUpRoleRemote();
			Student student1 = new Student("student1@example.com", "password1", "First1", "Last1", "123-456-7891");
			st.create(student1);
			Role role = new Role("admin");
			roleDao.create(role);
			Student studnet = st.findById(1);
			
			List<User> students = new ArrayList();
			students.add(studnet);
			role.setUsers(students);
			Role r = roleDao.findById(1);
			roleDao.update(r);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
