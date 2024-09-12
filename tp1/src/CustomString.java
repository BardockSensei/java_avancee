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
        // On aurait pu faire un appel au constructeur vide avec : this();
        this.customString = new char[customLengthMax];
        if (str != null) {
            // Blindage pour éviter les chaines qui dépassent la limite
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
        System.out.println("/================ CUSTOMSTRING ================/");
        for (int i=0; i<customLength; i++) {
            System.out.print(this.getCharacter(i));
        }
        System.out.println("\n[" + this.getCustomLength() + "/" + customLengthMax + "]");
        System.out.println("/==============================================/");
    }

    public void addCharacter(int position, char c) {
        if (0 <= position && position < customLengthMax) {
            this.customString[position] = c;
            if (position >= this.customLength) {
                this.customLength = position + 1;
            }
        }
    }

    public CustomString getSubCustomString(int position, int subLength) {
        String str = this.convertToString();
        String subStr = str.substring(position, position + subLength);
        return new CustomString(subStr);
    }

    public String getSubString(int position, int subLength) {
        String str = this.convertToString();
        return str.substring(position, position + subLength);
    }

    public void setCustomString(CustomString cs, int position) {
        if (0<= position && position < customLengthMax) {
            int length = cs.getCustomLength();
            if (position + length > customLengthMax) {
                length = customLengthMax - position;
            }

            for (int i = 0; i < length; i++) {
                this.customString[position + i] = cs.getCharacter(i);
            }

            this.customLength = Math.max(position + length, this.customLength);
        }
    }

    public void setCustomString(String s, int position) {
        if (0<= position && position < customLengthMax) {
            int length = s.length();
            if (position + length > customLengthMax) {
                length = customLengthMax - position;
            }

            for (int i = 0; i < length; i++) {
                this.customString[position + i] = s.charAt(i);
            }

            this.customLength = Math.max(position + length, this.customLength);
        }
    }

    public void concat(CustomString cs) {
        this.setCustomString(cs, this.getCustomLength());
    }

    public void concat(String s) {
        this.setCustomString(s, this.getCustomLength());
    }

    public void truncate(int newLength) {
        if (this.customLength > newLength) {
            this.customLength = newLength;
        }
    }

    public boolean equal(CustomString cs) {
        if (this.customLength != cs.getCustomLength()) {
            return false;
        }

        for (int i = 0; i < this.customLength; i++) {
            if (this.customString[i] != cs.getCharacter(i)) {
                return false;
            }
        }

        return true;
    }

    public boolean equal(String s) {
        if (this.customLength != s.length()) {
            return false;
        }

        for (int i = 0; i < this.customLength; i++) {
            if (this.customString[i] != s.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    /* Décale la sous-chaîne comprise entre "pos" et la fin de la chaîne
    de "n" unités vers la droite. Ici, on retourne un booléen pour dire à
    l'appelant si le décalage a pu se faire ou pas.*/
    private boolean rightShift(int position, int n) {
        if (this.customLength + n <= customLengthMax) {
            for (int i = this.customLength - 1; i >= position; i--) {
                this.customString[i + n] = this.customString[i];
                this.customString[i] = 0;
            }
            this.customLength += n;
            return true;
        } else {
            return false;
        }
    }

    public void insere(String s, int position) {
        if (0 <= position && position < this.customLength) {
            int length = s.length();
            if (this.rightShift(position, length)) {
                for (int i = 0; i < this.customLength; i++) {
                    this.customString[position + i] = s.charAt(i);
                }
            }
        }
    }

    public void insert(CustomString cs, int position) {
        if (0 <= position && position < this.customLength) {
            int length = cs.getCustomLength();
            if (this.rightShift(position, length)) {
                for (int i = 0; i < this.customLength; i++) {
                    this.customString[position + i] =cs.getCharacter(i);
                }
            }
        }
    }

    public int search(String s) {
        int length = s.length();
        for (int depart = 0; depart < this.customLength - length + 1; depart++ ) {
            int i = 0;
            while ((i < length) && (this.customString[depart + i] == s.charAt(i))) {
                i++;
            }

            if (i == length) {
                return depart;
            }
        }

        return -1;
    }

    // Comparaison de la chaîne "s" (de type "String") à la chaîne. Renvoie -1
    // si "s" est plus grande (ordre lexicographique) que la chaîne, 0 si "s"
    // est égale à la chaine, et 1 si "s" est plus petite que la chaîne.
    //
    public int compare(CustomString cs) {
        int length = cs.getCustomLength();
        int i = 0;

        while ((i < length) && (i < this.customLength) && (this.customString[i] == cs.getCharacter(i)))
        {
            i++;
        }

        if (i == length) {
            if (i == this.customLength) {
                return 0;
            } else {
                return 1;
            }
        } else if (i == this.customLength) {
            return -1;
        } else {
            if (this.customString[i] < cs.getCharacter(i)) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
