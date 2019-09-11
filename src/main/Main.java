package main;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

import sim.ISimulator;
import sim.Simulator;

/**
 * Main class to run the program.
 * Contains necessary IO and SAX parser exceptions from reading XML file. 
 * 
 * @author Maria Carolina Roque, nº 81398
 * @author Daniel Pestana, nº 93883
 * @author Carlos Simões, nº 93921
 */

public class Main {
    
	/** Reads data from XML (using SAX parser), from which it creates all necessary objects and runs simulator.
	 * @param args - name of XML file that must be validated by proper DTD
	 * @exception IOException - if file not found 
	 * @exception SAXException - indicates basic error or warning information
	 * @exception ParserConfigurationException - indicates a serious configuration error
	 */
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        
        //Check argument
        if (args.length != 1) {
            System.out.println("USAGE: simulator <filename>");
            System.exit(-1);
        }
        
        //Instantiate simulation and XML
        ISimulator sim = new Simulator();
        XML xml = new XML(sim);
        
        // Build the SAX parser
        SAXParserFactory fact = SAXParserFactory.newInstance();
        try {
        
            // Parse XML document
            fact.setValidating(true);
            SAXParser saxParser = fact.newSAXParser();
            saxParser.parse(new File(args[0]), xml);

        } catch(IOException e) {
            System.err.println("IO error");
            System.exit(-1);
        } catch(SAXException e) {
            System.err.println("Parser error");
            System.exit(-1);
        } catch(ParserConfigurationException e) {
            System.err.println("Parser configuration error");
            System.exit(-1);
        }  

        //Run simulation
        sim.run();
    }
}