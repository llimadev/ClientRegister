import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

public class AppFrame extends JFrame {
    JButton registerClientButton;
    JButton viewClientButton;
    JMenuBar menuBar;

    AppFrame() {
        //Component declarations
        registerClientButton = new JButton();
        viewClientButton = new JButton();
        menuBar = new JMenuBar();

        //Frame config
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Quit the application when the Frame is closed
        this.setResizable(false);
        this.setSize(400, 600);
        this.setLocationRelativeTo(null); //Make it so the window starts centered on screen
        this.setLayout(new BorderLayout()); // Change layout of frame to BorderLayout

        //Menu bar config
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        //Panels config
        JPanel panel = new JPanel(new GridBagLayout()); // Create a new panel with GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,0,10,0);

        //Banner
        JLabel bannerLabel = new JLabel();
        try {
            Image bannerImage = ImageIO.read(new File("images/frame.png")); // Load the image
            bannerLabel.setIcon(new ImageIcon(bannerImage)); // Set the image as the icon of the label
        } catch (IOException e) {
            e.printStackTrace();
        }

        //RegisterClintButton Config
        registerClientButton.addActionListener(_ -> System.out.println("Register client clicked"));
        registerClientButton.setText("Register Client");
        registerClientButton.setFocusable(false);
        registerClientButton.setBounds(0,50,300,200);

        //viewClientButton Config
        viewClientButton.addActionListener(_ -> System.out.println("View Client click"));
        viewClientButton.setText("View Clients");
        viewClientButton.setFocusable(false);

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                int newHeight = AppFrame.this.getHeight();
                int fontSize = newHeight / 10;

                int maxFontSize = 30;
                if (fontSize > maxFontSize) {
                    fontSize = maxFontSize;
                }
                registerClientButton.setFont(new Font("Segoe UI", Font.PLAIN, fontSize));
                viewClientButton.setFont(new Font("Segoe UI", Font.PLAIN, fontSize));
            }
        });

        //Adding Components and making them visible
        panel.add(registerClientButton, gbc); // Add the buttons to the panel
        panel.add(viewClientButton, gbc);
        this.setJMenuBar(menuBar);

        this.add(bannerLabel, BorderLayout.NORTH); // Add the banner to the top of the frame
        this.add(panel, BorderLayout.CENTER); // Add the panel to the center of the frame
        this.setVisible(true);
    }
}
