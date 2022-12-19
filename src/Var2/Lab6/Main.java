package Var2.Lab6;


import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

        saveBank("C:\\Users\\dania\\IdeaProjects\\KATA\\src\\Labs\\Var2\\Lab6\\ReportBank.xml",bank);
        Bank bank1 = getBank("C:\\Users\\dania\\IdeaProjects\\KATA\\src\\Labs\\Var2\\Lab6\\ReportBank.xml");
        System.out.println(bank1);


    }

    public static Bank getBank(String fileName) {
        try(XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(fileName))){
            return (Bank) xmlDecoder.readObject();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void saveBank(String fileName, Bank saveBank){
        try(XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(fileName))){
            xmlEncoder.writeObject(saveBank);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
