package GMassist.framework;

import org.apache.pivot.wtk.Component;

public class Plugin {
  
  /** The name of the plugin */
  String name;
  
  /** the gui that will be displayed on the tab */
  Component gui;
  
  /** get the name of the plugin */
  public String    getName() { return name; }
  
  /** get the gui layout for the plugin */
  public Component getGui()  { return gui;  }
}
