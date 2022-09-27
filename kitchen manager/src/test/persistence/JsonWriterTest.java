package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Kitchen k = new Kitchen();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException should have been thrown.");
        } catch (IOException e) {
            // Caught the exception.
        }
    }

    @Test
    void testWriterEmptyKitchen() {
        try {
            Kitchen k = new Kitchen();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyKitchen.json");
            writer.open();
            writer.write(k);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyKitchen.json");
            k = reader.readKitchen();
            assertEquals(0, k.getItemCount());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyGroceryList() {
        try {
            GroceryList gl = new GroceryList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGroceryList.json");
            writer.open();
            writer.write(gl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGroceryList.json");
            gl = reader.readGroceryList();
            assertEquals(0, gl.getItemCount());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralKitchen() {
        try {
            Kitchen k = new Kitchen();
            k.addFoodItem("apple", "fruit", "fridge", 1, "2022-08-01");
            k.addFoodItem("pear", "fruit", "fridge", 1, "2022-08-03");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralKitchen.json");
            writer.open();
            writer.write(k);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralKitchen.json");
            k = reader.readKitchen();
            assertEquals(2, k.getItemCount());
            assertEquals(k.getFoodItem("apple"),
                    "Name: apple\nType: fruit\nLocation: fridge\nQuantity: 1\nExpiry Date: 2022-08-01\n");
            assertEquals(k.getFoodItem("pear"),
                    "Name: pear\nType: fruit\nLocation: fridge\nQuantity: 1\nExpiry Date: 2022-08-03\n");

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralGroceryList() {
        try {
            GroceryList gl = new GroceryList();
            gl.addItem("apple");
            gl.addItem("pear");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGroceryList.json");
            writer.open();
            writer.write(gl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGroceryList.json");
            gl = reader.readGroceryList();
            assertEquals(2, gl.getItemCount());
            assertEquals(gl.getAllItems(), "apple\npear\n");

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
