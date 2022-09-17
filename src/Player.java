import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Player {
    private String playerName;
    private int shootScore;
    private int passScore;
    private int dribbleScore;
    private int defendScore;
    private int goals;
    private int tackles;

    public Player(String plName, int shootScore, int passScore, int dribbleScore, int defScore) {
        this.playerName = plName;
        this.shootScore = shootScore;
        this.passScore = passScore;
        this.dribbleScore = dribbleScore;
        this.defendScore = defScore;
    }

    public String getName() {
        return this.playerName;
    }

    public void getScores() {
        System.out.println("===============");
        System.out.println("| Shot: " + shootScore + "    |");
        System.out.println("| Pass: " + passScore + "    |");
        System.out.println("| Dribble: " + dribbleScore + " |");
        System.out.println("===============");
    }

    public boolean shoot() {
        Random rand = new Random();
        int shotQual = rand.nextInt(100);
//	    System.out.println(shotQual);
        boolean shotSuccess = this.shootScore + shotQual > 150 ? true : false;
        if (shotSuccess) {
            System.out.println("GOAAAAAAAAAAAAAAAAAAAL!!!");
            goals++;
        }
        return shotSuccess;
    }

    public boolean pass() {
        Random rand = new Random();
        int passQual = rand.nextInt(100);
//	    System.out.println(passQual);
        boolean passSuccess = this.passScore + passQual > 90 ? true : false;
        if (passSuccess)
            System.out.println("Good pass");
        return passSuccess;
    }

    public boolean dribble() {
        Random rand = new Random();
        int dribQual = rand.nextInt(100);
//	    System.out.println(dribQual);
        boolean dribSuccess = this.dribbleScore + dribQual > 110 ? true : false;
        if (dribSuccess)
            System.out.println("Good dribble");
        shootScore += 5;
        passScore += 5;
        return dribSuccess;
    }

    public boolean defend() {
        Random rand = new Random();
        int defQual = rand.nextInt(100);
//	    System.out.println(dribQual);
        boolean defSuccess = this.defendScore + defQual > 110 ? true : false;
        if (defSuccess) {
            System.out.println("Great tackle by " + playerName);
            tackles++;
        }
        else
            System.out.println(playerName + " failed his tackle.");
        pause();
        return defSuccess;
    }

    public void highlights() {
        System.out.println("  " + goals + "       " + tackles + "    |  " + playerName );
    }

    public static void pause() {
        try
        {
            TimeUnit.SECONDS.sleep(2);
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
    }
}

