package pl.kolodzianka.DaoImplements;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import pl.kolodzianka.Dao.BoxesDao;
import pl.kolodzianka.entities.Product;
import pl.kolodzianka.jsonUtils.JsonProductsList;
import pl.kolodzianka.jsonUtils.JsonShoppingBoxUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class BoxesDaoJsonImpl implements BoxesDao {

    private final static String PATH = "/Users/ania/Projekty/Sklepik/src/main/webapp/WEB-INF/boxes.json";
    static ObjectMapper mapper = new ObjectMapper();


    @Override
    public List findAll() {
        return null;
    }

    @Override
    public List<Product> findByUser(String userName) {
        return null;
    }

    @Override
    public void clearBox() {

    }

    @Override
    public void saveOrUpdate(String userName, List<Product> products) throws IOException{
        JsonShoppingBoxUtil boxList = new JsonShoppingBoxUtil();
        boxList.setUserName(userName);
        boxList.setProducts(products);
        File f = new File(PATH);
            if (f.exists() && f.isDirectory()) {
                boxList.setProducts((List<Product>) mapper.readValue(f, JsonProductsList.class));
                JsonGenerator g = mapper.getFactory().createGenerator(new FileOutputStream(f));
                mapper.writeValue(g, boxList);
            } else {
                mapper.writeValue(new FileOutputStream(PATH), boxList);
            }
    }


}

