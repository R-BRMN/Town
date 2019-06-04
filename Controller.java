import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;
import java.util.Collections;

public class Controller {
    /**
     *Instances of this class are responsible for acting out the Game's instructions, facing Players.
     */

    private Player [] _players;
    private LinkedList <Player> _alive;
    private LinkedList <Player> _killers;
    private LinkedList <Player> _doctors;
    private LinkedList <Player> _detectives;
    private LinkedList <Player> _whores;
    private Queue <String> _announcements;
    private boolean announce_interrogation_publicly = true;
    private boolean zombie_detectives = true;

    public Controller (Player [] player_list) {
        _players = player_list;
    }


    public Player [] get_players() {
        return _players;
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

    /**
     * Resets the same players for another game.
     */
    public void resetPlayers() {
        this._killers = new LinkedList<>();
        this._doctors = new LinkedList<>();
        this._detectives = new LinkedList<>();
        this._whores = new LinkedList<>();
        this._announcements = new LinkedList<>();
        this._alive = new LinkedList<>();
        for (int player_id = 0; player_id < this._players.length; player_id++) { //for each player
            this._alive.add(this._players[player_id]);
            this._players[player_id].setImmortal(false);
            this._players[player_id].setMuted(false);
            this._players[player_id].set_job(null);
            this._players[player_id].set_victim_id(-1);
            this._players[player_id].set_vote_id(-1);
        }
    }


    /**
     * After the night concludes, the doctor's effect dissolves.
     */
    public void resetMortality() {
        for (int player_id = 0; player_id < this._alive.size(); player_id++) { //for each player alive
            this._players[this._alive.get(player_id).getId()].setImmortal(false);
        }
    }

    /**
     * Acts out each profession.
     */
    public void actJobs() {
        //Doctor goes first:
        if (this._doctors.size()>0) {
            this.actDoctors();
        }
        //Killers do their thing:
        if (this._killers.size() > 0) {
            actKillers();
        }
        //Whores do their thing:
        if (this._whores.size()>0) {
            this.actWhores();
        }
        //Detectives do their thing:
        if (this._detectives.size() > 0) {
            actDetectives();
        }
        /**
         * Since more bamboozles means more fun, the game host will declare false findings as if the detective is still
         * alive.
         * This should be toggled in the 'advanced options'.
         * Zombie detectives have a 50/50 chance between yelling killer or innocent.
         */
        else if (zombie_detectives && announce_interrogation_publicly) {
            if ((int)(Math.random()*2) == 1) {
                this.addAnouncement("Detectives found: A KILLER!");
            }
            else {
                this.addAnouncement("Detectives found: Innocent!");

            }

        }
    }


    /**
     * Kills the chosen player if the the decision is unanimous.
     */
    public void actKillers() {
        int murdered_id = this._killers.get(0).getVictimId();
        for (int count = 0; count < this._killers.size(); count++) {
            //Find the agreed upon murdered player.
            if (murdered_id != this._killers.get(count).getVictimId()) {
                return;
            }
        }
        this.kill(this._players[murdered_id]);
        this.addAnouncement("Player ID: "+murdered_id+" has been killed!");
    }

    public void actDetectives() {
        for (int count = 0; count < this._detectives.size(); count++) {
            this.investigate(this._players[this._detectives.get(count).getVictimId()]);
        }
    }

    public void actWhores() {
        for (int count = 0; count < this._whores.size(); count++) {
            this.mute(this._players[this._whores.get(count).getVictimId()]);
        }
    }

    public void actDoctors() {
        for (int count = 0; count < this._doctors.size(); count++) {
            this.setImmortal(this._players[this._doctors.get(count).getVictimId()]);
        }
    }

    public void investigate(Player player) {
        if (player.get_job().equals("KILLER")) {
            this.addAnouncement("Detectives found: A KILLER!");
            return;
        }
        this.addAnouncement("Detectives investigated an innocent person");
    }

    public void mute(Player player) { //can whores mute immortals?
        this.addAnouncement("Player ID: "+player.getId()+" has been muted");
        player.setMuted(true);
    }

    public void setImmortal(Player player) {
        player.setImmortal(true);
    }

    public void kill (Player player) {
        if (!player.isImmortal()) { //If wasn't worked on by a doctor.
            this.removePlayer(player); //Kill player
        }
    }

    private void removePlayer(Player player) {
        int player_id = player.getId();
        String player_job = player.get_job();
        this.removeIdFromLinkedList(player_id, this._alive);
        if (player_job == "KILLER") {
            this.removeIdFromLinkedList(player_id, this._killers);
        }
        else if (player_job == "DOCTOR") {
            this.removeIdFromLinkedList(player_id, this._doctors);
        }
        else if (player_job == "WHORE") {
            this.removeIdFromLinkedList(player_id, this._whores);
        }
        else if (player_job == "DETECTIVE") {
            this.removeIdFromLinkedList(player_id, this._detectives);
        }
    }

    private void removeIdFromLinkedList(int id, LinkedList<Player> linked_list) {
        for (int i=0; i<linked_list.size(); i++) {
            if (linked_list.get(i).getId() == id) {
                linked_list.remove(i);
            }
        }
    }

    public void updateVictims() {
        /**
         * Updates victim for all living players.
         */
        for (int player_id = 0; player_id < this._alive.size(); player_id++) { //for each player alive
            this._alive.get(player_id).requestVictim();
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

    public void announceAllAlivePlayers(String announcement) {
        for (int player_id = 0; player_id < this._alive.size(); player_id++) { //for each player alive
            this._alive.get(player_id).announce(announcement);
        }
    }

    public String stringifyState() {
        String state = "";
        for (int player_id = 0; player_id < this._players.length; player_id++) { //for each player
            state += this._players[player_id].get_name() + " ID: "+this._players[player_id].getId();
            state += "\n\t VICTIM_ID: "+this._players[player_id].getVictimId();
            state += "\n\t VOTE_ID: "+this._players[player_id].getVoteId();

            state+="\n";
        }
        return state;
    }

    public String stringifyAliveState() {
        String state = "";
        for (int player_id = 0; player_id < this._alive.size(); player_id++) { //for each player
            state += this._alive.get(player_id).get_name() + " ID: "+this._alive.get(player_id).getId();
            state += "\n\t VICTIM_ID: "+this._alive.get(player_id).getVictimId();
            state += "\n\t VOTE_ID: "+this._alive.get(player_id).getVoteId();

            state+="\n";
        }
        return state;
    }

    public void addAnouncement(String announcement) {
        this._announcements.add(announcement);
    }

    public void announceAllAnnouncements() {
        while (this._announcements.size()>0) {
            this.announceAllPlayers(_announcements.remove());
        }
    }

    /**
     * Vote logic.
     *
     * Each player can choose any of the other players, and see the live score updating accordingly.
     * Each player chooses a person
     * person with most votes is killed
     *
     * @return
     */
    public Player haveVote() {
        int [] vote_array = new int [this._players.length];
        for (int i=0; i<vote_array.length; i++) {
            vote_array[i] = 0;
        }
        for (int player_id = 0; player_id < this._alive.size(); player_id++) { //for each player
            this._players[player_id].requestVote();
            vote_array[this._players[player_id].getVoteId()] += 1;//votearray shows how many times each has been voted.
        }
        int max = 0;
        int count = 0;
        for (int i=0; i<vote_array.length; i++) {
            if (vote_array[max] < vote_array[i]) {
                max = i;
                count = 1;
            }
            else if (vote_array[max] == vote_array[i]) {
                count += 1;
            }
        }
        if (count>1) {
            return haveVote();
        }
        else {
            return this._players[max];
        }
    }

    public boolean isGameOver() {
        return false;
    }

    public void announceScore() {
        
    }
}
