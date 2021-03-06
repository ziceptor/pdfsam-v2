/*
 * Created on 09-Nov-2008
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
package org.pdfsam.guiclient.commons.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.SoftBevelBorder;

import org.apache.log4j.Logger;
import org.pdfsam.guiclient.GuiClient;
import org.pdfsam.guiclient.business.listeners.SaveImageActionListener;
import org.pdfsam.guiclient.commons.business.listeners.EscapeKeyListener;
import org.pdfsam.guiclient.configuration.Configuration;
import org.pdfsam.guiclient.gui.components.JPreviewImage;
import org.pdfsam.i18n.GettextResource;
/**
 * Frame to open the single page preview
 * @author Andrea Vacondio
 *
 */
public class JPagePreviewFrame extends JFrame {

	private static final long serialVersionUID = -7352665495415591680L;

	private static final Logger log = Logger.getLogger(JPagePreviewFrame.class.getPackage().getName());
	
	private final JPanel mainPanel = new JPanel();
	private final JPanel statusPanel = new JPanel();
	private final JLabel statusLabel = new JLabel();
	private JScrollPane mainScrollPanel;
	private final JPreviewImage pagePreview = new JPreviewImage();
	private EscapeKeyListener escapeListener = new EscapeKeyListener(this);
	
	public JPagePreviewFrame(){
		initialize();
	}
	
	private void initialize() {
		try{	
			URL iconUrl = this.getClass().getResource("/images/pdf_"+GuiClient.getVersionType()+".png");
			setIconImage(new ImageIcon(iconUrl).getImage());
	        setSize(640, 480);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	        
			JMenuBar menuBar = new JMenuBar();
			JMenu menuFile = new JMenu();
			menuFile.setText(GettextResource.gettext(Configuration.getInstance().getI18nResourceBundle(),"File"));
			menuFile.setMnemonic(KeyEvent.VK_F);
			
			JMenuItem saveAsItem = new JMenuItem();
			saveAsItem.setText(GettextResource.gettext(Configuration.getInstance().getI18nResourceBundle(),"Save as"));
			saveAsItem.setActionCommand(SaveImageActionListener.SAVE_AS_ACTION);
			saveAsItem.addActionListener(new SaveImageActionListener(pagePreview, this));
			
			JMenuItem closeItem = new JMenuItem();
			closeItem.setText(GettextResource.gettext(Configuration.getInstance().getI18nResourceBundle(),"Close"));
			closeItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					setVisible(false);					
				}				
			});
			
				

			menuFile.add(saveAsItem);
			menuFile.add(closeItem);			
			menuBar.add(menuFile);
			getRootPane().setJMenuBar(menuBar);
			
			mainPanel.add(pagePreview);
			mainScrollPanel = new JScrollPane(mainPanel);
			
			statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
			statusPanel.setPreferredSize(new Dimension(600, 24));			
			statusPanel.add(Box.createRigidArea(new Dimension(5, 0)));
			statusPanel.add(statusLabel);			
			statusPanel.add(Box.createHorizontalGlue());
			statusPanel.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
			
			getContentPane().add(mainScrollPanel,BorderLayout.CENTER);
	        getContentPane().add(statusPanel,BorderLayout.PAGE_END); 
	        
	        addKeyListener(escapeListener);
	        
		}catch(Exception e){
			log.error(GettextResource.gettext(Configuration.getInstance().getI18nResourceBundle(),"Error creating preview panel."),e);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.Window#setVisible(boolean)
	 */
	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		if(!b){
			pagePreview.resetComponent();
		}
	}

	/**
	 * sets the image to be displayed
	 * @param image
	 */
	public void setPagePreview(Image image){
		pagePreview.setImage(image);
		statusLabel.setText("");
		validate();
        repaint();  
	}
	/**
	 * sets the image to be displayed and the status bar message
	 * @param image
	 * @param statusMessage
	 */
	public void setPagePreview(Image image, String statusMessage){
		pagePreview.setImage(image);
		statusLabel.setText(statusMessage);
		validate();
        repaint();  
	}
	
	@Override
	public Dimension getPreferredSize(){
		return pagePreview.getPreferredSize();
	}
}
