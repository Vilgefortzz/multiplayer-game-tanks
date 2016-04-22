/*
 * Copyright (c) 2016.
 * @gklimek
 */

package main.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame implements ActionListener, FrontEnd{

    // Rozmiar okna

    public static final int sizeX = 1366;
    public static final int sizeY = 768;

    // Panele 1) menu główne + poboczne 2) właściwa gra

    private MenuPanel menuPanel;
    private GamePanel gamePanel;

    // przyciski do menu głównego (widok dla niezalogowanego użytkownika)

    private JLabel title;
    private JLabel author;
    private JButton signInBtn;
    private JButton creditsBtn;
    private JButton exitBtn;

    // przyciski do menu pobocznego (logowanie)

    private JLabel logIn;
    private JLabel loginWriteLog;
    private JTextField loginLog;
    private JLabel passwordWriteLog;
    private JPasswordField passwordLog;
    private JButton loginBtn;
    private JLabel registrationInfo;
    private JButton goToRegistration;
    private JButton backBtn1;

    // przyciski do menu pobocznego (rejestracja)

    private JLabel registration;
    private JLabel loginWriteReg;
    private JTextField loginReg;
    private JLabel passwordWriteReg;
    private JPasswordField passwordReg;
    private JButton createAccountBtn;
    private JButton backBtn2;

    // przyciski do menu pobocznego (zalogowany użytkownik)

    private JLabel loggedAs;
    private JButton playBtn;
    private JButton statsBtn;
    private JButton helpBtn;
    private JButton logOutBtn;

    // przyciski do menu pobocznego (statystyki, wyniki)

    private JLabel rank;
    private JButton backBtn3;

    // przyciski do menu pobocznego (sterowanie)

    private JLabel helpText;
    private JButton backBtn4;

    // przyciski do menu pobocznego (credits)

    private JLabel creditsInfo;
    private JButton backBtn5;

    // Boxy czyli odpowiednie sekcje w menu

    private Box boxMenu;
    private Box boxCredits;
    private Box boxLogging;
    private Box boxSignUp;
    private Box boxLoggedUser;
    private Box boxStats;
    private Box boxHelp;

    public MainFrame(){

        setLookAndFeel("Nimbus"); // wygląd przycisków
        init();

        // Ustawienie ikonki aplikacji

        try {
            setIconImage(ImageIO.read(new File("res\\tank_icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {

        // Stworzenie odpowiednich boxów dla wszystkich sekcji

        boxMenu = createMenu();
        boxCredits = createCreditsMenu();
        boxLogging = createLoggingMenu();
        boxSignUp = createSignUpMenu();
        boxLoggedUser = createLoggedUserMenu();
        boxStats = createStatsMenu();
        boxHelp = createHelpMenu();

        // Dodanie akcji do buttonów

        signInBtn.addActionListener(this);
        creditsBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        loginBtn.addActionListener(this);
        goToRegistration.addActionListener(this);
        backBtn1.addActionListener(this);

        createAccountBtn.addActionListener(this);
        backBtn2.addActionListener(this);

        playBtn.addActionListener(this);
        statsBtn.addActionListener(this);
        helpBtn.addActionListener(this);
        logOutBtn.addActionListener(this);

        backBtn3.addActionListener(this);

        backBtn4.addActionListener(this);

        backBtn5.addActionListener(this);

        // Rozpoczęcie (menu główne)

        menuPanel = new MenuPanel();
        menuPanel.add(boxMenu);
        add(menuPanel);
    }

    // Ta metoda ustawia wygląd przycisków oraz innych elementów

    private void setLookAndFeel(String lookAndFeel) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (lookAndFeel.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Box createMenu() {
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(20)); // przerwa między elementami w boxie

        // Stworzenie napisu głównego

        title = new JLabel("TANKS - MULTIPLAYER");
        title.setFont(new Font("Courier New", Font.BOLD, 100));
        title.setForeground(Color.WHITE);
        box.add(title);

        // Dodanie autora

        box.add(Box.createVerticalStrut(-16));

        author = new JLabel("Made by Grzegorz Klimek");
        author.setFont(new Font("Helvetica", Font.BOLD, 9));
        author.setForeground(new Color(214, 208, 101));
        box.add(author);

        // Tworzenie buttonów

        box.add(Box.createVerticalStrut(20));

        signInBtn = new JButton("SIGN IN");
        signInBtn.setForeground(Color.WHITE);
        signInBtn.setBackground(Color.BLACK);
        signInBtn.setFont(new Font("Arial", Font.PLAIN, 50));
        box.add(signInBtn);

        box.add(Box.createVerticalStrut(6));

        creditsBtn = new JButton("CREDITS");
        creditsBtn.setForeground(Color.WHITE);
        creditsBtn.setBackground(Color.BLACK);
        creditsBtn.setFont(new Font("Arial", Font.PLAIN, 50));
        box.add(creditsBtn);

        box.add(Box.createVerticalStrut(6));

        exitBtn = new JButton("EXIT");
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setBackground(Color.BLACK);
        exitBtn.setFont(new Font("Arial", Font.PLAIN, 50));
        box.add(exitBtn);

        return box;
    }

    private Box createCreditsMenu(){

        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(20));

        creditsInfo = new JLabel("<html>This is a project for java.<br><br>The goal is to create a game" +
                "Tanks with the possibility of playing by many players,<br>gathering stats and much more." +
                "<br>Everything based on client-server architecture and with the bases connection." +
                "<br><br><br><br><br>Have fun!," +
                "<br>@gklimek</html>");
        creditsInfo.setFont(new Font("Courier New", Font.BOLD, 26));
        creditsInfo.setForeground(Color.WHITE);
        box.add(creditsInfo);

        box.add(Box.createVerticalStrut(6));

        backBtn5 = backButton();
        box.add(backBtn5);

        return box;
    }

    private Box createLoggingMenu(){

        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(20));

        logIn = new JLabel("LOG IN");
        logIn.setForeground(Color.WHITE);
        logIn.setFont(new Font("Courier New", Font.BOLD, 75));
        box.add(logIn);

        box.add(Box.createVerticalStrut(10));
        loginWriteLog = new JLabel("Login:");
        loginWriteLog.setForeground(Color.WHITE);
        loginWriteLog.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(loginWriteLog);

        box.add(Box.createVerticalStrut(0));
        loginLog = new JTextField();
        loginLog.setToolTipText("Write login of your account");
        loginLog.setMaximumSize(new Dimension(300, 30));
        box.add(loginLog);

        box.add(Box.createVerticalStrut(15));
        passwordWriteLog = new JLabel("Password:");
        passwordWriteLog.setForeground(Color.WHITE);
        passwordWriteLog.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(passwordWriteLog);

        box.add(Box.createVerticalStrut(0));
        passwordLog = new JPasswordField();
        passwordLog.setToolTipText("Write your password connected to your account");
        passwordLog.setMaximumSize(new Dimension(300, 30));
        box.add(passwordLog);

        box.add(Box.createVerticalStrut(10));
        loginBtn = new JButton("Sign in");
        loginBtn.setForeground(Color.BLACK);
        loginBtn.setBackground(Color.WHITE);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 20));
        box.add(loginBtn);

        box.add(Box.createVerticalStrut(20));
        registrationInfo = new JLabel("You don't have an account? Click button below ->");
        registrationInfo.setForeground(new Color(143, 226, 217));
        registrationInfo.setFont(new Font("Courier New", Font.BOLD, 14));
        box.add(registrationInfo);

        box.add(Box.createVerticalStrut(10));
        goToRegistration = new JButton("Create an account");
        goToRegistration.setForeground(Color.WHITE);
        goToRegistration.setBackground(Color.BLACK);
        goToRegistration.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(goToRegistration);

        box.add(Box.createVerticalStrut(70));
        backBtn1 = backButton();
        box.add(backBtn1);

        return box;

    }

    private Box createSignUpMenu(){
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(20));

        registration = new JLabel("REGISTRATION");
        registration.setForeground(Color.WHITE);
        registration.setFont(new Font("Courier New", Font.BOLD, 75));
        box.add(registration);

        box.add(Box.createVerticalStrut(10));
        loginWriteReg = new JLabel("Login:");
        loginWriteReg.setForeground(Color.WHITE);
        loginWriteReg.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(loginWriteReg);

        box.add(Box.createVerticalStrut(0));
        loginReg = new JTextField();
        loginReg.setEditable(true);
        loginReg.setToolTipText("Image login just as you wish");
        loginReg.setMaximumSize(new Dimension(300, 30));

        // TODO Sprawdzanie czy nie ma już użytkownika o danym loginie, jeżeli tak to odpowiedni komunikat

        box.add(loginReg);

        box.add(Box.createVerticalStrut(15));
        passwordWriteReg = new JLabel("Password:");
        passwordWriteReg.setForeground(Color.WHITE);
        passwordWriteReg.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(passwordWriteReg);

        box.add(Box.createVerticalStrut(0));
        passwordReg = new JPasswordField();
        passwordReg.setEditable(true);
        passwordReg.setToolTipText("Image your password - try choose not easy one");
        passwordReg.setMaximumSize(new Dimension(300, 30));
        box.add(passwordReg);

        box.add(Box.createVerticalStrut(10));
        createAccountBtn = new JButton("Sign up");
        createAccountBtn.setForeground(Color.BLACK);
        createAccountBtn.setBackground(Color.WHITE);
        createAccountBtn.setFont(new Font("Arial", Font.BOLD, 20));
        box.add(createAccountBtn);

        box.add(Box.createVerticalStrut(70));
        backBtn2 = backButton();
        box.add(backBtn2);

        return box;
    }

    private Box createLoggedUserMenu(){
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(20));

        loggedAs = new JLabel("Logged as: ");
        // TODO Z bazy danych będzie pobrana informacja o nazwie użytkownika [będzie to realizowane w momencie zalogowania - nie tutaj]
        loggedAs.setForeground(Color.WHITE);
        loggedAs.setFont(new Font("Courier New", Font.BOLD, 75));
        box.add(loggedAs);

        box.add(Box.createVerticalStrut(6));

        playBtn = new JButton("Play");
        playBtn.setForeground(Color.WHITE);
        playBtn.setBackground(Color.BLACK);
        playBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        box.add(playBtn);

        box.add(Box.createVerticalStrut(6));

        statsBtn = new JButton("Stats");
        statsBtn.setForeground(Color.WHITE);
        statsBtn.setBackground(Color.BLACK);
        statsBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        box.add(statsBtn);

        box.add(Box.createVerticalStrut(6));
        helpBtn = new JButton("Help");
        helpBtn.setForeground(Color.WHITE);
        helpBtn.setBackground(Color.BLACK);
        helpBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        box.add(helpBtn);

        box.add(Box.createVerticalStrut(6));
        logOutBtn = new JButton("Log out"); // wylogowanie się
        logOutBtn.setForeground(Color.BLACK);
        logOutBtn.setBackground(Color.WHITE);
        logOutBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        box.add(logOutBtn);

        return box;
    }

    private Box createStatsMenu(){
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(20));

        rank = new JLabel("RANK");
        rank.setForeground(Color.WHITE);
        rank.setFont(new Font("Courier New", Font.BOLD, 75));
        box.add(rank);

        box.add(Box.createVerticalStrut(6));

        backBtn3 = backButton();
        box.add(backBtn3);

        return box;
    }

    private Box createHelpMenu(){
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(20));

        helpText = new JLabel("<html>You are driving a tank. Your goal is to eliminate " +
                "other tanks.<br><br>1) Sign up entering your login and password" +
                "<br><br>2) After registration sign in" +
                "<br><br>3) Now you can join to the game<br><br>Your stats are remembered.<br><br>To move use:<br><br>W - up<br>A - left" +
                "<br>S - down<br>D - right" + "<br><br>L - Attack<br><br>Gl and Hf</html>");
        helpText.setFont(new Font("Courier New", Font.BOLD, 26));
        helpText.setForeground(Color.WHITE);
        box.add(helpText);

        box.add(Box.createVerticalStrut(6));

        backBtn4 = backButton();
        box.add(backBtn4);

        return box;
    }

    private JButton backButton(){
        JButton backBtn = new JButton("Back");
        backBtn.setForeground(Color.WHITE);
        backBtn.setBackground(Color.BLACK);
        backBtn.setFont(new Font("Arial", Font.PLAIN, 10));
        return  backBtn;
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if (e.getSource() == signInBtn){

            boxMenu.setVisible(false);
            menuPanel.remove(boxMenu);
            menuPanel.add(boxLogging);
            boxLogging.setVisible(true);
        }

        if (e.getSource() == creditsBtn){

            boxMenu.setVisible(false);
            menuPanel.remove(boxMenu);
            menuPanel.add(boxCredits);
            boxCredits.setVisible(true);
        }

        if (e.getSource() == exitBtn){
            System.exit(0);
        }

        if (e.getSource() == loginBtn){

            JOptionPane.showMessageDialog(null, "Logging completed");

            boxLogging.setVisible(false);
            menuPanel.remove(boxLogging);
            menuPanel.add(boxLoggedUser);
            boxLoggedUser.setVisible(true);
        }

        if (e.getSource() == goToRegistration){

            boxLogging.setVisible(false);
            menuPanel.remove(boxLogging);
            menuPanel.add(boxSignUp);
            boxSignUp.setVisible(true);
        }

        if (e.getSource() == backBtn1){
            boxLogging.setVisible(false);
            menuPanel.remove(boxLogging);
            menuPanel.add(boxMenu);
            boxMenu.setVisible(true);
        }

        if (e.getSource() == createAccountBtn){

            JOptionPane.showMessageDialog(null, "Account succesfully created!");

            boxLogging.setVisible(false);
            menuPanel.remove(boxLogging);
            menuPanel.add(boxSignUp);
            boxSignUp.setVisible(true);
        }

        if (e.getSource() == backBtn2){

            boxSignUp.setVisible(false);
            menuPanel.remove(boxSignUp);
            menuPanel.add(boxLogging);
            boxLogging.setVisible(true);
        }

        if (e.getSource() == playBtn){

            boxLoggedUser.setVisible(false);
            menuPanel.remove(boxLoggedUser);
            menuPanel.setVisible(false);
            remove(menuPanel);

            gamePanel = new GamePanel();
            add(gamePanel);
            gamePanel.requestFocusInWindow();
        }

        if (e.getSource() == statsBtn){

            boxLoggedUser.setVisible(false);
            menuPanel.remove(boxLoggedUser);
            menuPanel.add(boxStats);
            boxStats.setVisible(true);
        }

        if (e.getSource() == helpBtn){

            boxLoggedUser.setVisible(false);
            menuPanel.remove(boxLoggedUser);
            menuPanel.add(boxHelp);
            boxHelp.setVisible(true);
        }

        if (e.getSource() == logOutBtn){

            JOptionPane.showMessageDialog(null, "You are logged out. I hope we will see you soon ;)");

            boxLoggedUser.setVisible(false);
            menuPanel.remove(boxLoggedUser);
            menuPanel.add(boxMenu);
            boxMenu.setVisible(true);
        }

        if (e.getSource() == backBtn3){

            boxStats.setVisible(false);
            menuPanel.remove(boxStats);
            menuPanel.add(boxLoggedUser);
            boxLoggedUser.setVisible(true);
        }

        if (e.getSource() == backBtn4){

            boxHelp.setVisible(false);
            menuPanel.remove(boxHelp);
            menuPanel.add(boxLoggedUser);
            boxLoggedUser.setVisible(true);
        }

        if (e.getSource() == backBtn5){

            boxCredits.setVisible(false);
            menuPanel.remove(boxCredits);
            menuPanel.add(boxMenu);
            boxMenu.setVisible(true);
        }
    }

    @Override
    public boolean registerPlayer(int id, String name) {

        return this.gamePanel.registerPlayer(id, name);
    }

    @Override
    public void deregisterPlayer(int id) {

        this.gamePanel.deregisterPlayer(id);
    }

    @Override
    public void movePlayer(int id, int x, int y) {

        this.gamePanel.movePlayer(id, x, y);
    }

    @Override
    public void clearPlayers() {

        this.gamePanel.clearPlayers();
    }
}