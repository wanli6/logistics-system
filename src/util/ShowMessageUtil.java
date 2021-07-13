package util;

import javax.swing.*;

public class ShowMessageUtil {
    public static void Message(String s){
        JOptionPane.showMessageDialog(null,s,"提示",JOptionPane.INFORMATION_MESSAGE);
    }
}