package services;

public class APIBadResponseException extends Exception {

	public Error err;

	public APIBadResponseException(Error err) {
		this.err = err;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
