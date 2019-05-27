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


        //Let each player set their own game
        //c1.renameAll();
        //Let each player choose a victim
        c1.updateVictims();

    }
}
