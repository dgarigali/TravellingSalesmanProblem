package main;

import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import sim.ISimulator;

/**
 * XML class reads all necessary data for running the program. Uses SAX parser for reading XML file, that must be validated by proper DTD.
 * This class is only accessible in this package.
 * 
 * @author Maria Carolina Roque, nº 81398
 * @author Daniel Pestana, nº 93883
 * @author Carlos Simões, nº 93921
 */

class XML extends DefaultHandler {
    
	/**Instance of the simulation to be used*/
    private final ISimulator sim;
	/**ID of the node*/
    private int nodeidx; 
    /**ID of the target node*/
    private int targetnode;
    /**Flag that checks if character to be read is the edge's weight*/
    private boolean weight_flag = false;
    /**Total number of nodes*/
    private int nbnodes;
    /**ID of nest node*/
    private int nestnode;
    
    /**XML Constructor
     * @param sim - instance of simulator
     */
    public XML(ISimulator sim) {
        this.sim = sim;
    }

    /**Runs before starting the read of the document (in this case, it does nothing, only overrides the method)*/
    @Override
    public void startDocument() {
    }
    
    /**Runs after reading entire XML file. Checks if nest node exists and if number of nodes is coherent
     * @exception XMLException - checks user defined exception regarding coherency of XML file
     */
    @Override
    public void endDocument() { 
        try {
            if (!sim.nodeExists(nestnode))
                throw new XMLException("Nest node (id=" + nestnode + ") has no tag associated");
            if (sim.getNumNodes() != nbnodes)
                throw new XMLException("Number of nodes (nbnodes=" + nbnodes + ") different from number of nodes with any tag associated (" + sim.getNumNodes() + ")");
        } catch (XMLException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(-1);
        }
    }
    
    /**Reads info of each XML tag element
     * @param uri - namespace universal resource identifier 
     * @param name - element local name
     * @param tag - element tag
     * @param atts - contains element attributes (name, value, number of elements, ...)
     * @exception XMLException - checks user defined exception regarding coherency of XML file
     * @exception NumberFormatException - checks wrong XML parameter format 
     */
    @Override
    public void startElement(String uri, String name, String tag, Attributes atts) throws XMLException, NumberFormatException {
        
        switch (tag) {

            case "simulation":
                double finalinst = 0, plevel = 0;
                int antcolsize = 0;
                for (int i = 0; i < atts.getLength(); i++) {
                    switch (atts.getLocalName(i)) {
                        case "finalinst":
                            try {
                                finalinst = Double.parseDouble(atts.getValue(i));
                                if (finalinst < 0)
                                    throw new XMLException("finalinst must be a positive number!");
                            } catch (NumberFormatException e) {
                                System.out.println("Error: finalinst must be a decimal number!");
                                System.exit(-1);
                            } catch (XMLException e) {
                                System.out.println("Error: " + e.getMessage());
                                System.exit(-1);
                            } break;
                        case "antcolsize":
                            try {
                                antcolsize = Integer.parseInt(atts.getValue(i));
                                if (antcolsize < 0)
                                    throw new XMLException("antcolsize must be a positive number!");
                            } catch (NumberFormatException e) {
                                System.out.println("Error: antcolsize must be an integer number!");
                                System.exit(-1);
                            } catch (XMLException e) {
                                System.out.println("Error: " + e.getMessage());
                                System.exit(-1);
                            } break;
                        case "plevel":
                            try {
                                plevel = Double.parseDouble(atts.getValue(i));
                                if (plevel < 0) 
                                    throw new XMLException("plevel must be a positive number!");
                            } catch (NumberFormatException e) {
                                System.out.println("Error: plevel must be a decimal number!");
                                System.exit(-1);
                            } catch (XMLException e) {
                                System.out.println("Error: " + e.getMessage());
                                System.exit(-1);
                            } break;
                    }
                }   
                sim.setSimulationParameters(finalinst, antcolsize, plevel);
                break;

            case "graph":
                for (int i = 0; i < atts.getLength(); i++) {
                    switch (atts.getLocalName(i)) {
                        case "nbnodes":
                            try {
                                nbnodes = Integer.parseInt(atts.getValue(i));
                                if (nbnodes < 0)
                                    throw new XMLException("nbnodes must be a positive number!");
                            } catch (NumberFormatException e) {
                                System.out.println("Error: nbnodes must be an integer number!");
                                System.exit(-1);
                            } catch (XMLException e) {
                                System.out.println("Error: " + e.getMessage());
                                System.exit(-1);
                            } break;
                        case "nestnode":
                            try {
                                nestnode = Integer.parseInt(atts.getValue(i)); 
                            } catch (NumberFormatException e) {
                                System.out.println("Error: nestnode must be an integer number!");
                                System.exit(-1);
                            } break;
                    }
                }   
                sim.setGraphParameters(nbnodes, nestnode);
                break;

            case "node":
                try {
                    nodeidx = Integer.parseInt(atts.getValue(0));
                    sim.addNode(nodeidx);
                } catch (NumberFormatException e) {
                    System.out.println("Error: All nodeidx must be integer numbers!");
                    System.exit(-1);
                }
                break;

            case "weight":
                try {
                    targetnode = Integer.parseInt(atts.getValue(0));
                    sim.addNode(targetnode);
                    weight_flag = true;
                } catch (NumberFormatException e) {
                    System.out.println("Error: targetnode must be an integer number!");
                    System.exit(-1);
                }  
                break;

            case "move":
                double alpha = 0, beta = 0, delta = 0;
                for (int i = 0; i < atts.getLength(); i++) {
                    switch (atts.getLocalName(i)) {
                        case "alpha":
                            try {
                                alpha = Double.parseDouble(atts.getValue(i));
                                if (alpha < 0) 
                                    throw new XMLException("alpha must be a positive number!");
                            } catch (NumberFormatException e) {
                                System.out.println("Error: alpha must be a decimal number!");
                                System.exit(-1);
                            } catch (XMLException e) {
                                System.out.println("Error: " + e.getMessage());
                                System.exit(-1);
                            } break;
                        case "beta":
                            try {
                                beta = Double.parseDouble(atts.getValue(i));
                                if (beta < 0) 
                                      throw new XMLException("beta must be a positive number!");
                            } catch (NumberFormatException e) {
                                System.out.println("Error: beta must be a decimal number!");
                                System.exit(-1);
                            } catch (XMLException e) {
                                System.out.println("Error: " + e.getMessage());
                                System.exit(-1);
                            } break;
                        case "delta":
                            try {
                                delta = Double.parseDouble(atts.getValue(i));
                                if (delta < 0) 
                                    throw new XMLException("delta must be a positive number!");  
                            } catch (NumberFormatException e) {
                                System.out.println("Error: delta must be a decimal number!");
                                System.exit(-1);
                            } catch (XMLException e) {
                                System.out.println("Error: " + e.getMessage());
                                System.exit(-1);
                            } break;
                    }
                }   
                sim.setMoveParams(alpha, beta, delta);
                break;

            case "evaporation":
                double eta = 0, rho = 0;
                for (int i = 0; i < atts.getLength(); i++) {
                    switch (atts.getLocalName(i)) {
                        case "eta":
                            try {
                                eta = Double.parseDouble(atts.getValue(i));
                                if(eta < 0)
                                    throw new XMLException("eta must be a positive number!"); 
                            } catch (NumberFormatException e) {
                                System.out.println("Error: eta must be a decimal number!");
                                System.exit(-1);
                            } catch (XMLException e) {
                                System.out.println("Error: " + e.getMessage());
                                System.exit(-1);
                            } break;
                        case "rho":
                            try {
                                rho = Double.parseDouble(atts.getValue(i));
                                if(rho < 0) 
                                    throw new XMLException("rho must be a positive number!"); 
                            } catch (NumberFormatException e) {
                                System.out.println("Error: rho must be a decimal number!");
                                System.exit(-1);
                            } catch (XMLException e) {
                                System.out.println("Error: " + e.getMessage());
                                System.exit(-1);
                            } break;
                    }
                }   
                sim.setEvapParms(eta, rho);
                break;
            default:
                break;
        }
    }
    
    /**
     * Reads everything outside the tags. In this case, the weight of the edges.
     * @param ch - array of characters
     * @param start - first character of array
     * @param length - length of the character 
     * @exception XMLException - checks user defined exception regarding coherency of XML file
     * @exception NumberFormatException - checks wrong XML parameter format 
     */
    @Override
    public void characters(char[]ch,int start,int length) throws NumberFormatException {
        
        //Verify it corresponds to weight value
        if (weight_flag) {
            weight_flag = false;
            int weight = 0;
            try {
                weight = Integer.parseInt(new String(ch, start, length));
                if(weight < 0)
                    throw new XMLException("weight must be a positive number!");
                if (sim.edgeExists(nodeidx, targetnode))
                    throw new XMLException("Duplicated edge between " + nodeidx + " and " + targetnode);
                if (nodeidx == targetnode)
                    throw new XMLException("Edge connected between same node (id=" + nodeidx + ")");
                sim.addEdge(nodeidx, targetnode, weight);
            } catch (NumberFormatException e) {
                System.out.println("Error: All weights must be integer numbers!");
                System.exit(-1);
            } catch (XMLException e) {
                System.out.println("Error: " + e.getMessage());
                System.exit(-1);
            }
        }
    }
    
    /**Rewrites SAX parse warning*/
    @Override
    public void warning(SAXParseException exception) throws SAXParseException {
        System.out.println("Warning: " + exception.getMessage());
    }

    /**Rewrites SAX parse error. Exits program.*/
    @Override
    public void error(SAXParseException exception) throws SAXParseException {
        System.out.println("Error: " + exception.getMessage());
        System.exit(-1);
    }

    /**Rewrites SAX parse fatal error. Exits program.*/
    @Override
    public void fatalError(SAXParseException exception) throws SAXParseException {
        System.out.println("Fatal error: " + exception.getMessage());
        System.exit(-1);
    }       
}