package Zohotask10_09;

import java.util.Scanner;
import java.util.Arrays;

public class CricketMain {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		try {
			boolean teamselection = true;
			boolean tossing = true;
			int[] allteams = { 1, 2, 3, 4, 5, 6 };
			int bat=2;
			String[] teamonecap = {"",""}, teamtwocap= {"",""};
			String[] toss = {"Heads", "Tails"};
			String[] choice = {"Bat","Bowl"};
			
			System.out.println("\nSelect Overs \n1.ten \n2.twenty");
			int value = sc.nextInt();
			System.out.println();
			int overs = GamePlay.overs(value);
			
			while (teamselection) {
				System.out.println(
						"Team1 choice : \n1.India \n2.Srilanka \n3.Bangaladesh \n4.Afganishtan \n5.Pakistan \n6.HongKong  ");
				int team1choice = sc.nextInt();
				System.out.println(
						"\nTeam2 choice : \n1.India \n2.Srilanka \n3.Bangaladesh \n4.Afganishtan \n5.Pakistan \n6.HongKong  ");
				int team2choice = sc.nextInt();
				
				System.out.println();
				if (team1choice != team2choice && Arrays.toString(allteams).contains("" + team1choice)
						&& Arrays.toString(allteams).contains("" + team2choice)) {
					teamonecap = GamePlay.teamSelection(team1choice);
					System.out.print(" VS ");
					teamtwocap = GamePlay.teamSelection(team2choice);
					System.out.println();
					teamselection = false;
				}
				else {
					System.out.println("You selected both teams are same "
							+ "or select wrong option. please choose correctly...\n");
				}

			}
			
			while(tossing) {
				String [] captains = {teamonecap[0],teamtwocap[0]};
				String cap = GamePlay.getRandom(captains);
				
				System.out.println("\n"+cap+" is ready to choose");
				
				if(cap.equals(teamonecap[0])) {
				
					System.out.println("\nToss Choice : \nH-Heads \nT-Tails");
					String selectedtoss = ""+sc.next().charAt(0);
					if (selectedtoss.equalsIgnoreCase("h")||selectedtoss.equalsIgnoreCase("t")) {
						String luck = GamePlay.getRandom(toss);
						if(selectedtoss.equals(""+luck.charAt(0))) {
							System.out.println("\n"+teamonecap[0]+" own the toss");
							System.out.println("\nSelect \n1.Bating \n2.Bowling");
							int playchoice=sc.nextInt();
							bat=playchoice;
							String play=GamePlay.playingChoice(playchoice);
							System.out.println("\n"+teamonecap[1]+" own the toss and selected to "+play+"\n");
							tossing=false;
							GamePlay.matchStart(overs, bat);
						}
						else {
							int[] batbowl= {1,2};
							int playchoice = GamePlay.getRandom(batbowl);
							if(playchoice==1) {
								bat=2;
							}
							else {
								bat=1;
							}
							String play=GamePlay.playingChoice(playchoice);
							System.out.println("\n"+teamtwocap[1]+" own the toss and selected to "+play+"\n");
							tossing=false;
							GamePlay.matchStart(overs, bat);
						}

					} else {
						System.out.println("\nYou entered Wrong Option. " + "Please Choose Correction...\n");
					}
				}
				else {
					String selectedtoss = GamePlay.getRandom(choice);
					String luck = GamePlay.getRandom(toss);
					if(selectedtoss.equals(""+luck.charAt(0))) {
						int[] batbowl= {1,2};
						int playchoice = GamePlay.getRandom(batbowl);
						if(playchoice==1) {
							bat=2;
						}
						else {
							bat=1;
						}
						String play=GamePlay.playingChoice(playchoice);
						System.out.println("\n"+teamtwocap[1]+" own the toss and selected to "+play);
						tossing=false;
						GamePlay.matchStart(overs, bat);
					}
					else {
						System.out.println("\n"+teamonecap[0]+" own the toss");
						System.out.println("\nSelect \n1.Bating \n2.Bowling");
						int playchoice=sc.nextInt();
						bat=playchoice;
						String play=GamePlay.playingChoice(playchoice);
						System.out.println("\n"+teamonecap[1]+" own the toss and selected to "+play);
						tossing=false;
						GamePlay.matchStart(overs, bat);
					}
				}	
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		finally {
			sc.close();
		}
	}

}
