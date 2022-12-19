package Var2.Lab1;

import java.util.Arrays;

public class ShortTermInvest extends Deposit {

    public ShortTermInvest(String nameContributor, int sumContr) throws VkladException {
        super(nameContributor, sumContr);
    }

    @Override
    public double countSumContr(int quantityMonths) {
        double resultSum = 0;
        //Расчет конечной суммы вклада
        try {
            if(quantityMonths < 0){
                throw new KolichestvoException();
            }
            resultSum = getSumContr() * Math.pow((1 + 0.05), quantityMonths);
        }catch (KolichestvoException e){
            System.out.println("EXCEPTION ! Incorrect quantity months");
            System.out.println(Arrays.toString(e.getStackTrace()));
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return resultSum;
    }
}
