/*
 * Created on 19-Dec-2006
 * Copyright (C) 2006 by Andrea Vacondio.
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the 
 * GNU General Public License as published by the Free Software Foundation; 
 * either version 2 of the License.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program; 
 * if not, write to the Free Software Foundation, Inc., 
 *  59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package it.pdfsam.panels;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.border.EtchedBorder;

/**
 * Status bar for the main GUI
 * 
 * @author Andrea Vacondio
 * @see javax.swing.JPanel
 */
public class JStatusPanel extends JPanel {

	private static final long serialVersionUID = 4178557129723539075L;

	private final JLabel plugIcon = new JLabel();
	private final JLabel plugDesc = new JLabel();
	private final JProgressBar progressBar = new JProgressBar();

	public JStatusPanel() {
		this(null, null);
	}

	public JStatusPanel(Icon icon, String desc) {
		super();
		plugDesc.setText(desc);
		plugIcon.setIcon(icon);
		init();
	}

	/**
	 * Sets the panel icon
	 * @param icon
	 */
	public void setIcon(Icon icon) {
		plugIcon.setIcon(icon);
	}

	/**
	 * sets the panel text
	 * @param text
	 */
	public void setText(String text) {
		plugDesc.setText(text);
	}

	/**
	 * Delegate to progressBar
	 * @param value
	 */
	public void setBarIndeterminate(boolean value){
		progressBar.setIndeterminate(value);
	}
	
	/**
	 * Delegate to progressBar
	 * @param value
	 */
	public void setBarValue(int value){
		progressBar.setValue(value);
	}
	
	/**
	 * Delegate to progressBar
	 * @param value
	 */	
	public void setBarString(String value){
		progressBar.setString(value);
	}
	
	/**
	 * Delegate to progressBar
	 * @param value
	 */
	public void setBarStringPainted(boolean value){
		progressBar.setStringPainted(value);
	}
	
	/**
	 * Delegate to progressBar
	 * @return true if the string is painted
	 */
	public boolean isBarStringPainted(){
		return progressBar.isStringPainted();
	}
	
	/**
	 * Delegate to progressBar
	 * @return progressBar value
	 */
	public int getBarValue(){
		return progressBar.getValue();
	}
	
	private void init() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setPreferredSize(new Dimension(600, 24));

		plugIcon.setMinimumSize(new Dimension(20, 20));
		plugDesc.setMinimumSize(new Dimension(100, 20));
		plugDesc.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));

		plugIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
		plugDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		progressBar.setBorderPainted(true);
		progressBar.setOrientation(JProgressBar.HORIZONTAL);
		progressBar.setMaximum(100);
		progressBar.setMinimumSize(new Dimension(150, 20));
		progressBar.setPreferredSize(new Dimension(350, 20));
		progressBar.setMaximumSize(new Dimension(350, 20));
		progressBar.setBorder(new EtchedBorder(EtchedBorder.RAISED));

		
		final JSeparator separator = new JSeparator(JSeparator.VERTICAL);
		separator.setMaximumSize(new Dimension(10, 20));

		add(Box.createRigidArea(new Dimension(5, 0)));
		add(plugIcon);
		add(Box.createRigidArea(new Dimension(5, 0)));
		add(separator);
		add(Box.createRigidArea(new Dimension(5, 0)));
		add(plugDesc);
		add(Box.createHorizontalGlue());
		add(progressBar);
	}

}
