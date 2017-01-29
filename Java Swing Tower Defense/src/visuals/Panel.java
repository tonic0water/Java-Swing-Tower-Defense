package visuals;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;

import Map.RoadMapList;
import buttons.*;

public class Panel extends JPanel implements ActionListener{

	private int pw;
	private int ph;
	private Font bodyFont = new Font("Serif", Font.BOLD, 24);
	private CreateButton create;
	private StartButton start;
	private LoadButton load;
	private QuitButton quit;
	
	private RoadMapList road;

	private int dimensions = 15;

	public Panel(int width, int height){
		this.ph = height;
		this.pw = width;
	}

	@Override
	public void paintComponent(Graphics gr){
		super.paintComponent(gr);
		try {
			paintBackground(gr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void paintBackground(Graphics gr) throws IOException{
		this.setBackground(Color.BLACK);
		writeWelcome(gr);
	}

	private void writeWelcome(Graphics gr) throws IOException{
		this.setLayout(new GridLayout(5, 1));
		gr.drawImage(drawLogo(), (int)(pw/3.4), ph/15, null);
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(writeBodyText());
		this.add(createButtons());
	}

	private BufferedImage drawLogo() throws IOException{
		return ImageIO.read(new File("src/visuals/logo.jpg"));

	}

	private JLabel writeBodyText(){

		JLabel body = new JLabel("A Tower Defense Game", JLabel.CENTER);
		body.setForeground(Color.YELLOW);
		body.setFont(bodyFont);
		return body;
	}

	private JPanel createButtons(){
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(Color.BLACK);
		buttonPane.setLayout(new GridLayout(3,1));
		buttonPane.add(new JLabel());

		JPanel buttons = new JPanel();
		buttons.setBackground(Color.BLACK);
		buttons.setLayout(new GridLayout(1,6));
		buttons.add(new JLabel());

		start = new StartButton("Start Game");
		this.start.addActionListener(this);
		buttons.add(start);

		create = new CreateButton("Create Map");
		this.create.addActionListener(this);
		buttons.add(create);

		load = new LoadButton("Load Map");
		this.load.addActionListener(this);
		buttons.add(load);


		quit = new QuitButton("Quit Game");
		this.quit.addActionListener(this);
		buttons.add(quit);

		buttons.add(new JLabel());
		buttonPane.add(buttons);
		buttonPane.add(new JLabel());

		return buttonPane;
	}

	@Override
	public void actionPerformed(ActionEvent e){
		JButton source = (JButton)e.getSource();
		if(source instanceof StartButton){
			startGame();
		}
		else if(source instanceof CreateButton){
			//createMap();
		}
		else if(source instanceof LoadButton){
			//loadMap();
		}
		else if(source instanceof QuitButton){
			//TODO: quitGame(); popup window asking user if they're sure they want to quit
			System.exit(666);
		}

	}

	public void startGame(){

		this.removeAll();
		//this.repaint();
		this.setLayout(new BorderLayout());
		JLabel spacingTop = new JLabel ();
		spacingTop.setPreferredSize(new Dimension(100, 10));
		this.add(spacingTop,BorderLayout.PAGE_START);

		JLabel spacingLeft = new JLabel ();
		spacingLeft.setPreferredSize(new Dimension(10, 10));
		this.add(spacingLeft,BorderLayout.LINE_START);

		JLayeredPane lp = new JLayeredPane();
		GameScreen gs = new GameScreen((int)(pw/1.4),(int)(ph/1.35), dimensions);
		lp.add(gs,1);

		CritterScreen cs = new CritterScreen((int)(pw/1.4),(int)(ph/1.35), dimensions, 1);
		lp.add(cs, 0);
		this.add(lp,BorderLayout.CENTER);

		JPanel spacingRight = new JPanel();
		spacingRight.setBackground(Color.RED);
		spacingRight.setLayout(new BorderLayout());
		spacingRight.setPreferredSize(new Dimension(230,0));
		this.add(spacingRight,BorderLayout.LINE_END);

		this.add(createBottomBar(), BorderLayout.PAGE_END);
		this.repaint();
		this.setVisible(true);
		this.revalidate();
	}

	private JPanel createBottomBar() {
		JPanel bottomBar = new JPanel();
		bottomBar.setBackground(Color.BLACK);
		bottomBar.setPreferredSize(new Dimension(10, 110));
		bottomBar.setLayout(new BorderLayout());

		bottomBar.add(createProgressBar(), BorderLayout.PAGE_START);
		bottomBar.add(createFlow(), BorderLayout.CENTER);

		JLabel empty1 = new JLabel();
		empty1.setPreferredSize(new Dimension(40,0));
		bottomBar.add(empty1, BorderLayout.LINE_START);

		JLabel empty2 = new JLabel();
		empty2.setPreferredSize(new Dimension(40,0));
		bottomBar.add(empty2, BorderLayout.LINE_END);
		return bottomBar;
	}

	private JPanel createProgressBar() {
		JPanel progress = new JPanel();
		progress.setLayout(new BorderLayout());
		progress.setBackground(Color.BLACK);

		JLabel empty = new JLabel();
		empty.setPreferredSize(new Dimension(255, 0));
		progress.add(empty, BorderLayout.LINE_END);

		JLabel life = new JLabel("     Player Life:     ");
		life.setForeground(Color.BLUE);
		progress.add(life, BorderLayout.LINE_START);
		JProgressBar pb = new JProgressBar();
		pb.setValue(100);
		pb.setStringPainted(true);
		pb.setBackground(Color.DARK_GRAY);
		pb.setBorder(BorderFactory.createEmptyBorder());
		if(pb.getValue() > 50){
			pb.setForeground(Color.GREEN);
		}
		else if((pb.getValue() < 50) && pb.getValue() > 25){
			pb.setForeground(Color.YELLOW);
		}
		else{
			pb.setForeground(Color.RED);
		}
		progress.add(pb, BorderLayout.CENTER);
		return progress;

	}

	private JPanel createFlow(){
		JPanel flow = new JPanel();
		flow.setLayout(new BorderLayout());
		flow.setBackground(Color.BLACK);

		JLabel topSpace = new JLabel();
		topSpace.setPreferredSize(new Dimension(0, 10));
		flow.add(topSpace, BorderLayout.PAGE_START);

		JLabel botSpace = new JLabel();
		botSpace.setPreferredSize(new Dimension(0, 10));
		flow.add(botSpace, BorderLayout.PAGE_END);

		JPanel waveButtons = new JPanel();
		waveButtons.setBackground(Color.BLACK);
		flow.add(waveButtons, BorderLayout.LINE_END);

		waveButtons.setLayout(new GridLayout(5,1));

		JButton startWave = new JButton("Start Wave");
		JButton quit = new JButton("Quit to Main Menu");

		waveButtons.add(new JLabel()); 
		waveButtons.add(startWave);
		waveButtons.add(new JLabel()); 
		waveButtons.add(quit);
		waveButtons.add(new JLabel()); 

		JPanel tabSpacing = new JPanel();
		tabSpacing.setBackground(Color.BLACK);
		tabSpacing.setLayout(new BorderLayout());
		JLabel rSpace = new JLabel();
		rSpace.setPreferredSize(new Dimension(55,0));
		tabSpacing.add(rSpace, BorderLayout.LINE_END);
		
		JTabbedPane upgrades = new JTabbedPane();
		tabSpacing.add(upgrades, BorderLayout.CENTER);
		flow.add(tabSpacing, BorderLayout.CENTER);
		
		//upgrades.addTab("Damage Upgrades". );


		return flow;


	}
}
