package jfscout.ui.swt.core.views;

import jfscout.ui.swt.core.Activator;

import org.eclipse.scout.rt.ui.swt.ISwtEnvironment;
import org.eclipse.scout.rt.ui.swt.window.desktop.view.AbstractScoutView;

public class NorthEastView extends AbstractScoutView {

  public NorthEastView() {
  }

  @Override
  protected ISwtEnvironment getSwtEnvironment() {
    return Activator.getDefault().getEnvironment();
  }
}
