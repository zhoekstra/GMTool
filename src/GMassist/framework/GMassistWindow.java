package GMassist.framework;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Collection;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.TabPane;
import org.apache.pivot.wtk.Window;

public class GMassistWindow extends Window implements Bindable {
  
  /** the base pane that every plugin will load onto */
  private TabPane pane = null;

  @Override
  public void initialize(Map<String, Object> space, URL loc, Resources res) {
    pane = (TabPane)space.get("mainGMassistPane");
  }
  
  /**
   * Adds all of the active Plugins from a list to the TabPane. This will be
   * called after the user has selected a GM workspace since the GM workspace
   * needs to specify the active Plugins.
   * 
   * @param activePlugins  the list of Plugins to add to the TabPane
   */
  public void applyPlugins(Collection<GMassistTab> activePlugins) {
    boolean added = false;
    
    for(GMassistTab curr : activePlugins) {
      pane.getTabs().add(curr.getGui());
      added = true;
    }
    
    if(added) {
      pane.setSelectedIndex(0);
    }
  }
}
