package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediaStore extends JPanel {
    private Media media;

    public MediaStore(Media media, StoreManagerScreen parentScreen) {
        this.media = media;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 18)); 
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel(String.format("%.2f $", media.getCost())); 
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        if (media instanceof Playable) {
            JButton playButton = new JButton("Play");
            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog playDialog = new JDialog(parentScreen, "Playing Media", false); 
                    playDialog.setSize(300, 150);
                    playDialog.setLocationRelativeTo(parentScreen);
                    JLabel playingLabel = new JLabel("Playing: " + media.getTitle(), SwingConstants.CENTER);
                    playingLabel.setFont(new Font(playingLabel.getFont().getName(), Font.BOLD, 16));
                    playDialog.add(playingLabel);
                    try {
                        ((Playable) media).play();
                    } catch (PlayerException ex) {
                        throw new RuntimeException(ex);
                    }

                    playDialog.setVisible(true);
                }
            });
            container.add(playButton);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setPreferredSize(new Dimension(200, 150)); 
        this.setMaximumSize(new Dimension(220,170)); 
    }
}