package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TrefferAusgabe extends VBox {
	private static final DateTimeFormatter GERMAN_DATE = DateTimeFormatter.ofPattern("EEE, dd. MMM yyyy HH:mm:ss 'Uhr'", Locale.GERMAN);
	private static final DateTimeFormatter RSS_DATE = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
	private Text textTitel;
	private Text textDatum;
	private Text rangZiffer;
	private Hyperlink textHyperlink;
	private Button localButton;
	private TextArea textArea;
//	private Text abstand;

	public TrefferAusgabe(int rang, String title, String date, String link) {
		super();
		AnchorPane ap = new AnchorPane();

		rangZiffer = new Text();
		rangZiffer.setText(Integer.toString(rang) + ". ");
		AnchorPane.setTopAnchor(rangZiffer, 10.0);
		AnchorPane.setLeftAnchor(rangZiffer, 10.0);

		textTitel = new Text();
		textTitel.setText(title);
//		textTitel.autosize();
		AnchorPane.setTopAnchor(textTitel, 10.0);
		AnchorPane.setLeftAnchor(textTitel, 25.0);
		AnchorPane.setRightAnchor(textTitel, 120.0);

		textDatum = new Text();
		textDatum.setText(convertToGermanDate(date));
//		textDatum.autosize();
		AnchorPane.setTopAnchor(textDatum, 10.0);
		AnchorPane.setRightAnchor(textDatum, 10.0);

		textHyperlink = new Hyperlink();
		textHyperlink.setText(link);
		AnchorPane.setTopAnchor(textHyperlink, 25.0);
		AnchorPane.setLeftAnchor(textHyperlink, 10.0);
		AnchorPane.setRightAnchor(textHyperlink, 100.0);

		localButton = new Button();
		localButton.setText("Lokal anzeigen");
		localButton.prefWidth(80.0);
		AnchorPane.setTopAnchor(localButton, 25.0);
		AnchorPane.setRightAnchor(localButton, 10.0);

		textArea = new TextArea();
		textArea.setText("Test");
		AnchorPane.setTopAnchor(textArea, 50.0);
		AnchorPane.setLeftAnchor(textArea, 10.0);
		AnchorPane.setRightAnchor(textArea, 10.0);
		AnchorPane.setBottomAnchor(textArea, 10.0);

		// HBox oben = new HBox();
		// oben.resize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
		// oben.getChildren().addAll(rangZiffer, textTitel, textDatum);
		//
		// HBox unten = new HBox();
		// unten.resize(USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
		// unten.getChildren().addAll(textHyperlink, lokalerButton);

		// this.getChildren().addAll(oben, unten, textFeld, abstand);

		ap.getChildren().addAll(rangZiffer, textTitel, textDatum, textHyperlink, localButton, textArea);
		this.getChildren().add(ap);
	}
	
	private static String convertToGermanDate(String date) {
		if (date == null || "".equals(date.trim())) {
			return date;
		}
		
		try {
			LocalDateTime ldt = LocalDateTime.parse(date, RSS_DATE);
			String germanDate = ldt.format(GERMAN_DATE);
			return germanDate;
		} catch (DateTimeParseException e) {
			return date;
		}
	}
	
}
