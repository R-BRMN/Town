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

    /**
     * Acts out each player's professional choice.
     */
    public void actJobs() {
        for (int player_id = 0; player_id < this._players.length; player_id++) { //for each player
            Player victim = this._players[this._players[player_id].getVictimId()];
            this._players[player_id].actJob(victim);
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
        for (int player_id = 0; player_id < this._players.length; player_id++) { //for each player
            this._players[player_id].request_name();
        }
    }

    public void announceAllPlayers(String announcement) {
        for (int player_id = 0; player_id < this._players.length; player_id++) { //for each player
            this._players[player_id].announce(announcement);
        }
    }

    public String stringifyState() {
        String state = "";
        for (int player_id = 0; player_id < this._players.length; player_id++) { //for each player
            state+= "Name: "+this._players[player_id].get_name() + " ID: "+this._players[player_id].getId();
            state+="\n";
        }
        return state;
    }
}
