package Var2.Lab4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BankReport {

    private Bank bank;

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public BankReport(Bank bank) {
        this.bank = bank;
    }

    private String createReport(){
        StringBuilder report = new StringBuilder(bank.getName() + "\n\n");
        for(Branch branch : bank.getBranchList()){
            report.append("Филиал: ").append(branch.getName()).append("\n");
            for(Deposit contribution : branch.getContributionList()){
                report.append(contribution.getNameContributor()).append(" | ").
                        append(contribution.getSumContr()).append("\n");
            }
            report.append("--------------\n").append("Общая сумма вкладов по филиалу :").
                    append(branch.getSumOfContrib()).append("\n\n");
        }
        return report.toString();
    }

    public void createAndSaveReport(String fileName) throws IOException {
        String report = createReport();
        FileOutputStream fileOutput = new FileOutputStream(fileName);
        fileOutput.write(report.getBytes());
        fileOutput.close();
        System.out.println("Success write report!");
    }


    public String loadReport(String fileName){
        String report = null;
        //try с ресурсами автоматически закрывает поток
        try(FileInputStream fileInput = new FileInputStream(fileName)) {
            byte []bytesReport = new byte[fileInput.available()];
            fileInput.read(bytesReport);
            report = new String(bytesReport);

        }catch (IOException e){
            System.out.println("Exception was caught in Class" + this.getClass().getName());
        }
        return report;
    }
}
