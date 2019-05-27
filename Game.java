import java.util.Random;
import java.util.LinkedList;

/**
 * Game instances handle the progress of games.
 */
public class Game {

    public Player[] get_players() {
        return _players;
    }

    public void set_players(Player[] _players) {
        this._players = _players;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public Controller get_controller() {
        return _controller;
    }

    public void set_controller(Controller _controller) {
        this._controller = _controller;
    }

    private Player[] _players;
    private int step;
    private Controller _controller;

    /**
     * Instance of Game.
     * @param controller : The game's interface, for running a game.
     */
    public Game(Controller controller) {
        this._players = null;
        this.step = 0;
        this._controller = controller;
    }


    public void actJobs (Player [] players) {
        for (int i=0; i<players.length; i++) {
            players [i].actJobOnVote();
        }
        return;
    }

    public void assignJobs(String [] jobs) {
        String assigned = "";
        for (int j=0; j<jobs.length; j++) {
            int r  = -1;
            while (assigned.contains((String.valueOf(r))) || r == -1) {
                r = (int)(Math.random() * this._players.length);
            }
            this._players[r].assignJob(jobs[j]);
            assigned += String.valueOf(r);
        }
        for (int i=0; i<this._players.length; i++) {
            if (this._players[i].get_job() == null) {
                this._players[i].assignJob("CITIZEN");
            }
        }
        return;
    }

    /**
     * Plays a full round, according to these steps:
     * Step 0 : It is night time, People do their job.
     * Step 1 : Town is notified about what happened during the night, votes on who to kill.
     * Step 2 : The game is either won, or continues to step 0 in a loop.
     * @return
     */
    public void playStep() {
        switch(step) {
            case 0:
                //Game starts at night, with the killers choosing randomly.
                //Each person does their stuff
                this._controller.updateVictims();
                this.actJobs(this._players);
                System.out.println("nice");
                break;
            case 1:
                //Town is notified about what happened during the night.
                //Town is voting about who to kill.
                break;
            case 2:
                //Game won or continue.
                step = 0;
                break;
            case 3:
                //game won
                return;
        }
        step ++;
    }
}