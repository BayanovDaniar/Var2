package Var2.Lab4;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private String name;
    private List<Branch> branchList = new ArrayList<>();

    public Bank(String name) {
        this.name = name;
    }

    public void addBranch(Branch branch){
        branchList.add(branch);
    }

    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
