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

    public Controller getController() {
        return _controller;
    }
}
