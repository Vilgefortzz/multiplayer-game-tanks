/*
 * Copyright (c) 2016.
 * @gklimek
 */

package gui;

import client.Client;
import database.Database;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class MainFrame extends JFrame implements ActionListener{

    // Rozmiar okna

    public static final int sizeX = 1366;
    public static final int sizeY = 768;

    // Klient, który posiada to okno

    private Client client = null;

    // Baza danych

    private Database database = null;

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
    private JLabel passwordWriteLog;
    private JButton loginBtn;
    private JLabel registrationInfo;
    private JButton goToRegistration;
    private JButton backBtn1;

    private JTextField loginLog;
    private JPasswordField passwordLog;

    // przyciski do menu pobocznego (rejestracja)

    private JLabel registration;
    private JLabel loginWriteReg;
    private JLabel passwordWriteReg;
    private JLabel firstNameWriteReg;
    private JLabel lastNameWriteReg;
    private JLabel emailWriteReg;
    private JButton createAccountBtn;
    private JButton backBtn2;

    private JTextField loginReg;
    private JPasswordField passwordReg;
    private JTextField firstNameReg;
    private JTextField lastNameReg;
    private JTextField emailReg;

    // przyciski do menu pobocznego (zalogowany użytkownik)

    private JLabel playroom;
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
    private Box boxLogIn;
    private Box boxSignUp;
    public  Box boxLoggedUser;
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
        boxLogIn = createLogInMenu();
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

        // Stworzenie paneli z menu oraz grą

        menuPanel = new MenuPanel();
        menuPanel.add(boxMenu);
        gamePanel = new GamePanel(this, menuPanel);

        // Rozpoczęcie (menu główne)

        add(menuPanel);

        // Stworzenie instancji bazy danych

        database = new Database();

        // Połączenie z bazą danych

        try {
            database.connectToDatabase();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        // Zczytanie informacji z bazy danych (zapisanie do mapy userów z bazy)

        try {
            database.fillMapFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
                "<br>Everything based on client-server structure and with the databases connections." +
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

    private Box createLogInMenu(){

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
        loginLog.setMaximumSize(new Dimension(300, 30));
        box.add(loginLog);

        box.add(Box.createVerticalStrut(15));
        passwordWriteLog = new JLabel("Password:");
        passwordWriteLog.setForeground(Color.WHITE);
        passwordWriteLog.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(passwordWriteLog);

        box.add(Box.createVerticalStrut(0));
        passwordLog = new JPasswordField();
        passwordLog.setMaximumSize(new Dimension(300, 30));
        box.add(passwordLog);

        box.add(Box.createVerticalStrut(10));
        loginBtn = new JButton("Sign in");
        loginBtn.setForeground(Color.BLACK);
        loginBtn.setBackground(Color.WHITE);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 20));
        box.add(loginBtn);

        box.add(Box.createVerticalStrut(20));
        registrationInfo = new JLabel("<html>You don't have an account yet?" +
                "<br> Don't think twice,<br>click the button below -></html>");
        registrationInfo.setForeground(new Color(246, 236, 218));
        registrationInfo.setFont(new Font("Courier New", Font.BOLD, 20));
        box.add(registrationInfo);

        box.add(Box.createVerticalStrut(10));
        goToRegistration = new JButton("Create an account for FREE");
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
        loginReg.setMaximumSize(new Dimension(300, 30));
        box.add(loginReg);

        box.add(Box.createVerticalStrut(15));
        passwordWriteReg = new JLabel("Password:");
        passwordWriteReg.setForeground(Color.WHITE);
        passwordWriteReg.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(passwordWriteReg);

        box.add(Box.createVerticalStrut(0));
        passwordReg = new JPasswordField();
        passwordReg.setEditable(true);
        passwordReg.setMaximumSize(new Dimension(300, 30));
        box.add(passwordReg);

        box.add(Box.createVerticalStrut(15));
        firstNameWriteReg = new JLabel("First name:");
        firstNameWriteReg.setForeground(Color.WHITE);
        firstNameWriteReg.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(firstNameWriteReg);

        box.add(Box.createVerticalStrut(0));
        firstNameReg = new JTextField();
        firstNameReg.setEditable(true);
        firstNameReg.setMaximumSize(new Dimension(300, 30));
        box.add(firstNameReg);

        box.add(Box.createVerticalStrut(15));
        lastNameWriteReg = new JLabel("Last name:");
        lastNameWriteReg.setForeground(Color.WHITE);
        lastNameWriteReg.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(lastNameWriteReg);

        box.add(Box.createVerticalStrut(0));
        lastNameReg = new JTextField();
        lastNameReg.setEditable(true);
        lastNameReg.setMaximumSize(new Dimension(300, 30));
        box.add(lastNameReg);

        box.add(Box.createVerticalStrut(15));
        emailWriteReg = new JLabel("Email:");
        emailWriteReg.setForeground(Color.WHITE);
        emailWriteReg.setFont(new Font("Arial", Font.BOLD, 15));
        box.add(emailWriteReg);

        box.add(Box.createVerticalStrut(0));
        emailReg = new JTextField();
        emailReg.setEditable(true);
        emailReg.setMaximumSize(new Dimension(300, 30));
        box.add(emailReg);

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

        playroom = new JLabel("Playroom");
        playroom.setForeground(new Color(86, 192, 46));
        playroom.setFont(new Font("Comic Sans MS", Font.BOLD, 80));
        box.add(playroom);

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
            menuPanel.add(boxLogIn);
            boxLogIn.setVisible(true);
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

            if (database.loginUser(loginLog.getText(), String.valueOf(passwordLog.getPassword()))){

                JOptionPane.showMessageDialog(null, "You are successfully logged in", "", JOptionPane.INFORMATION_MESSAGE);
                setTitle("Client logged as: " + loginLog.getText());

                boxLogIn.setVisible(false);
                menuPanel.remove(boxLogIn);
                menuPanel.add(boxLoggedUser);
                boxLoggedUser.setVisible(true);

                loginLog.setText("");
                passwordLog.setText("");
            }
            else
                JOptionPane.showMessageDialog(null, "Incorrect login or password or you haven't yet a free account. Go to registration to create account or try to log in with proper data", "Log in failed", JOptionPane.ERROR_MESSAGE);
        }

        if (e.getSource() == goToRegistration){

            boxLogIn.setVisible(false);
            menuPanel.remove(boxLogIn);
            menuPanel.add(boxSignUp);
            boxSignUp.setVisible(true);

            loginLog.setText("");
            passwordLog.setText("");
        }

        if (e.getSource() == backBtn1){
            boxLogIn.setVisible(false);
            menuPanel.remove(boxLogIn);
            menuPanel.add(boxMenu);
            boxMenu.setVisible(true);
        }

        if (e.getSource() == createAccountBtn){

            if (database.registerUser(loginReg.getText(), String.valueOf(passwordReg.getPassword()), firstNameReg.getText(), lastNameReg.getText(), emailReg.getText())){

                JOptionPane.showMessageDialog(null, "Account successfully created!", "", JOptionPane.INFORMATION_MESSAGE);

                boxSignUp.setVisible(false);
                menuPanel.remove(boxSignUp);
                menuPanel.add(boxLogIn);
                boxLogIn.setVisible(true);

                loginReg.setText("");
                passwordReg.setText("");
                firstNameReg.setText("");
                lastNameReg.setText("");
                emailReg.setText("");
            }
            else
                JOptionPane.showMessageDialog(null, "User with this login has already exists! Change login to create account", "Registration failed", JOptionPane.ERROR_MESSAGE);
        }

        if (e.getSource() == backBtn2){

            boxSignUp.setVisible(false);
            menuPanel.remove(boxSignUp);
            menuPanel.add(boxLogIn);
            boxLogIn.setVisible(true);
        }

        /* --------------------------------------------------------------------------------------------------------- */
        /* --------------------------------------------------------------------------------------------------------- */
        /* ---------------------------------Lączenie klienta z serverem--------------------------------------------- */
        /* --------------------------------------------------------------------------------------------------------- */
        /* --------------------------------------------------------------------------------------------------------- */

        if (e.getSource() == playBtn){

            // Stworzenie klienta - socketa do komunikacji

            final String HOST = "localhost";
            final int PORT = 8080;

            try {

                client.connect(HOST, PORT);

            } catch (IOException ex) {

                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
            }

            if (client.isConnected()){

                boxLoggedUser.setVisible(false);
                menuPanel.remove(boxLoggedUser);
                menuPanel.setVisible(false);
                remove(menuPanel);

                // Widok: panel z grą

                gamePanel.setVisible(true);
                add(gamePanel);
                gamePanel.requestFocusInWindow();

                gamePanel.runRepaintingThread(); // ustawienie flagi animating na true + uruchomienie wątku rysującego świat
            }
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

            JOptionPane.showMessageDialog(null, "You are succesfully logged out. I hope we will see you soon ;)", "", JOptionPane.INFORMATION_MESSAGE);
            setTitle("Client not logged now");

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
}