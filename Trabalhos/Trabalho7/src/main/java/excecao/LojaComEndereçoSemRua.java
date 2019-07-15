package excecao;

public class LojaComEndereçoSemRua extends RuntimeException {
    private final static long serialVersionUID = 1;

    public LojaComEndereçoSemRua(Exception e) {
    	super(e);
    }
    
    public LojaComEndereçoSemRua(String e) {
    	super(e);
    }
}