package pl.kolodzianka;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import pl.kolodzianka.entities.Product;

import java.util.List;

public class JsonProductsList {

    List<Product> products;

    public JsonProductsList() {
    }

    public JsonProductsList( List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
