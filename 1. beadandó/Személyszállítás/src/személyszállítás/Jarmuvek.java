package személyszállítás;

public enum Jarmuvek {

    VONAT, BUSZ;

    public String toString() {
        switch (this) {
            case VONAT:
                return "vonat";
            case BUSZ:
                return "busz";
            default:
                return "";
        }
    }
}
