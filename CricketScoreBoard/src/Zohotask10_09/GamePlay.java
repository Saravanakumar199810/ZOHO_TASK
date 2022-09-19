package Zohotask10_09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GamePlay extends Teams {
	
	public static String getRandom(String[] array) {
	    int rnd = new Random().nextInt(array.length);
	    return array[rnd];
	}
	static int[][] bowlers = {{7,0},{8,0},{9,0},{10,0},{11,0}};
	static int previous=-1;
	public static int getRandom(int[][] array, int balls) {
	    int rnd = new Random().nextInt(array.length);
	    int count;
	    if(balls==60) {
	    	if(previous!=rnd) {
	    		if(array.length==1) {
	    			return array[rnd][0];
	    		}
	    		if(array[rnd][1]==2 ) {
		    		int[][] remainingBowler = new int[bowlers.length-1][2];
		    		int j=0;
		    		for(int i=0;i<bowlers.length;i++) {
		    			if(i!=rnd) {
		    				remainingBowler[j][0]=bowlers[i][0];
		    				remainingBowler[j][1]=bowlers[i][1];
		    				j++;
		    			}
		    		}
		    		bowlers=remainingBowler;
		    		previous=-1;
		    		getRandom(bowlers, 60);
		    	}
	    		else {
	    			previous=rnd;
		    		count=array[rnd][1]+1;
		    		array[rnd][1]=count;
		    		return array[rnd][0];
	    		}
	    	}
	    	else {
	    		getRandom(bowlers, 60);
	    	}
	    }
	    return array[rnd][0];
	}
	
	public static int getRandom(int[] array) {
	    int rnd = new Random().nextInt(array.length);
	    return array[rnd];
	}
	
	public static String[] teamSelection(int num){
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
			return teamIndiaeleven();
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
		int[][] bowlers2 = {{7,0},{8,0},{9,0},{10,0},{11,0}};
		if(bat==1) {
			play(balls, innings1, teamoneplayerdetails, teamtwoplayerdetails);
			bowlers = bowlers2;
			play(balls, innings2, teamtwoplayerdetails, teamoneplayerdetails);
		}
		else {
			play(balls, innings1, teamtwoplayerdetails, teamoneplayerdetails);
			bowlers=bowlers2;
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
		
		int bowler = getRandom(bowlers, balls);
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
				strikerate=(((float)teambat.get(striker[0]).getBatscore())*100f)/((float)teambat.get(striker[0]).getBallfaced());
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
					if(innings==1) {
						teamonescores.put(overcount, sixballs);
					}
					else {
						teamtwoscores.put(overcount, sixballs);
					}
					overcount++;
					sixballs = new ArrayList<String>();
					bowler = getRandom(bowlers, balls);
				}
			}
			
			if(innings==1 && (ballcount==balls || wicketcount<11)){
				bothteamScores[0]=totalscore;
				bothteamScores[1]=wicketcount;
			}
			
			if(ballcount==balls) {
				if(teambat.get(striker[0]).getBallfaced()>0) {
					strikerate=(((float)teambat.get(striker[0]).getBatscore())*100F)/((float)teambat.get(striker[0]).getBallfaced());
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
