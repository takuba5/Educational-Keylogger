import com.github.kwhat.jnativehook.GlobalScreen;


public class Main {
    public static void main(String[] args) {


        try {
            GlobalScreen.registerNativeHook();
            KeyListener keyListener = new KeyListener();

            GlobalScreen.addNativeKeyListener(keyListener);
        } catch (Exception ignored) {

        }


    }
}