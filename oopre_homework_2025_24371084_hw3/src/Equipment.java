public class Equipment {
    private String id;
    private boolean isEquipped;

    public Equipment(String id) {
        this.isEquipped = false;
        this.id = id;
    }

    public String getID() {
        return id;
    }

    public void setIsEquipped() {
        isEquipped = true;
    }

    public String getClassName() {
        return this.getClass().getName();
    }
}
