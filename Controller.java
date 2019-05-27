import java.util.LinkedList;

public class Controller {
    /**
     *Instances of this class are responsible for acting out the Game's instructions, facing Players.
     */

    private Player [] _players;

    public Player [] get_players() {
        return _players;
    }

    public Controller (Player [] player_list) {
        _players = player_list;
    }

    public void set_players(Player [] players) {
        this._players = players;
    }

    public void updateVotes() {
        /**
         * Updates vote of each player.
         */
        for (int player_index = 0; player_index < this._players.length; player_index++) {
            this._players[player_index].updateVote();
        }
    }

    public void updateVictims() {
        /**
         * Updates victim for each player.
         */
        for (int player_index = 0; player_index < this._players.length; player_index++) {
            this._players[player_index].updateVictim();
        }
    }

    public void renameAll() {
        for (int player_index = 0; player_index < this._players.length; player_index++) {
            this._players[player_index].request_name();
        }
    }
}
