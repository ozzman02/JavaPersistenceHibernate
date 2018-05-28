package client;

import examples.InheritanceMapping;
import examples.JoiningAssociations;
import examples.MergingDetachedObjects;
import examples.OneToMany;
import examples.QueryLanguage;
import examples.SelectsProblem;

public class Main {
	
	static void oneToManyExamples() {
		OneToMany.loadGuideAndStudents();
		OneToMany.createStudent();
		OneToMany.updateStudent();
		OneToMany.findGuide();
		OneToMany.findStudent();
		OneToMany.compareStudents();
	}
	
	static void queryLanguageExamples() {
		QueryLanguage.getGuides();
		QueryLanguage.getGuideNames();
		QueryLanguage.filterGuidesBySalary();
		QueryLanguage.guideReport();
		QueryLanguage.dynamicQuery();
		QueryLanguage.usingWildCard();
		QueryLanguage.nativeSQL();
		QueryLanguage.namedQuery();
		QueryLanguage.countFunction();
		QueryLanguage.maxFunction();
	}
	
	static void joinExamples() {
		JoiningAssociations.innerJoinStudents();
		JoiningAssociations.leftJoinStudents();
		JoiningAssociations.rightJoinStudents();
		JoiningAssociations.innerJoinGuides();
	}
	
	static void inheritanceMapping() {
		InheritanceMapping.singleTableStrategy();
		InheritanceMapping.getAnimalsSingleTableStrategy();
		InheritanceMapping.getDogsSingleTableStrategy();
		InheritanceMapping.joinedStrategy();
		InheritanceMapping.getAnimalsJoinedStrategy();
		InheritanceMapping.getDogsJoinedStrategy();
		InheritanceMapping.getCatsJoinedStrategy();
		InheritanceMapping.tablePerClassStrategy();
		InheritanceMapping.getAnimalsTablePerClassStrategy();
		InheritanceMapping.getDogsTablePerClassStrategy();
		InheritanceMapping.getCatsTablePerClassStrategy();
	}
	
	static void selectsProblem() {
		//SelectsProblem.loadGuideAndStudents();
		SelectsProblem.getAllStudents();
	}
	
	static void mergingDetachedObj() {
		//MergingDetachedObjects.mergeDetachedObj();
		MergingDetachedObjects.mergeDetachedObj2();
	}
	
	public static void main(String[] args) {
		mergingDetachedObj();
	}
	
}
