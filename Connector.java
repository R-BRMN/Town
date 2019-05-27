public interface Connector {

    public String requestName();
    public int requestVictim();
    public int requestVote();
    public void announce(String announcement);
    public void announceJob(String job);
    public void provideCurrentState();
    public int getId();

}
