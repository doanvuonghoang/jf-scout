package com.jf.scout.client.core;

import java.beans.PropertyChangeEvent;

import org.eclipse.scout.commons.UriUtility;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.AbstractClientSession;
import org.eclipse.scout.rt.client.ClientJob;
import org.eclipse.scout.rt.client.servicetunnel.http.ClientHttpServiceTunnel;
import org.eclipse.scout.rt.client.ui.desktop.IDesktop;
import org.eclipse.scout.rt.shared.services.common.code.CODES;

import com.jf.scout.client.core.ui.desktop.Desktop;

public class ClientSession extends AbstractClientSession {
  private static IScoutLogger logger = ScoutLogManager.getLogger(ClientSession.class);

  public ClientSession() {
    super(true);
  }

  /**
   * @return session in current ThreadContext
   */
  public static ClientSession get() {
    return ClientJob.getCurrentSession(ClientSession.class);
  }

  @Override
  public void execLoadSession() throws ProcessingException {
    setServiceTunnel(new ClientHttpServiceTunnel(this, UriUtility.toUrl(getBundle().getBundleContext().getProperty("server.url"))));

    //pre-load all known code types
    CODES.getAllCodeTypes(com.jf.scout.shared.core.Activator.PLUGIN_ID);

    setDesktop(new Desktop());

    // turn client notification polling on
    // getServiceTunnel().setClientNotificationPollInterval(2000L);
  }

  /* (non-Javadoc)
   * @see org.eclipse.scout.rt.client.AbstractClientSession#setDesktop(org.eclipse.scout.rt.client.ui.desktop.IDesktop)
   */
  @Override
  public void setDesktop(IDesktop a) throws ProcessingException {
    // TODO Auto-generated method stub
    if (a instanceof Desktop) {
      ((Desktop) a).addPropertyChangeListener("needRestart", (PropertyChangeEvent evt) -> {
        if (evt.getNewValue().equals(true)) {

          Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
              // TODO Auto-generated method stub
              while (!a.isOpened()) {
                try {
                  Thread.sleep(1000);
                }
                catch (Exception e) {
                }

              }

              try {
                stopSession(org.eclipse.equinox.app.IApplication.EXIT_RESTART);
              }
              catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
              }
            }
          });
          t.start();

        }
      });
    }

    super.setDesktop(a);
  }

  @Override
  public void execStoreSession() throws ProcessingException {
  }
}
