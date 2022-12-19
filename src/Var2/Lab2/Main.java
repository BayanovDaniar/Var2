package Var2.Lab2;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String bank_name;
        System.out.println("Enter Bank name : ");
        bank_name = enterString();
        Bank bank = new Bank(bank_name);

        String branch_name;
        System.out.println("Enter branch name : ");
        branch_name = enterString();
        Branch branch = new Branch(branch_name);

        String contribName;
        System.out.println("Enter name contributor: ");
        contribName = enterString();

        int sumContrib;
        System.out.println("Enter sum of contribution");
        sumContrib = enterInteger();
        Deposit contribution = new LongTermInvest(contribName, sumContrib);

        System.out.println("Bank " + bank.getName());
        System.out.println("Branch " + branch.getName());
        System.out.println("Contribution: ");
        System.out.println("\tName: " + contribution.getNameContributor());
        System.out.println("\tSum: " + contribution.getSumContr());

        ////////////////////////////////

        Scanner scanner = new Scanner(System.in);
        String bank_name1;
        System.out.println("Enter Bank name : ");
        bank_name1 = scanner.next();
        Bank bank1 = new Bank(bank_name1);

        String branch_name1;
        System.out.println("Enter branch name : ");
        branch_name1 = scanner.next();
        Branch branch1 = new Branch(branch_name1);

        String contribName1;
        System.out.println("Enter name contributor: ");
        contribName1 = scanner.next();

        int sumContrib1;
        System.out.println("Enter sum of contribution");
        sumContrib1 = scanner.nextInt();
        Deposit contribution1 = new LongTermInvest(contribName1, sumContrib1);

        System.out.println("Bank " + bank1.getName());
        System.out.println("Branch " + branch1.getName());
        System.out.println("Contribution: ");
        System.out.println("\tName: " + contribution1.getNameContributor());
        System.out.println("\tSum: " + contribution1.getSumContr());



    }

    static String enterString() throws IOException {
        byte b[] = new byte[100];
        System.in.read(b);
        String s = new String(b);
        s = s.trim();
        return s;
    }

    static int enterInteger() throws IOException {
        byte[] b = new byte[100];
        System.in.read(b);
        String s = new String(b);
        s = s.trim();
        return Integer.parseInt(s);
    }


}
