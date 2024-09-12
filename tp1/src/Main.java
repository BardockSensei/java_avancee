//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        CustomString cstr = new CustomString("Hello World !");
        System.out.println("Length = " + cstr.getCustomLength());
        System.out.println("Character at position 1 = '" + cstr.getCharacter(1) + "'\n");

        cstr.displayCustomString();

        cstr.addCharacter(9, 'Z');
        cstr.displayCustomString();
        cstr.addCharacter(24, 'Z');
        cstr.displayCustomString();

        CustomString subCstr = cstr.getSubCustomString(6, 5);
        subCstr.displayCustomString();
    }
}
