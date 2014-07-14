package com.jf.scout.server;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;

/**
 * Dummy application in order to manage server side product configurations in *.product files.
 * A typical config.ini for such a product has (among others) the following properties:
 * osgi.clean=true
 * osgi.console=
 * eclipse.consoleLog=true
 * org.eclipse.equinox.http.jetty.http.port=8080
 * org.eclipse.equinox.http.jetty.context.path=/scout_server
 * osgi.bundles=org.eclipse.equinox.common@2:start, org.eclipse.update.configurator@start,
 * org.eclipse.equinox.http.jetty@start, org.eclipse.equinox.http.registry@start, org.eclipse.core.runtime@start
 * osgi.bundles.defaultStartLevel=4
 * osgi.noShutdown=true
 * eclipse.ignoreApp=false
 * eclipse.product=com.jf.scout.server.product
 */
public class ServerApplication implements IApplication {
  private static IScoutLogger logger = ScoutLogManager.getLogger(ServerApplication.class);

  @Override
  public Object start(IApplicationContext context) throws Exception {
    //start the scheduler
    /*
    Scheduler scheduler=new Scheduler(Activator.getDefault().getBackendSubject(),ServerSession.class);
    scheduler.addJob(new LoadJobs());
    scheduler.start();
    Activator.getDefault().setScheduler(scheduler);
    */
    logger.info("scout server initialized");
    return EXIT_OK;
  }

  @Override
  public void stop() {

  }
}
