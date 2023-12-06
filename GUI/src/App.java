public class App {
    public static void main(String[] args) throws Exception {

        Interface i = new Interface();
        while (true) {
            System.out.print("Current Screen: ");
            System.out.println(i.screen);
            while (i.screen==1) {
                System.out.print("\rAttempting Read...");
                Thread.sleep(1000);
                i.dataScreen.readData();
            }
        }
    }
}
