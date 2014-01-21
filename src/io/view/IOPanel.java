package io.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import io.controller.IOController;
import io.model.Game;

import javax.swing.*;

public class IOPanel extends JPanel
{

	private JButton loadButton;
	private IOController baseController;
	private JButton saveButton;
	private JTextField titleField;
	private JTextField rankingField;
	private JTextArea rulesArea;
	private JLabel rulesLabel;
	private JLabel rankingLabel;
	private JLabel titleLabel;
	private JLabel gameCountLabel;
	private SpringLayout baseLayout;

	public IOPanel(IOController baseController)
	{

		this.baseController = baseController;

		saveButton = new JButton("save the game stuff");
		titleField = new JTextField(15);
		titleLabel = new JLabel("Game title:");
		rankingField = new JTextField(5);
		rankingLabel = new JLabel("Game Ranking");
		rulesArea = new JTextArea(5, 20);
		rulesLabel = new JLabel("Gamerules:");
		gameCountLabel = new JLabel("current game count:");
		baseLayout = new SpringLayout();
		loadButton = new JButton("LOad here");
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, 0, SpringLayout.NORTH, saveButton);
		baseLayout.putConstraint(SpringLayout.EAST, loadButton, 0, SpringLayout.EAST, rulesArea);

		setupPanel();
		setupLayout();
		setupListeners();

	}

	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.add(rankingField);
		this.add(rankingLabel);
		this.add(rulesArea);
		this.add(rulesLabel);
		this.add(titleField);
		this.add(titleLabel);
		this.add(saveButton);
		this.add(titleField);
		this.add(titleLabel);
		this.add(loadButton);
		this.add(gameCountLabel);
	}

	private void setupLayout()
	{
		baseLayout.putConstraint(SpringLayout.SOUTH, rankingLabel, -207,
				SpringLayout.SOUTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, rulesLabel, 114,
				SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, rankingField, 76,
				SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, rulesArea, 109,
				SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.NORTH, titleLabel, 3,
				SpringLayout.NORTH, titleField);
		baseLayout.putConstraint(SpringLayout.WEST, titleLabel, 0,
				SpringLayout.WEST, rankingLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rulesLabel, 10,
				SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.SOUTH, titleField, -15,
				SpringLayout.NORTH, rankingField);
		baseLayout.putConstraint(SpringLayout.NORTH, saveButton, 28,
				SpringLayout.SOUTH, rulesArea);
		baseLayout.putConstraint(SpringLayout.WEST, saveButton, 62,
				SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, rankingField, 0,
				SpringLayout.WEST, titleField);
		baseLayout.putConstraint(SpringLayout.WEST, titleField, 108,
				SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, rankingLabel, 10,
				SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, rulesArea, 126,
				SpringLayout.WEST, this);

	}

	private void setupListeners()
	{
		saveButton.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent click)
			{
				Game tempGame = baseController.makeGameFromInput(titleField.getText(), rankingField.getText(), rulesArea.getText());
				if (tempGame != null)
				{
					baseController.saveGameInformation(tempGame);
					gameCountLabel.setText("Current game count: " + baseController.getProjectGames().size());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Try again with a valid number");
				}
			}
		});
		
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				Game tempGame = baseController.readGameInformation();
				if( tempGame !=null)
				{
					titleField.setText(tempGame.getGameTitle());
					rankingField.setText(Integer.toString(tempGame.getFunRanking()));
					String tempRules = "";
					for(String currentRule : tempGame.getGameRules() )
					{
						tempRules += currentRule + "\n";
					}
					rulesArea.setText(tempRules);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Check the save file make sure it is in order.");
				}
			}
		});
	}
}