package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    ;

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Employee getEmployeeById(long id) {
        EntityManager em = getEntityManager();
        Employee e = em.find(Employee.class, id);
        return e;
    }

    public List<Employee> getEmployeesByName(String name) {
        EntityManager em = getEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.name = :name", Employee.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = getEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        return query.getResultList();

    }

    public List<Employee> getEmployeesWithHighestSalary() {
        EntityManager em = getEntityManager();

        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(emp.salary) FROM Employee emp)", Employee.class);
        return query.getResultList();

    }

    public Employee createEmployee(String name, String address, int salary) {

        EntityManager em = getEntityManager();
        Employee e = new Employee(name, address, salary);
        try {
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
            return e;
        } finally {
            em.close();
        }

    }

    public void deleteEmployee(Employee employee) {

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Employee e = em.merge(employee);
            em.remove(e);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

}
