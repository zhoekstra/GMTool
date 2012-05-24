package GMassist.framework;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Component;

public class GMassistTab {
  
  /** The name of the plugin */
  String name;
  
  /** the gui that will be displayed on the tab */
  Component gui;
  
  /* the plugin that is loaded onto this tab */
  Plugin plugin;
  
  /** properties associated with the Plugin */
  Map<String, String> properties;
  
  public GMassistTab(URL jar)
      throws IOException, SerializationException,
             InstantiationException, IllegalAccessException,
             ClassNotFoundException {
    URLClassLoader loader = new URLClassLoader(new URL[]{jar});
    BXMLSerializer bxml = new BXMLSerializer();
    
    /* get the resource names */
    String className = jar.getFile().split(".")[0];
    String bxmlName = "gmassist_" + className.toLowerCase() + ".jar";
    
    /* load the gui and the class from the jar file */
    gui = (Component)bxml.readObject(loader.getResource(bxmlName));
    plugin = (Plugin)loader.loadClass(className).newInstance();
  }
  
  /** get the name of the plugin */
  public String getName() { return name; }
  
  /** get the gui layout for the plugin */
  public Component getGui()  { return gui; }
  
  /** get a property for the plugin */
  public String getProperty(String name) { return properties.get(name); }
}
