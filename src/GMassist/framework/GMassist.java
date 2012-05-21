package GMassist.framework;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;

public class GMassist implements Application {
  GMassistWindow window = null;

  @Override
  public void startup(Display disp, Map<String, String> prop) throws Exception {
    BXMLSerializer bxml = new BXMLSerializer();
    window = (GMassistWindow)bxml.readObject(
        getClass().getResource("gmassist_framework.bxml"));
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
