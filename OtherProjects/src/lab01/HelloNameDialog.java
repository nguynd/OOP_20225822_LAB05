package lab01;
import javax.swing.JOptionPane;
public class HelloNameDialog{
	public static void main(String[] args){
		String result;
		result = JOptionPane.showInputDialog("Dien ten:");
		JOptionPane.showMessageDialog(null, "Hi" + result);
	System.exit(0);
	}
}