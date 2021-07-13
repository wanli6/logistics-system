package ui;

/**
 * @author 32635
 */
public class App {
    public static Login login;
    public static void main(String[] args) {
        new App();
    }
    public static Login getLogin(){
        return login;
    }
    public App(){
        login = new Login();
    }
}