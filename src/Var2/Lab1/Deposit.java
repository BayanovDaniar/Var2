package Var2.Lab1;

public abstract class Deposit {
    private String nameContributor;
    private int sumContr;

    public Deposit(String nameContributor, int sumContr) throws VkladException{
        this.nameContributor = nameContributor;
        try{
            if(sumContr <= 0){
                throw new VkladException();
            }
        }catch (VkladException e){
            System.out.println("it is not possible to create a contribution");
            throw e;
        }
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

    public static class KolichestvoException extends Exception{};

    public static class VkladException extends Exception{};
}
