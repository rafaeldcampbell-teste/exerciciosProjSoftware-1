package excecao;

public class ValorMinimoException extends RuntimeException {
    private final static long serialVersionUID = 1;

    public ValorMinimoException(Exception e) {
	super(e);
    }
    
    public ValorMinimoException(String e) {
	super(e);
    }
}