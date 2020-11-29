package Rest;

import Product.java.Product;
import Product.java.ProductDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("NotNullNullableValidation")
@Path("/get")
public final class MyGetProduct {

    @GET
    @Path("/ListProducts")
    public Object GetList() throws IOException {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Test7", "postgres", "2357")) {
            System.out.println("Connection Ok.");
            final ProductDAO productDAO = new ProductDAO(connection);

            List<Product> products = productDAO.getAll();
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            return javax.ws.rs.core.Response.ok(
                    gson.toJson(products)
            ).header(HttpHeaders.CACHE_CONTROL, "no-cache").build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Response.noContent();
    }

    @GET
    @Path("/FoundProduct/{org}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("org") String org) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Test7", "postgres", "2357")) {
            System.out.println("Connection Ok.");
            final ProductDAO productDAO = new ProductDAO(connection);

            List<Product> products = productDAO.getfound(org);
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            return javax.ws.rs.core.Response.ok(
                    gson.toJson(products)
                ).header(HttpHeaders.CACHE_CONTROL, "no-cache").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}