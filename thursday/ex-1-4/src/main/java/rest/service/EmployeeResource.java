package rest.service;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("employee")
public class EmployeeResource {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);
    Gson gson = new Gson();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllEmployees() {
        List<EmployeeDTO> empDTO = new ArrayList();
        List<Employee> employees = facade.getAllEmployees();

        for (Employee employee : employees) {
            empDTO.add(new EmployeeDTO(employee));
        }
        return gson.toJson(empDTO);

    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getId(@PathParam("id") int id) {

        Employee employee = facade.getEmployeeById(id);
        EmployeeDTO empDTO = new EmployeeDTO(employee);
        return gson.toJson(empDTO);

    }

    @GET
    @Path("/highestpaid")
    @Produces({MediaType.APPLICATION_JSON})
    public String getHighestPaid() {

        List<Employee> employees = facade.getEmployeesWithHighestSalary();
        List<EmployeeDTO> eDTO = new ArrayList();

        for (Employee e : employees) {
            eDTO.add(new EmployeeDTO(e));
        }

        return gson.toJson(eDTO);

    }

    @GET
    @Path("/name/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getName(@PathParam("name") String name) {

        List<EmployeeDTO> eDTO = new ArrayList();
        List<Employee> e = facade.getEmployeesByName(name);

        for (Employee employee : e) {
            eDTO.add(new EmployeeDTO(employee));
        }
        return gson.toJson(eDTO);

    }

}
