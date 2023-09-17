package src.Logos.Scripts;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OpenBrowserUrl {
    public void browserUrl(String Url){
        if(Desktop.isDesktopSupported()){
            try {
                URI uri = new URI(Url);
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(uri);
            } catch (URISyntaxException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
