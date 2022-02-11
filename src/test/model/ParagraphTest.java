package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParagraphTest {
    Paragraph paraTest;


    @BeforeEach
    void runBefore() {
        paraTest = new Paragraph();
    }

    @Test
    void testConstructor() {
        assertEquals(25, paraTest.getParagraphAsList().size());
        assertTrue(paraTest.getParagraphAsString() instanceof String);
    }

    @Test
    void testGetInputPara(){
        paraTest.setInputPara("hello");
        assertEquals("hello", paraTest.getInputPara());
    }

    @Test
    void testGenerateParagraph() {
        assertEquals(25, paraTest.getParagraphAsList().size());
    }

    @Test
    void testConvertParaToString() {
        List<String> listTest = new ArrayList<String>();
        listTest.add("Hello");
        listTest.add("Hello");
        listTest.add("Hello");

        assertEquals("Hello Hello Hello ", paraTest.convertParaToString(listTest));
    }

    @Test
    void testConvertStringToListWithChar() {
        String sentence = "This had 11";
        assertEquals(11, paraTest.convertStringToListWithChar(sentence).size());
        assertEquals('h',paraTest.convertStringToListWithChar(sentence).get(1));
    }


    @Test
    void testGetNumTypedCorrect() {
        paraTest.setParagraph("This is a test to see how many are equal");
        assertEquals(0, paraTest.getNumTypedCorrect(paraTest, paraTest.getParagraphAsString(),
                "asdf"));
        assertEquals(4, paraTest.getNumTypedCorrect(paraTest, paraTest.getParagraphAsString(),
                "This"));
        assertEquals(3, paraTest.getNumTypedCorrect(paraTest, paraTest.getParagraphAsString(),
                "this"));
        assertEquals(3, paraTest.getNumTypedCorrect(paraTest, paraTest.getParagraphAsString(),
                "this"));
        assertEquals(40, paraTest.getNumTypedCorrect(paraTest, paraTest.getParagraphAsString(),
                "This is a test to see how many are equal"));
    }
}
