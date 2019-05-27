import java.util.LinkedList;

public class Controller {
    /**
     *Instances of this class will handle different interfaces for the same Game instance.
     */

    private LinkedList<Player> _players;

    public LinkedList<Player> get_players() {
        return _players;
    }

    public Controller () {
        _players = new LinkedList<Player>();
    }

    public void set_players(LinkedList<Player> _players) {
        this._players = _players;
    }


//    public void addPlayer(String name) {
//        this.get_players().add(new Player(name));
//    }

    public void addPlayer(Player player) {
        this.get_players().add(player);
    }


    public void updateVotes() {
        for (int player_index = 0; player_index < this.get_players().size(); player_index++) {
            this.get_players().get(player_index).updateVote();
        }
    }

    public void updateVictims() {
        /**
         *
         */
        for (int player_index = 0; player_index < this.get_players().size(); player_index++) {
            this.get_players().get(player_index).updateVictim();
        }
    }

    public void renameAll() {
        for (int player_index = 0; player_index < this.get_players().size(); player_index++) {
            this.get_players().get(player_index).request_name();
        }
    }
}
