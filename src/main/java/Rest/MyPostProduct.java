package Rest;

import Product.java.Product;
import Product.java.ProductDAO;
import org.eclipse.jetty.server.Response;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@SuppressWarnings("NotNullNullableValidation")
@Path("/Post")
public class MyPostProduct {

    @POST
    @Path("/AddProduct")
    public String AddProduct(@FormParam("id") int id,
                             @FormParam("name") String name,
                             @FormParam("organization") String organization,
                             @FormParam("quantity") int quantity) throws IOException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Test7", "postgres", "2357")) {
            System.out.println("Connection Ok.");
            final ProductDAO productDAO = new ProductDAO(connection);
            productDAO.save(new Product(id, name, organization, quantity));
            return "Save ok";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Save not";
        }
    }
    @POST
    @Path("/DeleteProduct")
    public int deleteProduct(@FormParam("name") String name){
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Test7", "postgres", "2357")) {
            System.out.println("Connection Ok.");
            final ProductDAO productDAO = new ProductDAO(connection);
            if (productDAO.delete(name))
                return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Response.SC_NOT_FOUND;
    }
}
