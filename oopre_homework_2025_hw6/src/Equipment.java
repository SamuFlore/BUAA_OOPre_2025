public class Equipment {
    private String id;
    private int ce;

    public Equipment(String id, int ce) {
        this.id = id;
        this.ce = ce;
    }

    public String getID() {
        return id;
    }

    public String getClassName() {
        return this.getClass().getName();
    }

    public int getCe() {
        return ce;
    }
}
