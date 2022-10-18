import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperatorInputForm {
    private JPanel MainPanel;
    private JComboBox numDistractorsInput;

    private SubjectInfo subject;
    private JButton startButton;
    private TrialPanel trialPanel;
    private JPanel trialPanelContainer;
    private JTextField subjectIDTextField;
    private JComboBox NumDistractorsComboBox;
    private JComboBox trialTypeComboBox;
    private JPanel ControlPanel;
    private JPanel subjectIDPanel;
    private JPanel trialTypePanel;
    private JPanel numDistractorsPanel;
    private JLabel subjectIDlabel;
    private JLabel trialTypeLabel;
    private JLabel numDistractorsLabel;
    private JPanel startButtonPanel;
    private JLabel correctLabel;
    private JLabel incorrectLabel;
    private JLabel correctScoreLabel;
    private JLabel incorrectScoreLabel;
    private JSlider preferredInspectionTime;
    private JPanel preferredInspectionTimePanel;
    private JLabel preferredInspectionTimeInfoLabel;
    private JLabel preferredInspectionTimeDisplayLabel;

    private int interval;

    private Timer timer;

    public OperatorInputForm() {
        interval = preferredInspectionTime.getValue();
        preferredInspectionTimeDisplayLabel.setText(String.valueOf(interval));
        subject = new SubjectInfo();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (subjectIDTextField.getText().equals("")) {
                    int input = JOptionPane.showConfirmDialog(null, "Enter a Subject ID", "Invalid", JOptionPane.CANCEL_OPTION);
                } else {
                    correctLabel.setText("Correct:");
                    correctScoreLabel.setText(String.valueOf(subject.getCorrectGuesses()));
                    incorrectLabel.setText("Incorrect:");
                    incorrectScoreLabel.setText(String.valueOf(subject.getIncorrectGuesses()));
                    subject.setSubjectID(subjectIDTextField.getText());
                    subject.setTrialType(trialTypeComboBox.getSelectedItem().toString());
                    subject.setNumDistractors(Integer.parseInt(NumDistractorsComboBox.getSelectedItem().toString()));
                    if (subject.getTrialType().equals("Color")) {
                        trialPanel.ColorsOnly(subject);
                        timer = new Timer(interval, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                trialPanel.clearDotsNSquares();
                                timer.stop();
                                subject.currentGuess.setTrialType("Color Only");
                                subject.currentGuess.setInspectionTime(interval);
                                feedback("red circle present?");
                            }
                        });
                        timer.start();
                    } else if (subject.getTrialType().equals("Shape")) {
                        trialPanel.ShapesOnly(subject);
                        timer = new Timer(interval, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                trialPanel.clearDotsNSquares();
                                timer.stop();
                                subject.currentGuess.setTrialType("Shape Only");
                                subject.currentGuess.setInspectionTime(interval);
                                feedback("red square present?");
                            }
                        });
                        timer.start();
                    } else if (subject.getTrialType().equals("Combo")) {
                        trialPanel.ShapesAndColors(subject);
                        timer = new Timer(interval, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                trialPanel.clearDotsNSquares();
                                timer.stop();
                                subject.currentGuess.setTrialType("Combo");
                                subject.currentGuess.setInspectionTime(interval);
                                feedback("red circle present?");

                            }
                        });
                        timer.start();
                    }
                }
            }
        });

        preferredInspectionTime.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                interval = preferredInspectionTime.getValue();
                preferredInspectionTimeDisplayLabel.setText(String.valueOf(interval));
            }
        });
    }

    private void feedback(String message){
        boolean hasUnique = trialPanel.HasUnique();
        subject.incrementNumGuesses();
        subject.currentGuess.setInspectionTime(interval);
        int input = JOptionPane.showConfirmDialog(null,
                "Was there a " + message, "Select and Option...", JOptionPane.YES_NO_OPTION);
        if(hasUnique){
            if(input == JOptionPane.YES_OPTION){
                subject.setCorrectGuesses(subject.getCorrectGuesses()+1);
                subject.currentGuess.setCorrectAndUnique(true,true);
            }
            else{
                subject.setIncorrectGuesses(subject.getIncorrectGuesses()+1);
                subject.currentGuess.setCorrectAndUnique(false,true);
                interval +=25;
                preferredInspectionTimeDisplayLabel.setText(String.valueOf(interval));
            }
        }
        else{
            if(input == JOptionPane.YES_OPTION){
                subject.setIncorrectGuesses(subject.getIncorrectGuesses()+1);
                subject.currentGuess.setCorrectAndUnique(false,false);
                interval+=25;
                preferredInspectionTimeDisplayLabel.setText(String.valueOf(interval));
            }
            else{
                subject.setCorrectGuesses(subject.getCorrectGuesses()+1);
                subject.currentGuess.setCorrectAndUnique(true,false);
            }
        }
        correctScoreLabel.setText(String.valueOf(subject.getCorrectGuesses()));
        incorrectScoreLabel.setText(String.valueOf(subject.getIncorrectGuesses()));
        subject.recordGuess();
        if(subject.getCorrectGuesses() + subject.getIncorrectGuesses() >= 10){
            input = JOptionPane.showConfirmDialog(null, "Trial Complete!", "Trial Complete", JOptionPane.OK_OPTION);
            subject.saveSubjectInfo();
            subjectIDTextField.setText("");
            correctLabel.setText("");
            correctScoreLabel.setText("");
            incorrectLabel.setText("");
            incorrectScoreLabel.setText("");
            subject.resetSubject();
            preferredInspectionTime.setValue(125);
            interval = preferredInspectionTime.getValue();
            preferredInspectionTimeDisplayLabel.setText(String.valueOf(interval));

        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        trialPanel = new TrialPanel();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("OperatorInputForm");
        frame.setContentPane(new OperatorInputForm().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
