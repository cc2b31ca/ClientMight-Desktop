package src.Logos.Scripts;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class LoadPlugins {
    String jar_path = "Plugins/KPlugins.jar";

    public void Load() {
        try {
            URL jarurl = new File(jar_path).toURI().toURL();
            URLClassLoader classLoader = new URLClassLoader(new URL[]{jarurl});
            Class<?> exturnclass = classLoader.loadClass("com.example.ExternalClass");
            Object exturnobject = exturnclass.newInstance();
            exturnclass.getMethod("externalMethod").invoke(exturnobject);
        } catch (MalformedURLException | ClassNotFoundException | InstantiationException | IllegalAccessException |
                 InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }
}
