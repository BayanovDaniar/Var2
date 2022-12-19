package Var2.Lab2;

import java.util.ArrayList;
import java.util.List;

public class Branch {
    private String name;
    private List<Deposit> contributionList = new ArrayList<>();

    public Branch(String name) {
        this.name = name;
    }

    public int getSumOfContrib() {
        int sumOfContrib = 0;
        for(Deposit contribution : contributionList){
            sumOfContrib += contribution.getSumContr();
        }
        return sumOfContrib;
    }

    public void addContrib(Deposit contribution){
        contributionList.add(contribution);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Deposit> getContributionList() {
        return contributionList;
    }

    public void setContributionList(List<Deposit> contributionList) {
        this.contributionList = contributionList;
    }
}
