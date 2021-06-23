package view;

import model.CountDownTimer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

public class TimerForm extends JFrame {
    static int minute;
    static int second;

    Container container = getContentPane();
    ImageIcon mainIcon = new ImageIcon("images\\timericon128.png");
    JLabel imageLabel = new JLabel("", mainIcon, SwingConstants.CENTER);

    JLabel nameLabel = new JLabel("Timer");

    JLabel minuteLabel = new JLabel("min");
    JLabel secondLabel = new JLabel("sec");

    JTextField minuteTextField = new JTextField();
    JTextField secondTextField = new JTextField();

    JLabel timeLabel = new JLabel("00 : 00", SwingConstants.CENTER);
    JLabel dividerLabel = new JLabel(":");


    JButton startBtn = new JButton("START");


    public TimerForm() {

        setVisible(true);
        setBounds(0, 0, 256, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        container.setLayout(null);
        setLocationAndSize();
        setStyle();
        addComponentsToContainer();
        addActionEvent();

    }

    private void addActionEvent() {
        minuteTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });
        secondTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    e.consume();
                }
            }
        });

        startBtn.addActionListener(e -> {
            CountDownTimer timer = new CountDownTimer(Integer.parseInt(minuteTextField.getText()),
                    Integer.parseInt(secondTextField.getText()));
            minute = timer.getMinute();
            second = timer.getSecond();


            Thread tempThread = new Thread(() -> timeLabel.setText(getTime(minute, second)));
            Thread counterThread = new Thread(() -> {

                while (tempThread.isAlive()) {
                    try {
                        while (minute >= 0) {
                            timeLabel.setText(TimerForm.this.getTime(minute, second));

                            second--;
                            if (second < 0) {
                                second = 59;
                                minute--;
                            }
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                }


            });

            counterThread.start();
            tempThread.start();


        });

    }

    private String getTime(int minute, int second) {
        DecimalFormat df = new DecimalFormat("00");
        return df.format(minute) + " : " + df.format(second);
    }


    private void setStyle() {

        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.ITALIC, 34f));
        minuteLabel.setFont(nameLabel.getFont().deriveFont(20f));
        secondLabel.setFont(nameLabel.getFont().deriveFont(20f));

        minuteTextField.setFont(minuteTextField.getFont().deriveFont(20f));
        secondTextField.setFont(minuteTextField.getFont().deriveFont(20f));
        dividerLabel.setFont(minuteTextField.getFont().deriveFont(Font.BOLD, 30f));

        Border border = BorderFactory.createLineBorder(Color.black, 1);
        timeLabel.setBorder(border);
        timeLabel.setFont(nameLabel.getFont().deriveFont(20f));
        timeLabel.setOpaque(true);
        timeLabel.setBackground(new Color(198, 245, 226));

    }

    private void addComponentsToContainer() {
        container.add(imageLabel);
        container.add(nameLabel);
        container.add(minuteLabel);
        container.add(secondLabel);
        container.add(minuteTextField);
        container.add(secondTextField);
        container.add(dividerLabel);
        container.add(startBtn);

        container.add(timeLabel);


    }

    private void setLocationAndSize() {
        imageLabel.setBounds(60, 20, 128, 128);
        nameLabel.setBounds(74, 150, 300, 50);

        minuteLabel.setBounds(50, 245, 100, 30);
        minuteTextField.setBounds(40, 280, 60, 30);

        dividerLabel.setBounds(115, 275, 60, 30);

        secondLabel.setBounds(150, 245, 100, 30);
        secondTextField.setBounds(140, 280, 60, 30);

        startBtn.setBounds(60, 330, 120, 40);

        timeLabel.setBounds(70, 200, 100, 30);
    }

}
