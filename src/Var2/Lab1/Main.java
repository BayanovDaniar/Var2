package Var2.Lab1;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank("Sber");
        Branch branch = new Branch("First");

        Deposit contribution = null;
        try {
            contribution = new LongTermInvest("John Smith" , 12000);
        } catch (Deposit.VkladException e) {
            e.printStackTrace();
        }
        Deposit contribution1 = null;
        try {
            contribution1 = new ShortTermInvest("Alice Smith", 13000);
        } catch (Deposit.VkladException e) {
            e.printStackTrace();
        }

        System.out.print("Final contribution for " + contribution.getNameContributor() + " : ");
        System.out.println(contribution.countSumContr(3));
        System.out.print("Final contribution for + " + contribution1.getNameContributor() + " : ");
        System.out.println(contribution1.countSumContr(4));


        System.out.println("///////////////");

        //Call exception
        System.out.print("Final contribution for" + contribution1.getNameContributor() + " : ");
        System.out.println(contribution1.countSumContr(-2));


        System.out.println("///////////////");

        //Call exception constructor
        try {
            Deposit contribution2 = new ShortTermInvest("Dan Alabama", -1000);
        }catch (Deposit.VkladException e){
            System.out.println("Failed to create a contribution");
        }

    }


}
