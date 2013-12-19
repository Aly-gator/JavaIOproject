package io.view;

import io.controller.IOController;

import javax.swing.JFrame;

public class IOFrame extends JFrame
{
	/**
	 * 
	 */
	private IOPanel basePanel;
	
	/**
	 * constructor for the IOFrame/
	 * @param baseController IOController passed in for MVC relationship.
	 * 
	 */
	public IOFrame(IOController baseController)
	{
		basePanel = new IOPanel(baseController);
		
		setupFrame();
		
	}
	/**
	 * Sets up the frame size and looks the content panel.
	 */
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setTitle("IO Project");
		this.setSize(400, 400);
		this.setVisible(true);
		
	}

}
