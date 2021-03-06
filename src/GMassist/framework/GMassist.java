package GMassist.framework;

import java.io.File;
import java.io.IOException;
import java.net.URLClassLoader;

import nu.xom.Builder;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.HashMap;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;

public class GMassist implements Application {
  /** The path to the plugins folder */
  public static final String PLUGINFOLDER = "./plugins/";
  /** The xml parser */
  private Builder xmlParser = new Builder();
  /** jar loader loads classes and files in those jars */
  URLClassLoader jarExplorer;
	
  /** the actual window application */
  private GMassistWindow window = null;
  
  /** the set of possible plugins to load */
  private Map<String, GMassistTab> plugins = null;
  
  /**
   * Constructor the main GMassist object. This loads all the plugins their
   * associated GUI. This will also register any actions that need to be
   * taken for the menu system.
   */
  public GMassist() {
    BXMLSerializer bxml = new BXMLSerializer();
    plugins = new HashMap<String, GMassistTab>();
    
    File plugin_folder = new File(PLUGINFOLDER);
    for(File f : plugin_folder.listFiles()){
    	if(f.getName().endsWith(".jar")){
    		String pluginname = f.getName().split(".")[0];
    		plugins.put(pluginname, new GMassistTab(f.toURI().toURL()));
    	}
    }
  }
  
  @Override
  public void startup(Display disp, Map<String, String> prop) throws Exception {
    BXMLSerializer bxml = new BXMLSerializer();
    List<GMassistTab> activePlugins = new ArrayList<GMassistTab>();
    
    window = (GMassistWindow)bxml.readObject(
        getClass().getResource("gmassist_framework.bxml"));
    
    for(String name : plugins)
      activePlugins.add(plugins.get(name));
    window.applyPlugins(activePlugins);
    
    window.open(disp);
  }

  @Override
  public boolean shutdown(boolean optional) {
    if (window != null)
      window.close();
    return false;
  }

  @Override
  public void suspend() {
  }

  @Override
  public void resume() {
  }

  public static void main(String[] args) {
    DesktopApplicationContext.main(GMassist.class, args);
  }
  
  
}
