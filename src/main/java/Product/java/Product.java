package Product.java;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Product {
    @JsonProperty("id")
    public int id;
    @JsonProperty("name")
    public String name;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", organization='" + organization + '\'' +
                ", quantity=" + quantity +
                '}';
    }
    @JsonProperty("organization")
    public String organization;
    @JsonProperty("quantity")
    public int quantity;
    private static final Type listProducts = new TypeToken<ArrayList<Product>>(){}.getType();

    public static ArrayList<Product> read(String fileName) {
        try {
            return new Gson().fromJson(new BufferedReader(new FileReader(fileName)), listProducts);
        } catch (
                FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @JsonCreator
    public Product(@JsonProperty int id, @JsonProperty String name, @JsonProperty String organization, @JsonProperty int quantity) {
        this.id = id;
        this.name = name;
        this.organization = organization;
        this.quantity = quantity;
    }
}
