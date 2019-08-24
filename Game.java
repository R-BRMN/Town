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
        System.out.println("cool");
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
                System.out.println("Step 0");
                this._controller.updateVictims();
                this._controller.actJobs();
                this._controller.resetMortality();
                //clean up dead
                break;
            case 1:
                System.out.println("Step 1");
                this._controller.announceAllAnnouncements(); //Town is notified about what happened during the night.
                this._controller.kill(this._controller.haveVote());

                //Town is voting about who to kill.
                //First vote, everyone chooses who they want to kill. They get 5 minutes or they lose their vote.
                //If there is a clear winner, 
                break;
            case 2:
                //Game won or continue.
                if (!this._controller.isGameOver()) {
                    step = -1; //because step gets ++ at end of function
                }
                break;
            case 3:
                //game won
                this._controller.announceScore();
                return;
        }
        step ++;
    }

    public Controller getController() {
        return _controller;
    }
}
