package practice.tax;

import org.junit.Test;

public class AdditionalTax {
    public static AddTaxResult calculateTax(int principle) {
        double taxRate = 0.1;
        int supplyValue = (int) Math.round(principle / 1.1);
        int tax = (int) Math.round(supplyValue * taxRate);

        return new AddTaxResult(supplyValue,tax);
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
