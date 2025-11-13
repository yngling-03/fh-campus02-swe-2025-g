package at.campus02.swe;

import at.campus02.swe.logic.CalculatorImpl;
import at.campus02.swe.parser.Parser;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EndToEndTest {

    @Test
    public void additionAndMultiplicationScenario() throws Exception {
        double result = executeCalculation("endToEndAddMul.xml");
        assertEquals(20.0, result, 1e-9);
    }

    @Test
    public void subtractionAndDivisionScenario() throws Exception {
        double result = executeCalculation("endToEndSubDiv.xml");
        assertEquals(2.0, result, 1e-9);
    }

    private double executeCalculation(String resourceName) throws
            CalculatorException, FileNotFoundException, XMLStreamException, URISyntaxException {
        Parser parser = new Parser(new CalculatorImpl());
        File input = resolveResource(resourceName);
        return parser.parse(input);
    }

    private File resolveResource(String resourceName) throws URISyntaxException {
        URL resource = Thread.currentThread().getContextClassLoader().getResource(resourceName);
        assertNotNull("Missing test resource: " + resourceName, resource);
        return new File(resource.toURI());
    }
}
