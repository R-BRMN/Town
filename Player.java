public class Player {

    private String _name;
    private int _health;
    private String _job;
    private int _muted;
    private Player _vote;
    private int _victim;

    public Connector get_connector() {
        return _connector;
    }

    public void set_connector(Connector _connector) {
        this._connector = _connector;
    }

    private Connector _connector;
    private int _id;


    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_health() {
        return _health;
    }

    public void set_health(int _health) {
        this._health = _health;
    }

    public String get_job() {
        return _job;
    }

    public void set_job(String _job) {
        this._job = _job;
    }

    public int get_muted() {
        return _muted;
    }

    public void set_muted(int _muted) {
        this._muted = _muted;
    }

    public Player get_vote() {
        return _vote;
    }

    public void set_vote(Player _vote) {
        this._vote = _vote;
    }

    public int get_victim() {
        return _victim;
    }

    public void set_victim(int _victim) {
        this._victim = _victim;
    }

    public void request_name() {
        this.set_name(this.get_connector().request_name());
    }


    public Player (int id) {
        _health = 1;
        _job = null;
        _id = id;
    }

    public void kill () {
        this._health -= 1;
    }

    public void heal () {
        this._health += 1;
    }

    public String investigate () {
        //notify all players
        return this._job;
    }

    public void mute () {
        this._muted += 1;
    }

    public String assignJob (String job) {
        this._job = job;
        return job;
    }

    public void actJobOnVote () {
        this.actJob(this._vote);
    }


    public void actJob (Player victim) {
        String job = this._job;
        if (job.equals("KILLER")) {
            victim.kill();
        }
        else if (job.equals("DETECTIVE")) {
            victim.investigate();
        }
        else if (job.equals("WHORE")) {
            victim.mute();
        }
        else if (job.equals("DOCTOR")) {
            victim.heal();
        }
        return;
    }

    public void updateVote() {
        return;
    }

    public void updateVictim() {
        this.set_victim(this.get_connector().request_victim());
        return;
    }
}
