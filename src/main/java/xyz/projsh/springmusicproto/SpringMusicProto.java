package xyz.projsh.springmusicproto;

import com.formdev.flatlaf.FlatDarculaLaf;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class SpringMusicProto {
    
    public static String musLoc = String.format("%s/Music/", System.getProperty("user.home"));
    
    private static String[] args;
    private static ConfigurableApplicationContext context;
    
    public static void doRestart() {
        try {
            URL url = new URL("http://localhost:8080/api/restart");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.getInputStream();
            System.out.println("lol");
        } catch (Exception ex) {
            Logger.getLogger(WebGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void restart() {
        context.close();
        SpringMusicProto.context = SpringApplication.run(SpringMusicProto.class, args);
    }
    
    public static void main(String[] args) {
        FlatDarculaLaf.install();
	SpringMusicProto.args = args;
        SpringMusicProto.context = SpringApplication.run(SpringMusicProto.class, args);
	System.setProperty("java.awt.headless", "false");
	SwingUtilities.invokeLater(() -> {
            new WebGui().setVisible(true);
	});
    }

}
