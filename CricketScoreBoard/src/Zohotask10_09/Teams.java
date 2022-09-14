package Zohotask10_09;

import java.util.ArrayList;
import java.util.HashMap;

public class Teams {
	
	static int[] bothteamScores = {0,0,0,0};
	static HashMap<Integer,ArrayList<String>> teamonescores = new HashMap<Integer,ArrayList<String>>();
	static HashMap<Integer,ArrayList<String>> teamtwoscores = new HashMap<Integer,ArrayList<String>>();
	static HashMap<Integer,PojoClass> teamoneplayerdetails = new HashMap<Integer, PojoClass>();
	static HashMap<Integer,PojoClass> teamtwoplayerdetails = new HashMap<Integer, PojoClass>();
	
	public static String[] teamIndiaeleven() {
		String[] indeleven = {"Rohit","Kohli","Rahul","Surya","Pant","Hardik",
				"Jadeja","Bhuvi","Shami","Bumrah","Chahal"};
		String[] teamcap = {"Rohit","India"};
		team(indeleven, teamcap);
		return teamcap;
	}
	public static String[] teamSrilankaeleven() {
		String[] sleleven = {"P.Nisanka","K.Mendis","C.Asalanka","D.Gunathilaka","B.Rajapaksa","D.Sanaka",
				"W.Hasaranga","C.Karunaratne","M.Theeksana","A.Fernando","D.Madushana"};
		String[] teamcap = {"D.Sanaka","Srilanka"};
		team(sleleven, teamcap);
		return teamcap;
	}
	public static String[] teamBangaladesheleven() {
		String[] baneleven = {"S.Rahman","M.Hossain","Shakib","M.Rahim","Mahmudullah","M.Hassan",
				"A.Hossain","M.Hasan","T.Ahmad","E.Hossain","M.Rahman"};
		String[] teamcap = {"Shakib","Bangaladesh"};
		team(baneleven, teamcap);
		return teamcap;
	}
	public static String[] teamAfganistaneleven() {
		String[] afgeleven = {"H.Zazai","R.Gurbaz","I.Zardan","N.Zadran","M.Nabi","K.Janat",
				"Rashid","A.Omarzai","M.U.Rahman","F.Malik","F.Farooqi"};
		String[] teamcap = {"M.Nabi","Afganistan"};
		team(afgeleven, teamcap);
		return teamcap;
	}
	public static String[] teamPakistaneleven() {
		String[] pakeleven = {"M.Rizwan","B.Azam","F.Zaman","I.Ahmad","K.Shah","A.Ali",
				"M.Nawaz","S.Khan","H.Rauf","M.Hasnain","N.Shah"};
		String[] teamcap = {"B.Azam","Pakistan"};
		team(pakeleven, teamcap);
		return teamcap;
	}
	public static String[] teamHongKongeleven() {
		String[] hkeleven = {"N.Khan","Y.Murtaza","B.Hayat","K.Shah","A.Khan","Z.Ali",
				"S.McKechnie","H.Arshad","E.Khan","A.Shukla","M.Ghazanfar"};
		String[] teamcap = {"N.Khan","HongKong"};
		team(hkeleven, teamcap);
		return teamcap;
	}

	
	static int teamcount=0;
	public static void team(String[] playingeleven, String[] teamcap) {
		PojoClass pc = new PojoClass();
		for(int i=0;i<playingeleven.length;i++) {
			pc = new PojoClass();
			pc.setId(i+1);
			pc.setName(playingeleven[i]);
			pc.setTeam(teamcap[1]);
			if(teamcap[0].equals(playingeleven[i])) {
				pc.setCaptain("C");
				pc.setTeam(teamcap[1]);
			}
			pc.setBallfaced(0);
			pc.setBatscore(0);
			pc.setBowlscore(0);
			pc.setEconomy(0);
			pc.setFours(0);
			pc.setOversbowled(0);
			pc.setSixes(0);
			pc.setStrikerate(0);
			pc.setWickettaken(0);
			if(teamcount%2==0) {
				teamoneplayerdetails.put(pc.getId(), pc);
			}
			else {
				teamtwoplayerdetails.put(pc.getId(), pc);
			}
		}
		
		teamcount++;
		if(teamcount==2) {
			teamcount=0;
		}
	}
	public static void scoreBoard() {
		
		System.out.println("\n***********************SCORE BOARD***********************\n");
		
		System.out.format("%-20s|%-10s|%-8s|%-6s|%-8s|\n", "PLAYER","R(B)","4s","6s","SR");
		for(int i = 0; i<teamoneplayerdetails.size();i++) {
			System.out.println();
			System.out.format("%-20s|%-10s|%-8s|%-6s|%-8s|\n",teamoneplayerdetails.get(i+1).getName(),
					teamoneplayerdetails.get(i+1).getBatscore()+"("+teamoneplayerdetails.get(i+1).getBallfaced()+")",
					teamoneplayerdetails.get(i+1).getFours(),
					teamoneplayerdetails.get(i+1).getSixes(),
					teamoneplayerdetails.get(i+1).getStrikerate());
		}
		System.out.println("\n");
		for(int i = 0; i<teamonescores.size();i++) {
			System.out.println("over "+(i+1)+" "+teamonescores.get(i+1));
		}
		System.out.println("\n"+teamoneplayerdetails.get(1).getTeam()+"  "+bothteamScores[0]+" - "+bothteamScores[1]);
		
		
		System.out.println("\n");
		System.out.format("%-20s|%-10s|%-8s|%-6s|%-8s|\n", "PLAYER","R(B)","4s","6s","SR");
		for(int i = 0; i<teamtwoplayerdetails.size();i++) {
			System.out.println();
			System.out.format("%-20s|%-10s|%-8s|%-6s|%-8s|\n",teamtwoplayerdetails.get(i+1).getName(), 
					teamtwoplayerdetails.get(i+1).getBatscore()+"("+teamtwoplayerdetails.get(i+1).getBallfaced()+")",
					teamtwoplayerdetails.get(i+1).getFours(),
					teamtwoplayerdetails.get(i+1).getSixes(),
					teamtwoplayerdetails.get(i+1).getStrikerate());
		}
		System.out.println("\n");
		for(int i = 0; i<teamtwoscores.size();i++) {
			System.out.println("over "+(i+1)+" "+teamtwoscores.get(i+1));
		}
		System.out.println("\n"+teamtwoplayerdetails.get(1).getTeam()+"  "+bothteamScores[2]+" - "+bothteamScores[3]
				+"                     "+teamoneplayerdetails.get(1).getTeam()+"  "+bothteamScores[0]+" - "+bothteamScores[1]);
		

		System.out.println("\n");
		if(bothteamScores[0]>bothteamScores[2]) {
			System.out.println("****************** "+teamoneplayerdetails.get(1).getTeam()+" Won the Match ******************");
		}
		else {
			System.out.println("****************** "+teamtwoplayerdetails.get(1).getTeam()+" Won the Match ******************");
		}
	}
	
}
