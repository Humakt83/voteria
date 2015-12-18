package fi.ukkosnetti.voteria.client.rest;

public interface RestResultHandler<T> {

	void handleResult(T result); 
	
}
