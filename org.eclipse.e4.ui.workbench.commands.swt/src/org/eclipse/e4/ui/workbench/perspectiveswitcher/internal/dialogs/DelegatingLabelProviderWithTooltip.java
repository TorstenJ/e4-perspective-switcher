/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Joseph Carroll <jdsalingerjr@gmail.com> - Updated for Eclipse4
 *******************************************************************************/
package org.eclipse.e4.ui.workbench.perspectiveswitcher.internal.dialogs;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

/**
 * @since 3.5
 *
 */
public abstract class DelegatingLabelProviderWithTooltip extends ColumnLabelProvider {
  /**
   *
   */
  private final ILabelDecorator decorator;

  ILabelProvider wrappedLabelProvider;

  /**
   * 
   * @param pWrappedLabelProvider
   * @param pDecorator
   */
  DelegatingLabelProviderWithTooltip(ILabelProvider pWrappedLabelProvider, ILabelDecorator pDecorator) {
    this.wrappedLabelProvider = pWrappedLabelProvider;
    this.decorator = pDecorator;
    pWrappedLabelProvider.addListener(new ILabelProviderListener() {

      @Override
      public void labelProviderChanged(LabelProviderChangedEvent event) {
        fireLabelProviderChanged(event);
      }
    });
  }

  @Override
  protected void initialize(ColumnViewer viewer, ViewerColumn column) {
    super.initialize(viewer, column);
    if (decorator != null) {
      ColumnViewerToolTipSupport.enableFor(viewer);
    }
  }

  @Override
  protected void fireLabelProviderChanged(LabelProviderChangedEvent event) {
    super.fireLabelProviderChanged(event);
  }

  @Override
  public String getText(Object element) {
    return wrappedLabelProvider.getText(element);
  }

  @Override
  public Image getImage(Object element) {
    return wrappedLabelProvider.getImage(element);
  }

  @Override
  public Font getFont(Object element) {
    if (wrappedLabelProvider instanceof IFontProvider) {
      return ((IFontProvider) wrappedLabelProvider).getFont(element);
    }
    return null;
  }

  @Override
  public Color getForeground(Object element) {
    if (wrappedLabelProvider instanceof IColorProvider) {
      return ((IColorProvider) wrappedLabelProvider).getForeground(element);
    }
    return null;
  }

  @Override
  public Color getBackground(Object element) {
    if (wrappedLabelProvider instanceof IColorProvider) {
      return ((IColorProvider) wrappedLabelProvider).getBackground(element);
    }
    return null;
  }

  @Override
  public String getToolTipText(Object element) {
    if (decorator == null) {
      return null;
    }
    String text = getText(element);
    element = unwrapElement(element);
    return decorator.decorateText(text, element);
  }

  /**
   * Returns the element that will be used to determine the bundle id. In most cases, this method can just return the
   * provided element. Sometimes, it might be necessary to return a nested object, or an IConfigurationElement.
   * 
   * @param element
   * @return the element, or a client object wrapped by element, or an IConfigurationElement
   */
  protected abstract Object unwrapElement(Object element);

  @Override
  public void dispose() {
    wrappedLabelProvider.dispose();
    super.dispose();
  }
}