package timeric.viergwinnt.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.swing.JDialog;
import javax.swing.JEditorPane;

public class RulesDialog {

	public static void openRulesDialog() {
		
		JDialog rulesDialog = new JDialog();
		rulesDialog.setModal(true);
		rulesDialog.setTitle("Spielregeln");
		rulesDialog.setSize(1000, 700);
		rulesDialog.setLocationByPlatform(true);

		JEditorPane textArea;
		try {
			textArea = new JEditorPane();
			textArea.setEditable(false);
			textArea.setContentType("text/html");
			textArea.setText(loadRules());

		} catch (IOException e) {
			throw new IllegalStateException("Regeldatei fehlt", e);
		}
		rulesDialog.setContentPane(textArea);

		rulesDialog.setVisible(true);
		
	}

	private static String loadRules() throws IOException {
		InputStream is = RulesDialog.class.getClassLoader().getResourceAsStream("regeln.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String rulesTxt = reader.lines().collect(Collectors.joining(System.lineSeparator()));

		reader.close();
		is.close();

		return rulesTxt;
	}

}
