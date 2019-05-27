public class Main {
    public static void main (String [] args) {
        System.out.println("Welcome to Town testing!");

        Player p1 = new Player (new TestConnector(0));
        Player p2 = new Player (new TestConnector(1));
        Player p3 = new Player (new TestConnector(2));
        Player p4 = new Player (new TestConnector(3));
        Player p5 = new Player (new TestConnector(4));
        Player p6 = new Player (new TestConnector(5));
        Player p7 = new Player (new TestConnector(6));
        Player p8 = new Player (new TestConnector(7));

        Controller c1 = new Controller(new Player []{p1, p2, p3, p4, p5, p6, p7, p8});

        Game g1 = new Game(c1);
        g1.getController().resetPlayers();
        g1.getController().assignJobs(new String[]{"KILLER","KILLER","DOCTOR","DETECTIVE"});
        //g1.getController().renameAll(); //Let each player set their own game
        g1.playStep();
        System.out.println(g1.getController().stringifyState());


    }
}
