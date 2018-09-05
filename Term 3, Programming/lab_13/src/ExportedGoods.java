/**
 * Created by Yule Cat on 17.12.2017.
 */
public class ExportedGoods {
    private String nameOfGood;
    private String importingCountry;
    private int amountOfConsignment;

    public ExportedGoods() {
    }

    public ExportedGoods(String nameOfGood, String importingCountry, int amountOfConsignment) {
        this.nameOfGood = nameOfGood;
        this.importingCountry = importingCountry;
        this.amountOfConsignment = amountOfConsignment;
    }

    @Override
    public String toString() {
        return nameOfGood + "   " + importingCountry + "    " + amountOfConsignment;
    }

    public void setNameOfGood(String nameOfGood) {
        this.nameOfGood = nameOfGood;
    }

    public void setImportingCountry(String importingCountry) {
        this.importingCountry = importingCountry;
    }

    public void setAmountOfConsignment(int amountOfConsignment) {
        this.amountOfConsignment = amountOfConsignment;
    }

    public String getNameOfGood() {
        return nameOfGood;
    }

    public String getImportingCountry() {
        return importingCountry;
    }

    public int getAmountOfConsignment() {
        return amountOfConsignment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExportedGoods)) return false;

        ExportedGoods that = (ExportedGoods) o;

        if (getAmountOfConsignment() != that.getAmountOfConsignment()) return false;
        if (getNameOfGood() != null ? !getNameOfGood().equals(that.getNameOfGood()) : that.getNameOfGood() != null)
            return false;
        return getImportingCountry() != null ? getImportingCountry().equals(that.getImportingCountry()) : that.getImportingCountry() == null;
    }
}
