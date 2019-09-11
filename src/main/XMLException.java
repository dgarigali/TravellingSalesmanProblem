package main;

/**
 * XML Exception class extends Exception superclass. 
 * Corresponds to an user defined exception that checks the XML file coherency.
 * 
 * @author Maria Carolina Roque, nº 81398
 * @author Daniel Pestana, nº 93883
 * @author Carlos Simões, nº 93921
 */

public class XMLException extends RuntimeException  {

    /**Serial version for handling warning*/
    private static final long serialVersionUID = 1L;
    
	/** XML Exception constructor 
	 * @param message - exception message 
	 */

    public XMLException(String message) {
        super(message);
    }
}
