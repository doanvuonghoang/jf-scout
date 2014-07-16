package com.jf.scout.ui.rap.core;

import org.eclipse.scout.rt.ui.rap.AbstractStandaloneRwtEnvironment;

import com.jf.scout.client.core.ClientSession;

public class StandaloneRwtEnvironment extends AbstractStandaloneRwtEnvironment {

  public StandaloneRwtEnvironment() {
    super(Activator.getDefault().getBundle(), ClientSession.class);
  }
}
