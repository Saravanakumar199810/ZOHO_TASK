package CricketScoreBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GamePlay extends Teams {
	
	public static String getRandom(String[] array) {
	    int rnd = new Random().nextInt(array.length);
	    return array[rnd];
	}
	
	public static int getRandom(int[] array) {
	    int rnd = new Random().nextInt(array.length);
	    return array[rnd];
	}
	
	static int first=0, value;
	public static String[] teamSelection(int num){
		if(first==1) {
			if(value<num && value==1) {
				num=num+value;
			}
			else if(value<num && value==2 ) {
				num=num+value-1;
			}
			else if(value<num && value==4 ) {
				num=num+1;
			}
			else if(value<num) {
				num=num+value-2;
			}
			else if(value==num) {
				num=num+1;
			}
		}
		first++;
		value=num;
		switch(num) {
		case 1:
			System.out.print("Team India");
			return teamIndiaeleven();
		case 2:
			System.out.print("Team Srilanka");
			return teamSrilankaeleven();
		case 3:
			System.out.print("Team Bangaladesh");
			return teamBangaladesheleven();
		case 4:
			System.out.print("Team Afganistan");
			return teamAfganistaneleven();
		case 5:
			System.out.print("Team Pakistan");
			return teamPakistaneleven();
		case 6:
			System.out.print("Team HongKong");
			return teamHongKongeleven();
		default:
			return null;
		}
	}
	
	public static String playingChoice(int num) {
		switch(num) {
		case 1:
			return "Batting First";
		case 2:
			return "Bowling First";
		default:
			String[] str = {"Batting First","Bowling First"};
			String st = getRandom(str);
			return st;
		}
	}
	
	public static int overs(int num) {
		switch(num) {
		case 1:
			int over1=10;
			return over1;
		case 2:
			int over2=20;
			return over2;
		default:
			int[] over = {10,20};
			int ovr = getRandom(over);
			return ovr;
		}
	}
	
	public static void matchStart(int over, int bat) {
		int balls = over*6;
		int innings1=1, innings2=2;
		HashMap<Integer, PojoClass> batting = new HashMap<Integer, PojoClass>();
		if(bat==1) {
			play(balls, innings1, teamoneplayerdetails, teamtwoplayerdetails);
			play(balls, innings2, teamtwoplayerdetails, teamoneplayerdetails);
		}
		else {
			play(balls, innings1, teamtwoplayerdetails, teamoneplayerdetails);
			play(balls, innings2, teamoneplayerdetails, teamtwoplayerdetails);
			batting=teamtwoplayerdetails;
			teamtwoplayerdetails=teamoneplayerdetails;
			teamoneplayerdetails=batting;
		}
		scoreBoard();
	}
	
	public static void play(int balls, int innings, HashMap<Integer,PojoClass> teambat, HashMap<Integer,PojoClass> teambowl) {
		
		ArrayList<String> sixballs = new ArrayList<String>();
		int ballcount=1, temp=2, batsman=2, totalscore=0, batscore=0, rungiven=0, overcount=1, wicketcount=0;
		int ballsfaced=0, fourscount=0, sixcount=0, wickettake=0;
		float strikerate=0f;
		String run;
		String[] bowlOne = {"0","1","2","3","4","6","W","wd","Nb"};
		String[] bowlTwo = {"0","1","2","3","4","6","wd","Nb"};
		
		int bowler = 7;
		int[] striker = {1,2};
		
		while(ballcount<=balls && wicketcount<11) {
			
			if(innings==2) {
				if(bothteamScores[0]<bothteamScores[2]) {
					if(teambat.get(striker[0]).getBallfaced()>0) {
						strikerate=((teambat.get(striker[0]).getBatscore())*100)/(teambat.get(striker[0]).getBallfaced());
						teambat.get(striker[0]).setStrikerate(strikerate);
					}
					if(teambat.get(striker[1]).getBallfaced()>0) {
						strikerate=((teambat.get(striker[1]).getBatscore())*100)/(teambat.get(striker[1]).getBallfaced());
						teambat.get(striker[1]).setStrikerate(strikerate);
					}
					//sixballs.add(teambowl.get(bowler).getName());
					teamtwoscores.put(overcount, sixballs);
					return;
				}
				bothteamScores[2]=totalscore;
				bothteamScores[3]=wicketcount;
			}
			if(sixballs.size()!=0) {
				if(sixballs.get(sixballs.size()-1).equals("Nb")){
					run=getRandom(bowlTwo);
				}
				else {
					run=getRandom(bowlOne);
				}
			}
			else {
				run=getRandom(bowlOne);
			}
			
			
			if("0".equals(run)) {
				sixballs.add(run);
				ballsfaced=teambat.get(striker[0]).getBallfaced()+1;
				teambat.get(striker[0]).setBallfaced(ballsfaced);
				if(ballcount%6==0) {
					temp=striker[0];
					striker[0]=striker[1];
					striker[1]=temp;
				}
			}
			else if("1".equals(run)) {
				sixballs.add(run);
				totalscore+=1;
				batscore=teambat.get(striker[0]).getBatscore()+1;
				teambat.get(striker[0]).setBatscore(batscore);
				ballsfaced=teambat.get(striker[0]).getBallfaced()+1;
				teambat.get(striker[0]).setBallfaced(ballsfaced);
				rungiven=teambowl.get(bowler).getBowlscore()+1;
				teambowl.get(bowler).setBowlscore(rungiven);
				if(ballcount%6!=0) {
					temp=striker[0];
					striker[0]=striker[1];
					striker[1]=temp;
				}
			}
			else if("2".equals(run)) {
				totalscore+=2;
				sixballs.add(run);
				batscore=teambat.get(striker[0]).getBatscore()+2;
				teambat.get(striker[0]).setBatscore(batscore);
				ballsfaced=teambat.get(striker[0]).getBallfaced()+1;
				teambat.get(striker[0]).setBallfaced(ballsfaced);
				rungiven=teambowl.get(bowler).getBowlscore()+2;
				teambowl.get(bowler).setBowlscore(rungiven);
				if(ballcount%6==0) {
					temp=striker[0];
					striker[0]=striker[1];
					striker[1]=temp;
				}
			}
			else if("3".equals(run)) {
				totalscore+=3;
				sixballs.add(run);
				batscore=teambat.get(striker[0]).getBatscore()+3;
				teambat.get(striker[0]).setBatscore(batscore);
				ballsfaced=teambat.get(striker[0]).getBallfaced()+1;
				teambat.get(striker[0]).setBallfaced(ballsfaced);
				rungiven=teambowl.get(bowler).getBowlscore()+3;
				teambowl.get(bowler).setBowlscore(rungiven);
				if(ballcount%6!=0) {
					temp=striker[0];
					striker[0]=striker[1];
					striker[1]=temp;
				}
			}
			else if("4".equals(run)) {
				totalscore+=4;
				sixballs.add(run);
				batscore=teambat.get(striker[0]).getBatscore()+4;
				teambat.get(striker[0]).setBatscore(batscore);
				ballsfaced=teambat.get(striker[0]).getBallfaced()+1;
				teambat.get(striker[0]).setBallfaced(ballsfaced);
				rungiven=teambowl.get(bowler).getBowlscore()+4;
				teambowl.get(bowler).setBowlscore(rungiven);
				fourscount=teambat.get(striker[0]).getFours()+1;
				teambat.get(striker[0]).setFours(fourscount);
				if(ballcount%6==0) {
					temp=striker[0];
					striker[0]=striker[1];
					striker[1]=temp;
				}
			}
			else if("6".equals(run)) {
				totalscore+=6;
				sixballs.add(run);
				batscore=teambat.get(striker[0]).getBatscore()+6;
				teambat.get(striker[0]).setBatscore(batscore);
				ballsfaced=teambat.get(striker[0]).getBallfaced()+1;
				teambat.get(striker[0]).setBallfaced(ballsfaced);
				rungiven=teambowl.get(bowler).getBowlscore()+6;
				teambowl.get(bowler).setBowlscore(rungiven);
				sixcount=teambat.get(striker[0]).getSixes()+1;
				teambat.get(striker[0]).setSixes(sixcount);
				if(ballcount%6==0) {
					temp=striker[0];
					striker[0]=striker[1];
					striker[1]=temp;
				}
			}
			else if("wd".equals(run)) {
				totalscore+=1;
				sixballs.add(run);
				rungiven=teambowl.get(bowler).getBowlscore()+1;
				teambowl.get(bowler).setBowlscore(rungiven);
			}
			else if("Nb".equals(run)) {
				totalscore+=1;
				sixballs.add(run);
				rungiven=teambowl.get(bowler).getBowlscore()+1;
				teambowl.get(bowler).setBowlscore(rungiven);
			}
			else if("W".equals(run)) {
				sixballs.add(run);
				ballsfaced=teambat.get(striker[0]).getBallfaced()+1;
				teambat.get(striker[0]).setBallfaced(ballsfaced);
				strikerate=(((float)teambat.get(striker[0]).getBatscore())*100)/((float)teambat.get(striker[0]).getBallfaced());
				teambat.get(striker[0]).setStrikerate(strikerate);
				wickettake=teambowl.get(bowler).getWickettaken()+1;
				teambowl.get(bowler).setWickettaken(wickettake);
				batsman+=1;
				striker[0]=batsman;
				wicketcount+=1;
				if(innings==1 && (ballcount==balls || wicketcount<11)){
					bothteamScores[0]=totalscore;
					bothteamScores[1]=wicketcount;
				}
				if(innings==2 && (ballcount==balls || wicketcount<11)){
					bothteamScores[2]=totalscore;
					bothteamScores[3]=wicketcount;
				}
				if(wicketcount==10) {
					//sixballs.add(teambowl.get(bowler).getName());
					if(innings==1) {
						teamonescores.put(overcount, sixballs);
					}
					else {
						teamtwoscores.put(overcount, sixballs);
					}
					return;
				}
			}
			if(!"Nb".equals(run) && !"wd".equals(run)) {
				if(ballcount%6==0) {
					//sixballs.add(teambowl.get(bowler).getName());
					if(innings==1) {
						teamonescores.put(overcount, sixballs);
					}
					else {
						teamtwoscores.put(overcount, sixballs);
					}
					overcount++;
					sixballs = new ArrayList<String>();
					bowler +=1;
					if(bowler==12) {
						bowler=7;
					}
				}
			}
			
			if(innings==1 && (ballcount==balls || wicketcount<11)){
				bothteamScores[0]=totalscore;
				bothteamScores[1]=wicketcount;
			}
			
			if(ballcount==balls) {
				//sixballs.add(teambowl.get(bowler).getName());
				if(teambat.get(striker[0]).getBallfaced()>0) {
					strikerate=(((float)teambat.get(striker[0]).getBatscore())*100)/((float)teambat.get(striker[0]).getBallfaced());
					teambat.get(striker[0]).setStrikerate(strikerate);
				}
				if(teambat.get(striker[1]).getBallfaced()>0) {
					strikerate=((teambat.get(striker[1]).getBatscore())*100)/(teambat.get(striker[1]).getBallfaced());
					teambat.get(striker[1]).setStrikerate(strikerate);
				}
			}
			if(!"Nb".equals(run) && !"wd".equals(run)) {
				ballcount++;
			}
		}
	}
	
}
