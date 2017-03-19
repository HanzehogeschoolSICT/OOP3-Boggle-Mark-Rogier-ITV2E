package model.trie;

import java.util.ArrayList;

public class Branch {

	private String name;
	private ArrayList<Branch> branches;
	
	public Branch(String name) {
		this.name = name;
		this.branches = new ArrayList<Branch>();
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Branch> getBranches() {
		return branches;
	}
	
	public Branch addBranch(Branch branch) {
		if(getBranch(branch.getName()) == null) {
			branches.add(branch);
		}
		return this;
	}
	
	public Branch getBranch(String childPrefix) {
		for(Branch childBranch : branches) {
			if(childBranch.getName().equals(childPrefix)) {
				return childBranch;
			}
		}
		return null;
	}
}
