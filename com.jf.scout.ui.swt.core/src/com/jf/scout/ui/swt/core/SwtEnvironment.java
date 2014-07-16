package com.jf.scout.ui.swt.core;

import org.eclipse.scout.rt.client.AbstractClientSession;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.ui.swt.AbstractSwtEnvironment;
import org.eclipse.scout.rt.ui.swt.ISwtEnvironmentListener;
import org.eclipse.scout.rt.ui.swt.SwtEnvironmentEvent;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

import com.jf.scout.ui.swt.core.editor.ScoutEditorPart;
import com.jf.scout.ui.swt.core.views.CenterView;
import com.jf.scout.ui.swt.core.views.EastView;
import com.jf.scout.ui.swt.core.views.NorthEastView;
import com.jf.scout.ui.swt.core.views.NorthView;
import com.jf.scout.ui.swt.core.views.NorthWestView;
import com.jf.scout.ui.swt.core.views.SouthEastView;
import com.jf.scout.ui.swt.core.views.SouthView;
import com.jf.scout.ui.swt.core.views.SouthWestView;
import com.jf.scout.ui.swt.core.views.WestView;

public class SwtEnvironment extends AbstractSwtEnvironment {

  public SwtEnvironment(Bundle bundle, String perspectiveId, Class<? extends AbstractClientSession> clientSessionClazz) {
    super(bundle, perspectiveId, clientSessionClazz);

    registerPart(IForm.VIEW_ID_OUTLINE, NorthWestView.class.getName());
    registerPart(IForm.VIEW_ID_OUTLINE_SELECTOR, SouthWestView.class.getName());
    registerPart(IForm.VIEW_ID_CENTER, CenterView.class.getName());
    registerPart(IForm.VIEW_ID_PAGE_TABLE, CenterView.class.getName());
    registerPart(IForm.VIEW_ID_PAGE_DETAIL, NorthView.class.getName());
    registerPart(IForm.VIEW_ID_PAGE_SEARCH, SouthView.class.getName());
    registerPart(IForm.VIEW_ID_N, NorthView.class.getName());
    registerPart(IForm.VIEW_ID_NW, NorthWestView.class.getName());
    registerPart(IForm.VIEW_ID_W, WestView.class.getName());
    registerPart(IForm.VIEW_ID_SW, SouthWestView.class.getName());
    registerPart(IForm.VIEW_ID_S, SouthView.class.getName());
    registerPart(IForm.VIEW_ID_SE, SouthEastView.class.getName());
    registerPart(IForm.VIEW_ID_E, EastView.class.getName());
    registerPart(IForm.VIEW_ID_NE, NorthEastView.class.getName());

    registerPart(IForm.EDITOR_ID, ScoutEditorPart.class.getName());

    addEnvironmentListener(new ISwtEnvironmentListener() {
      @Override
      public void environmentChanged(SwtEnvironmentEvent e) {
        if (e.getType() == SwtEnvironmentEvent.STOPPED) {
          if (!PlatformUI.getWorkbench().isClosing()) {
            PlatformUI.getWorkbench().close();
          }
        }
      }
    });

  }
}