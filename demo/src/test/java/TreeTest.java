import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.*;
import org.junit.jupiter.api.Test;

import com.example.Association;
import com.example.BinaryTree;

public class TreeTest {
    
    @Test
    public void testInsertion() {
        BinaryTree<Integer, String> tree = new BinaryTree<>();
        tree.insertar(new Association<>(5, "cinco"));
        tree.insertar(new Association<>(3, "tres"));
        tree.insertar(new Association<>(7, "siete"));
        
        assertEquals("cinco", tree.search(5));
        assertEquals("tres", tree.search(3));
        assertEquals("siete", tree.search(7));
    }

    @Test
    public void testSearch() {
        BinaryTree<Integer, String> tree = new BinaryTree<>();
        tree.insertar(new Association<>(5, "cinco"));
        tree.insertar(new Association<>(3, "tres"));
        tree.insertar(new Association<>(7, "siete"));

        assertEquals("cinco", tree.search(5));
        assertEquals("tres", tree.search(3));
        assertEquals("siete", tree.search(7));
        assertNull(tree.search(10)); // Verificar que devuelve null para una clave no existente
    }


}
