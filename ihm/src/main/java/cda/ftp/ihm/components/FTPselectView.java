package cda.ftp.ihm.components;

import java.util.List;

import cda.ftp.client.logic.FTPinterface;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FTPselectView extends ScrollPane {

	public TilePane tilePane;
	
	private final CornerRadii bgRadius = new CornerRadii(3);

	private final Background hoverBackground = new Background(new BackgroundFill(Color.LIGHTSKYBLUE.interpolate(Color.WHITE, 0.7), bgRadius, Insets.EMPTY));
	private final Background focusBackground = new Background(new BackgroundFill(Color.LIGHTSKYBLUE, bgRadius, Insets.EMPTY));
	private final Background unfocusBackground = new Background(new BackgroundFill(Color.TRANSPARENT, bgRadius, Insets.EMPTY));

	public FTPselectView() {
		super();
		generateView();
		addIcons();
	}

	private void generateView() {
		tilePane = new TilePane();

		this.setContent(tilePane);
		this.setPadding(new Insets(8, 16, 16, 32));
		this.fitToWidthProperty().set(true);
		this.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
		VBox.setVgrow(this, Priority.ALWAYS);


		tilePane.setHgap(16);
		tilePane.setVgap(16);

		tilePane.setTileAlignment(Pos.TOP_LEFT);
	}

	public void updateView() {
		clearIcons();
		addIcons();
	}

	private void addIcons() {
		List<FTPiconView> icons = FTPinterface.ls();
		icons.forEach(this.tilePane.getChildren()::add);
		boxifyVBoxes();
	}

	private void clearIcons() {
		this.tilePane.getChildren().clear();
	}

	private void boxifyVBoxes() {
		for(Node child : tilePane.getChildren()) {
			FTPiconView icon = (FTPiconView) child;
			// when vbox is clicked focus on it
			icon.setOnMouseClicked(e -> {
				FTPinterface.currentFile = "";
				if(icon.isFocused() && icon.isDir()) {
					accessDir(icon);
				} else if(!icon.isFocused()) {
					icon.requestFocus();
					FTPactionView.setName(icon.getName());
					if(icon.isDir()) {
						FTPinterface.currentFile = "";
						FTPactionView.setFolder();
					}
					else {
						FTPinterface.currentFile = icon.getName();
						FTPinterface.currentSize = icon.getSize();
						FTPactionView.setSize(icon.getSize());
					}
				}
			});

			// use different backgrounds for hover, focused and unfocused states
			icon.backgroundProperty().bind(Bindings
					.when(icon.focusedProperty())
					.then(focusBackground)
					.otherwise(Bindings
							.when(icon.hoverProperty())
							.then(hoverBackground)
							.otherwise(unfocusBackground))
					);
		}
	}

	private void accessDir(FTPiconView icon) {
		FTPinterface.cd(icon.getName());
	}

	/* to desactivate the focus on the app, really hacky */
	public void requestFocus() { }
}

