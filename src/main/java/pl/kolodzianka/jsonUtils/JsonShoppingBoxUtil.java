package pl.kolodzianka.jsonUtils;

import pl.kolodzianka.entities.Product;
import java.util.List;

public class JsonShoppingBoxUtil {

    String userName;

    List<Product> products;

    public JsonShoppingBoxUtil() {
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
