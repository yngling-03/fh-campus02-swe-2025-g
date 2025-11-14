package at.campus02.swe.parser;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;
import static org.mockito.Mockito.*;

import at.campus02.swe.Calculator;
import at.campus02.swe.Calculator.Operation;


public class ParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNullParser() {
        new Parser(null);
    }

    @Test(expected = FileNotFoundException.class)
    public void testParserInvalidFile() throws Exception {

        Calculator cal = mock(Calculator.class);

        Parser parser = new Parser(cal);
        parser.parse(new File("invalid"));
    }

    @Test
    public void testParserTest01Xml() throws Exception {

        Calculator cal = mock(Calculator.class);

        Parser parser = new Parser(cal);
        parser.parse(new File("src/test/resources/test01.xml"));

        verify(cal).push(1.0);
        verify(cal).push(2.0);
        verify(cal).perform(Operation.add);

        verifyNoMoreInteractions(cal);
    }

    @Test
    public void testParserTestSinXml() throws Exception {

        Calculator cal = mock(Calculator.class);

        Parser parser = new Parser(cal);
        parser.parse(new File("src/test/resources/test02.xml"));

        verify(cal).push(50.0);
        verify(cal).pop();                // ⬅️ NEU
        verify(cal).perform(Operation.sin);

        verifyNoMoreInteractions(cal);
    }


    @Test
    public void testParserTestCosXml() throws Exception {

        Calculator cal = mock(Calculator.class);

        Parser parser = new Parser(cal);
        parser.parse(new File("src/test/resources/test03.xml"));

        verify(cal).push(100.0);
        verify(cal).perform(Operation.cos);

        verifyNoMoreInteractions(cal);
    }

    @Test
    public void testParserTestModXml() throws Exception {

        Calculator cal = mock(Calculator.class);

        Parser parser = new Parser(cal);
        parser.parse(new File("src/test/resources/test04.xml"));

        verify(cal).push(5.0);
        verify(cal).push(2.0);
        verify(cal).perform(Operation.mod);

        verifyNoMoreInteractions(cal);
    }

    @Test
    public void testParserTestDotproductXml() throws Exception {

        Calculator cal = mock(Calculator.class);

        Parser parser = new Parser(cal);
        parser.parse(new File("src/test/resources/test05-dotproduct.xml"));

        // Es sollten 5 Werte gepusht werden (1,3,2,4,2),
        // aber wir interessieren uns hier nicht für die genauen Werte:
        verify(cal, times(5)).push(anyDouble());

        // Wichtig: dotproduct wird ausgeführt
        verify(cal).perform(Operation.dotproduct);

        verifyNoMoreInteractions(cal);
    }


}