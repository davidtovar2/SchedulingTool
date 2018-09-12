import javax.swing.JFrame;

public class SchedulingTool {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Simple Mockup Design");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SchedulingToolPanel panel = new SchedulingToolPanel();
		frame.setResizable(true);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);

	}

}
