package Var2.Lab7;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bank implements Serializable {
    private String name;
    private List<Branch> branchList = new ArrayList<>();

    public Bank() {
    }

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

    @Override
    public String toString() {
        return "Bank{" +
                "name='" + name + '\'' +
                ", branchList=" + branchList +
                '}';
    }
}
