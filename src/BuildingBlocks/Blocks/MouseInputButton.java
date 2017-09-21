/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;

import BuildingBlocks.Master.BlockGraphic;
import javafx.scene.control.Button;
import BuildingBlocks.Master.ContactPoint;
import BuildingBlocks.Master.Input;
import BuildingBlocks.Master.LogicBlock;
import BuildingBlocks.Master.Output;
import BuildingBlocks.Master.util.CreationUtil;
import BuildingBlocks.Master.util.Dialogs;
import java.util.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseButton;


/**
 *
 * @author Ricardo
 */
public class MouseInputButton extends BlockGraphic {

    private boolean isImpuls = true;

    public MouseInputButton () {
        super("Button", "Click for impulse", CreationUtil.createOutput(ContactPoint.Datatype.BOOLEAN), Type.VARIABLE);
        createPressModeChangeDialog();
    }

    @Override
    protected void setOnMousePressedEvent (MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (isImpuls) {
                getOutputs().get(0).setOutput(true);
            } else {
                getOutputs().get(0).setOutput(!(boolean)getOutputs().get(0).getOutput());
            }
        }
    }

    @Override
    protected void setOnMouseReleasedEvent (MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (isImpuls) {
                getOutputs().get(0).setOutput(false);
            }
        }
    }

    @Override
    public void update (Observable o, Object arg) {

    }

    private void createPressModeChangeDialog () {
        Group modeChangeGroup = new Group();
        Label pressMode = new Label("Drückmodus");
        ComboBox comboBox = new ComboBox<Type>();
        comboBox.getItems().add("Impuls");
        comboBox.getItems().add("Umschaltung");
        if (isImpuls) {
            comboBox.getSelectionModel().select(0);
        } else {
            comboBox.getSelectionModel().select(1);
        }

        Button changeMode = new Button("Übernehmen");
        changeMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                try {
                    switch ((String) comboBox.getSelectionModel().getSelectedItem()) {
                        case "Impuls":
                            isImpuls = true;
                            break;
                        case "Umschaltung":
                            isImpuls = false;
                            break;
                        default:
                            Dialogs.alertDialog("Fehler", "Eingabefehler", "Keine Funktion für folgenden Wert gefunden: " + (String) comboBox.getSelectionModel().getSelectedItem());
                    }

                } catch (Exception e) {
                    isImpuls = true;
                }
            }
        });
        
        comboBox.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                comboBox.requestFocus();
            }
        });
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(pressMode, 0, 0);
        grid.add(comboBox, 0, 1);
        grid.add(changeMode, 1, 1);
        modeChangeGroup.getChildren().add(grid);
        addDialogFunction(modeChangeGroup);
    }

}
