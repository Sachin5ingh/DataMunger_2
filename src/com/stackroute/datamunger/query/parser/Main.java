package com.stackroute.datamunger.query.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	
	public static List<Restriction> extractConditions(String queryString) {
		List<Restriction> conditions = null;
		// queryString = queryString.toLowerCase();
		String[] whereQuery;
		// List<String> Cond = new ArrayList<String>();
		String tempString;
		String[] conditionQuery;
		String[] getCondition = null;
		if (queryString.contains("where")) {
			conditions = new ArrayList<Restriction>();
			whereQuery = queryString.trim().split("where ");
			if (whereQuery[1].contains("group by")) {
				conditionQuery = whereQuery[1].trim().split("group by");
				tempString = conditionQuery[0];
			} else if (whereQuery[1].contains("order by")) {
				conditionQuery = whereQuery[1].trim().split("order by");
				tempString = conditionQuery[0];
			} else {
				tempString = whereQuery[1];
			}
			getCondition = tempString.trim().split(" and | or ");
			// for (String s : getCondition) {
			// System.out.println(s.trim());
			// }
			String[] condSplit = null;
			if (getCondition != null) {
				for (int i = 0; i < getCondition.length; i++) {
					if (getCondition[i].contains("=")) {
						condSplit = getCondition[i].trim().split("\\W+");
						conditions.add(new Restriction(condSplit[0], condSplit[1], "="));
					} else if (getCondition[i].contains(">")) {
						condSplit = getCondition[i].trim().split("\\W+");
						conditions.add(new Restriction(condSplit[0], condSplit[1], ">"));
					} else if (getCondition[i].contains("<")) {
						condSplit = getCondition[i].trim().split("\\W+");
						conditions.add(new Restriction(condSplit[0], condSplit[1], "<"));
					}

				}
			}
			// Conditions.add(new Restriction(getCondition[0].substring(0, 6),
			// getCondition[0].substring(9, 12), getCondition[0].substring(7, 8)));

		}
		return conditions;
	}

	public static void main(String[] args) {
		
		List<Restriction> conditions = null;
		
		System.out.println(extractConditions("select city,winner,team1,team2,player_of_match from data/ipl.csv where season >= 2008 or toss_decision != bat"));

	}

}
