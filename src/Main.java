
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void pause() {
        try
        {
            TimeUnit.SECONDS.sleep(3);
        }
        catch(InterruptedException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Player [] myTeam = {
                new Player("Lionel Messi", 80, 90, 90, 40),
                new Player("Christiano Ronaldo", 90, 80, 85, 40),
                new Player("Ruben Dias", 60, 75, 60, 85),
                new Player("Kylian Mbappe", 85, 80, 90, 50),
                new Player("Kevin De Bruyne", 75, 95, 85, 55),
                new Player("Virgil Van Dijk", 50, 70, 55, 90)
        };

        Random rand = new Random();
        String action;
        boolean success;
        int myScore = 0;
        int oppScore = 0;
        int pl = rand.nextInt(myTeam.length);
        int newpl = 0;

        while(true) {
            while (true) {   // loop if you have possession
                System.out.println(myTeam[pl].getName() + " has the ball");
                System.out.println("Would you like to shoot(s), dribble(d), pass(p), or see stats(st)?");
                action = scan.next();
                if (action.equals("st")) {
                    myTeam[pl].getScores();
                    continue;
                }
                success = action.equals("s") ? myTeam[pl].shoot() : action.equals("p") ? myTeam[pl].pass() : action.equals("d") ? myTeam[pl].dribble() : false;
                if (success == true) {
                    if (action.equals("p")) {
                        newpl = rand.nextInt(myTeam.length);
                        while (newpl == pl)
                            newpl = rand.nextInt(myTeam.length);
                        pl = newpl;
                    }
                    else if (action.equals("s")) {
                        myScore++;
                        System.out.println("======== Score: " + myScore + " - " + oppScore + " ========");
                        System.out.println("The other team has the ball");
                        pause();
                        break;
                    }
                }
                else {
                    pause();
                    System.out.println(myTeam[pl].getName() + " turned over possession RIP" + "\n");
                    pause();
                    break;
                }
            }

            if (oppScore == 5 || myScore == 5) {
                if (myScore == 5)
                    System.out.println("YOU WON!!!" + "\n");
                else
                    System.out.println("You lost :(" + "\n");
                System.out.println("==== Highlights ====" + "\n" + "Goals  Tackles |");
                for (Player p : myTeam)
                    p.highlights();
                break;
            }

            while (true) {    // loop if other team has possession
                pl = rand.nextInt(myTeam.length);
                if (myTeam[pl].defend())
                    break;
                newpl = rand.nextInt(myTeam.length);
                while (newpl == pl)
                    newpl = rand.nextInt(myTeam.length);
                pl = newpl;
                if (myTeam[pl].defend())
                    break;
                System.out.println("\n" + "The other team scored :(" + "\n");
                oppScore++;
                pause();
                System.out.println("======== Score: " + myScore + " - " + oppScore + " ========");
                break;
            }
            if (oppScore == 5 || myScore == 5) {
                if (myScore == 5)
                    System.out.println("YOU WON!!!" + "\n");
                else
                    System.out.println("You lost :(" + "\n");
                System.out.println("==== Highlights ====" + "\n" + "Goals  Tackles |  Player");
                for (Player p : myTeam)
                    p.highlights();
                break;
            }
        }
    }
}
