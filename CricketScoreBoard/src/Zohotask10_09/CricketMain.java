package CricketScoreBoard;

import java.util.Scanner;
import java.util.Arrays;

public class CricketMain {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		try {
			boolean teamselection = true;
			boolean tossing = true;
			boolean overselection = true;
			
			int[] choice1 = { 1, 2, 3, 4, 5, 6 };
			int[] choice2 = { 1, 2, 3, 4, 5 };
			int bat=2, overs=0;
			String[] teamonecap = {"",""}, teamtwocap= {"",""};
			String[] toss = {"Heads", "Tails"};
			String[] choice = {"Bat","Bowl"};
			
			while(overselection) {
				System.out.println("\nSelect Overs \n1.ten \n2.twenty \n3.YourChoice");
				int value = sc.nextInt();
				if(value==3) {
					System.out.println("Enter the number of overs : ");
					overs =sc.nextInt();
					System.out.println();
					overselection=false;
				}
				else if(value==1 || value==2) {
					System.out.println();
					overs = GamePlay.overs(value);
					overselection=false;
				}
				else {
					System.out.println("\nPlease select correct option\n");
				}
			}
			
			while (teamselection) {
				Teams.teamChoice(-1);
				int team1choice = sc.nextInt();
				System.out.println();
				Teams.teamChoice(team1choice);
				int team2choice = sc.nextInt();
				
				System.out.println();
				if (Arrays.toString(choice1).contains("" + team1choice)
						&& Arrays.toString(choice2).contains("" + team2choice)) {
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
