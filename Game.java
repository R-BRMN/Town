import java.util.Random;
import java.util.LinkedList;

/**
 * Game instances handle the progress of games.
 */
public class Game {

    private int step;
    private Controller _controller;


    /**
     * Instance of Game.
     * @param controller : The game's controller, used to micro-manage players.
     */
    public Game(Controller controller) {
        this.step = 0;
        this._controller = controller;
    }


//    /**
//     * Assigns each Player a job and notifies them of it, assigns "CITIZEN" to jobless players.
//     * Known job strings are: KILLER, DOCTOR, DETECTIVE, WHORE, SHAMAN.
//     * @param jobs : String [] containing names of jobs.
//     */
//    public void assignJobs(String [] jobs) {
//        String assigned = "";
//        for (int j=0; j<jobs.length; j++) { //For each job in the jobs list
//            int r  = -1;
//            while (assigned.contains((String.valueOf(r))) || r == -1) { //if player at r has no job, or if first try.
//                r = (int)(Math.random() * this._controller.get_players().length); //Choose a random player
//            }
//            assigned += String.valueOf(r); //Make note of assigning a job to player at r.
//            this._controller.get_players()[r].assignJob(jobs[j]);
//        }
//        for (int i=0; i<this._controller.get_players().length; i++) { //All who have no job are citizens.
//            if (this._controller.get_players()[i].get_job() == null) {
//                this._controller.get_players()[i].assignJob("CITIZEN");
//            }
//        }
//    }


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
                this._controller.actJobs();
                System.out.println(this._controller.stringifyState());
                break;
            case 1:
                //Town is notified about what happened during the night.
                //Town is voting about who to kill.
                this._controller.announceAllPlayers("Sup");
                System.out.println(this._controller.stringifyState());
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


    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public Controller getController() {
        return _controller;
    }

    public void set_controller(Controller _controller) {
        this._controller = _controller;
    }

}
