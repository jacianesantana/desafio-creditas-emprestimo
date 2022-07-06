package loan.api.model;

public enum LoanType {

    PERSONAL,
    WITH_GUARANTEE,
    CONSIGNED;

    public Integer getTaxes() {
        switch (this) {
            case PERSONAL: return 4;
            case WITH_GUARANTEE: return 3;
            case CONSIGNED: return 2;

            default: return null;
        }
    }

}
