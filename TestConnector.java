import java.util.Scanner;


public class TestConnector implements Connector {

    private int _id;

    public TestConnector(int id) {
        _id = id;
    }

    Scanner s = new Scanner(System.in);

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String requestName() {
        System.out.println("Please type new name for player.id = "+this.getId());
        String new_name = s.next();
        return (new_name);
    }

    public int requestVictim() {
        System.out.println("Who is your victim, player.id = "+this.getId()+"?");
        int victim = s.nextInt();
        return (victim);
    }

    public int requestVote() {
        System.out.println("Who do you vote to kill, player.id = "+this.getId()+"?");
        int vote = s.nextInt();
        return (vote);
    }

    public void announce(String announcement) {
        System.out.println("Announcement for id="+this._id+": "+announcement);
    }

    public void announceJob(String job) {
        System.out.println("Hey id="+this._id+", you are a "+job);
    }

    public void provideCurrentState() {

    }
}
