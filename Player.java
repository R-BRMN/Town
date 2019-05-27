public class Player {

    private String _name;
    private String _job;
    private boolean _muted;
    private int _vote_id;
    private int _victim_id;
    private Connector _connector;

    public boolean isAlive() {
        return _alive;
    }

    public void setAlive(boolean _alive) {
        this._alive = _alive;
    }

    private boolean _alive;

    public boolean isImmortal() {
        return _immortal;
    }

    public void setImmortal(boolean _immortal) {
        this._immortal = _immortal;
    }

    private boolean _immortal;

    public int getId() {
        return _id;
    }


    private int _id;

    public Player (Connector connector) {
        _job = null;
        _connector = connector;
        _id = connector.getId();

    }

    public Connector get_connector() {
        return _connector;
    }

    public void set_connector(Connector _connector) {
        this._connector = _connector;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_job() {
        return _job;
    }

    public void set_job(String _job) {
        this._job = _job;
    }

    public boolean getMuted() {
        return _muted;
    }

    public void setMuted(boolean _muted) {
        this._muted = _muted;
    }

    public int getVoteId() {
        return _vote_id;
    }

    public void set_vote_id(int _vote_id) {
        this._vote_id = _vote_id;
    }

    public int getVictimId() {
        return _victim_id;
    }

    public void set_victim_id(int _victim_id) {
        this._victim_id = _victim_id;
    }

    public void request_name() {
        this.set_name(this.get_connector().requestName());
    }

    public String investigate () {
        //notify all players
        return this._job;
    }

    public void assignJob (String job) {
        this._job = job;
        this._connector.announceJob(job);
    }


    public void updateVote() {
        return;
    }

    public void updateVictim() {
        this._victim_id = this._connector.requestVictim();
    }

    public void announce(String announcement) {
        this._connector.announce(announcement);
    }
}
