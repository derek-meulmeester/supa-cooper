import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.supacooper.SupaCooper;


public class DesktopGame {
        public static void main (String[] args) {
                new LwjglApplication(new SupaCooper(), "SupaCooper - Desktop", 800, 480, false);
        }
}
