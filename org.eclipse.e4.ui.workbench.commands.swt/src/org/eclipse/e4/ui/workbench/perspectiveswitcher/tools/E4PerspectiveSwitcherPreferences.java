/*******************************************************************************
 * Copyright (c) 2012 - 2013 Joseph Carroll and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Joseph Carroll <jdsalingerjr@gmail.com> - initial API and implementation
 *     Lars Vogel <Lars.Vogel@gmail.com> - ongoing maintenance
 ******************************************************************************/
package org.eclipse.e4.ui.workbench.perspectiveswitcher.tools;

import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

/**
 * All definitions and initialization for the preferences of the perspective
 * switcher.
 * 
 */
public class E4PerspectiveSwitcherPreferences {

	/** root node */
	public static final String ROOT_PREFERENCES_NODE = "org.eclipse.e4.ui.workbench.perspectiveswitcher"; //$NON-NLS-1$

	/** last active context node */
	public static final String LAST_ACTIVE_CONTEXT_NODE = "org.eclipse.e4.ui.workbench.perspectiveswitcher.lastActive"; //$NON-NLS-1$

	/** preference for showing the perspective name (when <code>true</code>) */
	public static final String SHOW_TEXT = "perspective_switcher_show_text"; //$NON-NLS-1$

	/**
	 * preference for showing the "new perspective" button (when
	 * <code>true</code>)
	 */
	public static final String SHOW_NEW_BUTTON = "perspective_switcher_show_new_button"; //$NON-NLS-1$

	/**
	 * preference for enabling the menus on the perspective buttons (when
	 * <code>true</code>)
	 */
	public static final String ENABLE_MENU = "perspective_switcher_enable_menu"; //$NON-NLS-1$

	/** preference for last active perspective */
	public static final String LAST_ACTIVE = "last_active_perspective"; //$NON-NLS-1$

	/**
	 * Sets up the preferences to default values:
	 * <ol>
	 * <li>Show text
	 * <li>Show "new" button
	 * <li>Enable menus
	 * <li>Reset last active perspective
	 */
	public static void initialize() {
		IEclipsePreferences showTextNode = DefaultScope.INSTANCE
				.getNode(ROOT_PREFERENCES_NODE);
		showTextNode.putBoolean(SHOW_TEXT, true);

		IEclipsePreferences newButtonNode = DefaultScope.INSTANCE
				.getNode(ROOT_PREFERENCES_NODE);
		newButtonNode.putBoolean(SHOW_NEW_BUTTON, true);

		IEclipsePreferences enableMenuNode = DefaultScope.INSTANCE
				.getNode(ROOT_PREFERENCES_NODE);
		enableMenuNode.putBoolean(ENABLE_MENU, true);

		IEclipsePreferences lastActiveNode = DefaultScope.INSTANCE
				.getNode(LAST_ACTIVE_CONTEXT_NODE);
		lastActiveNode.put(LAST_ACTIVE, ""); //$NON-NLS-1$
	}

}
