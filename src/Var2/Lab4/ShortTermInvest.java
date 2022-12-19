package Var2.Lab4;

public class ShortTermInvest extends Deposit {

    public ShortTermInvest(String nameContributor, int sumContr) {
        super(nameContributor, sumContr);
    }

    @Override
    public double countSumContr(int quantityMonths) {
        double resultSum = 0;
        resultSum = getSumContr() * Math.pow((1 + 0.05), quantityMonths);
        return resultSum;
    }
}
