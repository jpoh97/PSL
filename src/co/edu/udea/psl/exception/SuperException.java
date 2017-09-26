package co.edu.udea.psl.exception;

import org.apache.log4j.Logger;

/**
 * Class inheriting from Exception to handle exceptions internally
 * 
 * @author Juan Pablo Ospina Herrera - jpoh97@gmail.com
 * @since 1.8
 * @version 1.0
 *
 */

public class SuperException extends Exception {

        // Use Log4j-1.2.17.jar
	private static final long serialVersionUID = 8867410660071843507L;
	Logger log = Logger.getLogger(this.getClass());

	public SuperException() {
		
	}

	public SuperException(String message, Throwable cause, 
                boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		log.error(message, cause);
	}

	public SuperException(String message, Throwable cause) {
		super(message, cause);
		log.error(message, cause);
	}

	public SuperException(String message) {
		super(message);
		log.error(message);
	}

	public SuperException(Throwable cause) {
		super(cause);
		log.error(cause);
	}

}