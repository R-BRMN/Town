public class Main {
    public static void main (String [] args) {
        System.out.println("Welcome to Town testing!");

        Controller c1 = new Controller();
        c1.addPlayer(new Player(1));
        c1.addPlayer(new Player(2));
        c1.addPlayer(new Player(3));
        c1.addPlayer(new Player(4));
        c1.addPlayer(new Player(5));
        c1.addPlayer(new Player(6));
        c1.addPlayer(new Player(7));

        for (int i=0; i<c1.get_players().size(); i++) {
            c1.get_players().get(i).set_connector(new TestConnector(i));
        }

        //Let each player set their own game
        //c1.renameAll();
        //Let each player choose a victim
        c1.updateVictims();

    }
}
