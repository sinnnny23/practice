package practice.tax;

import org.junit.Test;

public class AdditionalTax {
    public static AddTaxResult calculateTax(int principle) {
        double taxRate = 0.1;
        double supply = (double) (principle / 1.1);
        double tax = (double) (supply * taxRate);

        int tax2 = (int) Math.round(tax);
        int supplyValue = (int) Math.round(supply);

        return new AddTaxResult(supplyValue,tax2);
    }

    @Test
    public void testCalculateTax() {
        int principle = 1005;
        AddTaxResult result = calculateTax(principle);
        int supplyValue = result.getSupplyValue();
        int tax = result.getTax();

        System.out.println("원금: " + principle);
        System.out.println("공급가액: " + supplyValue);
        System.out.println("부가세: " + tax);
    }
}
