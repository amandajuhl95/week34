package rest.service;

import com.google.gson.Gson;
import dto.CustomerDTO;
import entities.BankCustomer;
import facades.CustomerFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("bankcustomer")
public class BankCustomerResource {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");;
    CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
    Gson gson = new Gson();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getId(@PathParam("id") long id) {
        CustomerDTO customer = facade.getCustomerByID(id);
        return gson.toJson(customer);
    }

    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllBankCustomers() {
        List<CustomerDTO> cusDTO = new ArrayList();
        List<BankCustomer> customers = facade.getAllBankCustomers();

        for (BankCustomer customer : customers) {

            cusDTO.add(new CustomerDTO(customer));
        }
        return gson.toJson(cusDTO);
    }

}
