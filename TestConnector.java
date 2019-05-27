import java.util.Scanner;


public class TestConnector extends Connector {

    Scanner s = new Scanner(System.in);

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    private int _id;

    public TestConnector(int id) {
        _id = id;
    }

    @Override
    public String request_name() {
        System.out.println("Please type new name for player.id = "+this.get_id());
        String new_name = s.next();
        return (new_name);
    }

    public int request_victim() {
        System.out.println("Who is your victim, player.id = "+this.get_id()+"?");
        int victim = s.nextInt();
        return (victim);
    }
}
