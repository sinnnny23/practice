package practice.tax;

import org.junit.Test;

public class TaxType {
    public static Tax calculateTax(String type, int totalAmount) {
        double taxRate = 0.1;
        int supplyValue;
        int tax;

        if (type.contains("면세")) {
            supplyValue = totalAmount;
            tax = 0;
        } else if (type.contains("간이")) {
            if (totalAmount < 4000000) {
                supplyValue = totalAmount;
                tax = 0;
            } else {
                supplyValue = (int) Math.round(totalAmount / 1.1);
                tax = (int) Math.round(supplyValue * taxRate);
            }
        } else {
            throw new IllegalArgumentException("유효하지 않은 사업자 유형입니다.");
        }
        return new Tax(supplyValue, tax);
    }
    @Test
    public void testCalculateTax() {
        String type = "간이";
        int totalAmount = 100000005;
        Tax result = calculateTax(type, totalAmount);

        System.out.println("사업자 유형: " + type);
        System.out.println("합계금액: " + totalAmount
                + ", 공급가액: " + result.getSupplyValue()
                + ", 부가세: " + result.getTax()
        );
    }
}

class Tax{
    private int supplyValue;
    private int tax;

    public Tax(int supplyValue, int tax) {
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
