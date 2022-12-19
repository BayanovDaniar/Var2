package Var2.Lab4;

public abstract class Deposit {
    private String nameContributor;
    private int sumContr;

    public Deposit(String nameContributor, int sumContr){
        this.nameContributor = nameContributor;
        this.sumContr = sumContr;
    }

    //Абстрактный класс, реализуется в подклассах
    public abstract double countSumContr(int quantityMonths);

    public String getNameContributor() {
        return nameContributor;
    }

    public void setNameContributor(String nameContributor) {
        this.nameContributor = nameContributor;
    }

    public int getSumContr() {
        return sumContr;
    }

    public void setSumContr(int sumContr) {
        this.sumContr = sumContr;
    }

}
