package Var2.Lab5;


import java.io.*;

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

        saveBank("C:\\Users\\dania\\IdeaProjects\\KATA\\src\\Labs\\Var2\\Lab5\\ReportBank.bin",bank);
        Bank bank1 = getBank("C:\\Users\\dania\\IdeaProjects\\KATA\\src\\Labs\\Var2\\Lab5\\ReportBank.bin");
        System.out.println(bank1);

    }

    public static Bank getBank(String fileName) {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))){
            return (Bank) objectInputStream.readObject(); //возвращает object, нужно явное приведение
        }catch (IOException e){
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveBank(String fileName, Bank saveBank){
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))){
            objectOutputStream.writeObject(saveBank);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
