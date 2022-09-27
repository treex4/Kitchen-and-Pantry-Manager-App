package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFileInData.json");
        try {
            Kitchen k = reader.readKitchen();
            fail("IOException should have been caught.");
        } catch (IOException e) {
            // Caught the exception.
        }
    }

    @Test
    void testReaderEmptyKitchen() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyKitchen.json");
        try {
            Kitchen k = reader.readKitchen();
            assertEquals(0, k.getItemCount());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyGroceryList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGroceryList.json");
        try {
            GroceryList gl = reader.readGroceryList();
            assertEquals(0, gl.getItemCount());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralKitchen() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralKitchen.json");
        try {
            Kitchen k = reader.readKitchen();
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
    void testReaderGeneralGroceryList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGroceryList.json");
        try {
            GroceryList gl = reader.readGroceryList();
            gl = reader.readGroceryList();
            assertEquals(2, gl.getItemCount());
            assertEquals(gl.getAllItems(), "apple\npear\n");

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
