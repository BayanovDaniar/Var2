package Var2.Lab4;


import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Bank bank = new Bank("Tinko-ff");
        Branch branch = new Branch("Tomsk");
        Branch branch1 = new Branch("Nsb");
        bank.addBranch(branch);
        bank.addBranch(branch1);

        Deposit contribution = new ShortTermInvest("Elton John", 10000);
        Deposit contribution1 = new ShortTermInvest("Marshall Welles", 10200);
        branch.addContrib(contribution);
        branch1.addContrib(contribution1);
        Deposit contribution2 = new LongTermInvest("Mickael Jordan", 13000);
        Deposit contribution3 = new LongTermInvest("Mr Collins", 700);
        branch.addContrib(contribution2);
        branch1.addContrib(contribution3);

        BankReport bankReport = new BankReport(bank);
        String filePath = "C:\\Users\\dania\\IdeaProjects\\KATA\\src\\Labs\\Var2\\Lab4\\Report.txt";

        bankReport.createAndSaveReport(filePath);
        String report = bankReport.loadReport(filePath);
        System.out.println("\n\n\n" + report);
    }
}
