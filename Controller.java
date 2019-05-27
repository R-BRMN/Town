import java.util.LinkedList;

public class Controller {
    /**
     *Instances of this class are responsible for acting out the Game's instructions, facing Players.
     */

    private Player [] _players;
    private LinkedList <Player> _killers;
    private LinkedList <Player> _doctors;
    private LinkedList <Player> _detectives;
    private LinkedList <Player> _whores;

    public Player [] get_players() {
        return _players;
    }

    public Controller (Player [] player_list) {
        _players = player_list;
        _killers = new LinkedList<>();
        _doctors = new LinkedList<>();
        _detectives = new LinkedList<>();
        _whores = new LinkedList<>();

    }

    public void set_players(Player [] players) {
        this._players = players;
    }

    /**
     * Assigns each Player a job and notifies them of it, assigns "CITIZEN" to jobless players.
     * Known job strings are: KILLER, DOCTOR, DETECTIVE, WHORE, SHAMAN.
     * @param jobs : String [] containing names of jobs.
     */
    public void assignJobs(String [] jobs) {
        String assigned = "";
        for (int j=0; j<jobs.length; j++) { //For each job in the jobs list
            int r  = -1;
            while (assigned.contains((String.valueOf(r))) || r == -1) { //if player at r has no job, or if first try.
                r = (int)(Math.random() * this._players.length); //Choose a random player
            }
            assigned += String.valueOf(r); //Make note of assigning a job to player at r.
            this._players[r].assignJob(jobs[j]);
            if (jobs[j] == "KILLER") {
                this._killers.add(this._players[r]);
            }
            else if (jobs[j] == "DOCTOR") {
                this._doctors.add(this._players[r]);
            }
            else if (jobs[j] == "DETECTIVE") {
                this._detectives.add(this._players[r]);
            }
            else if (jobs[j] == "WHORE") {
                this._whores.add(this._players[r]);
            }
        }
        for (int i=0; i<this.get_players().length; i++) { //All who have no job are citizens.
            if (this.get_players()[i].get_job() == null) {
                this.get_players()[i].assignJob("CITIZEN");
            }
        }
    }

    public void resetPlayers() {
        for (int player_id = 0; player_id < this._players.length; player_id++) { //for each player
            this._players[player_id].setImmortal(false);
            this._players[player_id].setMuted(false);
            this._players[player_id].setAlive(true);
            this._players[player_id].set_job(null);
            this._players[player_id].set_victim_id(-1);
            this._players[player_id].set_vote_id(-1);
        }
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
        System.out.println(this._killers.size());
        System.out.println(this._killers);
        //Doctor goes first:
        for (int count = 0; count < this._doctors.size(); count++) {
            this.setImmortal(this._players[this._doctors.get(count).getVictimId()]);
        }
        //Whores do their thing:
        for (int count = 0; count < this._whores.size(); count++) {
            this.mute(this._players[this._whores.get(count).getVictimId()]);
        }
        //Detectives do their thing:
        for (int count = 0; count < this._detectives.size(); count++) {
            this.investigate(this._players[this._detectives.get(count).getVictimId()]);
        }
        //Killers do their thing:
        int murdered_id = this._killers.get(0).getVictimId();
        for (int count = 1; count < this._killers.size(); count++) {
            //Find the agreed upon murdered player.
            if (murdered_id != this._killers.get(count).getVictimId()) {
                return;
            }
        }
        this.kill(this._players[murdered_id]);
    }

    public void investigate(Player player) {
        System.out.println("investigated: "+player.getId());
        return;
    }

    public void mute(Player player) { //can whores mute immortals?
        System.out.println("muted: "+player.getId());
        player.setMuted(true);
        return;
    }

    public void setImmortal(Player player) {
        System.out.println("cured: "+player.getId());
        player.setImmortal(true);
    }

    public void kill (Player player) {
        if (!player.isImmortal()) { //If wasn't worked on by a doctor.
            player.setAlive(false); //Kill player
            System.out.println("killed: "+player.getId());
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


    /**
     * Ask every player to name themselves.
     */
    public void renameAll() {
        for (int player_id = 0; player_id < this._players.length; player_id++) { //for each player
            this._players[player_id].request_name();
        }
    }


    /**
     * Send the same string to every player.
     * @param announcement : String the message to be sent.
     */
    public void announceAllPlayers(String announcement) {
        for (int player_id = 0; player_id < this._players.length; player_id++) { //for each player
            this._players[player_id].announce(announcement);
        }
    }

    public String stringifyState() {
        String state = "";
        for (int player_id = 0; player_id < this._players.length; player_id++) { //for each player
            state += this._players[player_id].get_name() + " ID: "+this._players[player_id].getId();
            state += "\n\t ALIVE: "+this._players[player_id].isAlive();
            state += "\n\t VICTIM_ID: "+this._players[player_id].getVictimId();
            state += "\n\t VOTE_ID: "+this._players[player_id].getVoteId();

            state+="\n";
        }
        return state;
    }
}
