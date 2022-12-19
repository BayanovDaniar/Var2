package Var2.Lab7;

public class LongTermInvest extends Deposit {

    public LongTermInvest() {}

    public LongTermInvest(String nameContributor, int sumContr) {
        super(nameContributor, sumContr);
    }

    @Override
    public double countSumContr(int quantityMonths) {
        double resultSum = 0;
        resultSum = getSumContr() * Math.pow((1 + 0.1), quantityMonths);
        return resultSum;
    }
}
