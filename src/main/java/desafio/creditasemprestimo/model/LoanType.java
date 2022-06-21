package desafio.creditasemprestimo.model;

public enum LoanType {

    EMPRESTIMO_PESSOAL,
    EMPRESTIMO_COM_GARANTIA,
    CONSIGNADO;

    public Integer getTaxes() {
        switch (this) {
            case EMPRESTIMO_PESSOAL: return 4;
            case EMPRESTIMO_COM_GARANTIA: return 3;
            case CONSIGNADO: return 2;

            default: return null;
        }
    }

}
