package Lecture10;

import javax.swing.*;
import java.awt.*;

public class TextAreaWindow extends JFrame {
    private JTextArea textArea;
    private JComboBox<String> comboBox;

    public TextAreaWindow(String title) throws HeadlessException, ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        super(title);
        setNimbusLAF();
        SwingUtilities.updateComponentTreeUI(TextAreaWindow.this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null); // centralize
        Container container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        initFontFamilyComboBox();
        initFontStyleRadioBtns();
        initFontSizeSlider();
        initTextArea();

        setVisible(true);
    }

    private void initFontFamilyComboBox() {
        Box box = new Box(BoxLayout.X_AXIS);
        box.add(Box.createRigidArea(new Dimension(10, 0)));
        box.add(new JLabel("Font Family: "));
        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        comboBox = new JComboBox<>(fonts);
        box.add(Box.createRigidArea(new Dimension(10, 0)));
        box.add(comboBox);

        comboBox.addActionListener(e -> {
            String fontFamily = (String) comboBox.getSelectedItem();
            textArea.setFont(new Font(fontFamily, textArea.getFont().getStyle(), textArea.getFont().getSize()));
        });

        this.getContentPane().add(box);
    }

    private void initFontStyleRadioBtns() {
        Box box = new Box(BoxLayout.X_AXIS);
        box.add(Box.createRigidArea(new Dimension(10, 0)));
        box.add(new JLabel("Font Style: "));
        box.add(Box.createRigidArea(new Dimension(10, 0)));
        ButtonGroup group = new ButtonGroup();

        JRadioButton normal = new JRadioButton("Normal", true);
        normal.addActionListener(e -> {
            textArea.setFont(new Font(textArea.getFont().getFontName(), Font.PLAIN, textArea.getFont().getSize()));
        });
        group.add(normal);
        box.add(normal);
        box.add(Box.createRigidArea(new Dimension(10, 0)));

        JRadioButton bold = new JRadioButton("Bold", false);
        bold.setFont(new Font(bold.getFont().getFamily(), Font.BOLD, bold.getFont().getSize()));
        bold.addActionListener(e -> {
            textArea.setFont(new Font(textArea.getFont().getFontName(), Font.BOLD, textArea.getFont().getSize()));
        });
        group.add(bold);
        box.add(bold);
        box.add(Box.createRigidArea(new Dimension(10, 0)));

        JRadioButton italic = new JRadioButton("Italic", false);
        italic.setFont(new Font(italic.getFont().getFamily(), Font.ITALIC, italic.getFont().getSize()));
        italic.addActionListener(e -> {
            textArea.setFont(new Font(textArea.getFont().getFontName(), Font.ITALIC, textArea.getFont().getSize()));
        });
        group.add(italic);
        box.add(italic);

        this.getContentPane().add(box);
    }

    private void initFontSizeSlider() {
        Box box = new Box(BoxLayout.X_AXIS);
        box.add(Box.createRigidArea(new Dimension(10, 0)));
        box.add(new JLabel("Font Size: "));
        box.add(Box.createRigidArea(new Dimension(10, 0)));

        JSlider slider = new JSlider(6, 48, 14);
        slider.addChangeListener(e -> {
            textArea.setFont(new Font(textArea.getFont().getFontName(), Font.ITALIC, slider.getValue()));
        });

        box.add(slider);
        this.getContentPane().add(box);
    }

    private void initTextArea() {
        textArea = new JTextArea("Lorem ipsum");
        textArea.setFont(new Font((String) comboBox.getSelectedItem(), textArea.getFont().getStyle(), 15));
        this.getContentPane().add(textArea);
    }

    private void setNimbusLAF() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        new TextAreaWindow("TextArea Window");
    }
}
