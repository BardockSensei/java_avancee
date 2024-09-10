public class CustomString
{
    private final static int customLengthMax = 65536;

    private char[] customString;
    private int customLength;

    public CustomString() {
        this.customString = new char[customLengthMax];
        this.customLength = 0;
    }

    public CustomString(String str) {
        // On aurait pu faire un appel à this(); ici
        this.customString = new char[customLengthMax];
        if (str != null) {
            // blindage pour éviter les chaines qui dépassent la limite
            this.customLength = Math.min(str.length(), customLengthMax);
            for (int i = 0; i < this.customLength; i++) {
                this.customString[i] = str.charAt(i);
            }
        }
    }

    public CustomString(CustomString cs) {
        this.customString = new char[customLengthMax];
        if (cs != null) {
            this.customLength = cs.getCustomLength();
            for (int i=0; i < cs.customLength; i++) {
                this.customString[i] = cs.getCharacter(i);
            }
        }
    }

    public int getCustomLength() {
        return this.customLength;
    }

    public char getCharacter(int pos) {
        if (0 <= pos && pos < customLengthMax) {
            return this.customString[pos];
        } else {
            return 0; // pas de -1 ici
        }
    }

    public String convertToString() {
        return new String(this.customString);
    }

    public void displayCustomString() {
        System.out.print("Chaine : ");
        for (int i=0; i<customLength; i++) {
            System.out.print(this.getCharacter(i));
        }
        System.out.println("Taille : " + this.getCustomLength());
    }

    public void addCharacter(int position, char c) {

    }

    // concat(chaine c)
    // concat(string s)
}
