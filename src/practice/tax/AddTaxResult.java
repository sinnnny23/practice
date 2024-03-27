package practice.tax;

public class AddTaxResult {
    private int supplyValue;
    private int tax;

    public AddTaxResult(int supplyValue, int tax) {
        this.supplyValue = supplyValue;
        this.tax = tax;
    }

    public int getSupplyValue() {
        return supplyValue;
    }

    public int getTax() {
        return tax;
    }
}
