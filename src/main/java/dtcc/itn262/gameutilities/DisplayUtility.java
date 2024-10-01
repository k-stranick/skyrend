package dtcc.itn262.gameutilities;

public class DisplayUtility {

    private DisplayUtility() {
    }

    public static void printSeparator(int i) {
        for (int j = 0; j < i; j++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
