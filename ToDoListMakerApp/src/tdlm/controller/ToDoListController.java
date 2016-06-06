package tdlm.controller;

import javafx.scene.control.TableView;
import properties_manager.PropertiesManager;
import tdlm.gui.Workspace;
import saf.AppTemplate;
import saf.ui.AppYesNoCancelDialogSingleton;
import static saf.ui.AppYesNoCancelDialogSingleton.YES;
import tdlm.gui.InputItemDialogSingleton;
import static tdlm.PropertyType.ADD_ITEM_MESSAGE;
import static tdlm.PropertyType.ADD_ITEM_TITLE;
import static tdlm.PropertyType.REMOVE_ITEM_MESSAGE;
import static tdlm.PropertyType.REMOVE_ITEM_TITLE;
import tdlm.data.DataManager;
import tdlm.data.ToDoItem;
import tdlm.gui.RemoveItemDialogSingleton;
/**
 * This class responds to interactions with todo list editing controls.
 * 
 * @author McKillaGorilla
 * @version 1.0
 */
public class ToDoListController {
    AppTemplate app;
    
    // WE WANT TO KEEP TRACK OF ITEMS IN TABLE
    int size;
    
    public ToDoListController(AppTemplate initApp) {
        // NOTHING YET
        size = 0;
	app = initApp;
    }

    public void processAddItem() {
        // ENABLE/DISABLE THE PROPER BUTTONS
	Workspace workspace = (Workspace)app.getWorkspaceComponent();
	workspace.reloadWorkspace();
        
        InputItemDialogSingleton dialog = workspace.getInputItemDialog();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        // SHOW ADD ITEM PROMPT
        dialog.show(props.getProperty(ADD_ITEM_TITLE),props.getProperty(ADD_ITEM_MESSAGE));
        
        // ADD NEW ITEM DATA WITH USER INPUT 
        ToDoItem newItem = dialog.getItem();
        DataManager dataManager = (DataManager)app.getDataComponent();
        if (newItem != null) dataManager.addItem(newItem);
        
        // UPDATE THE TABLE AND SIZE
        TableView<ToDoItem> itemsTable = workspace.getItemsTable();
        itemsTable.setItems(dataManager.getItems());
        size = dataManager.getItems().size();
        
        // ENABLE AND DISABLE APPROPRIATE CONTROLS
        workspace.updateTableControls(size);
    }

    public void processRemoveItem() {
         // ENABLE/DISABLE THE PROPER BUTTONS
	Workspace workspace = (Workspace)app.getWorkspaceComponent();
	workspace.reloadWorkspace();
        
        RemoveItemDialogSingleton dialog = workspace.getRemoveItemDialog();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        // SHOW REMOVE ITEM CONFIRMATION PROMPT
        dialog.show(props.getProperty(REMOVE_ITEM_TITLE),props.getProperty(REMOVE_ITEM_MESSAGE));
        
        // GET SELECTED ITEM TO REMOVE
        DataManager dataManager = (DataManager)app.getDataComponent();
        TableView<ToDoItem> itemsTable = workspace.getItemsTable();
        ToDoItem selectedItem = itemsTable.getSelectionModel().getSelectedItem();
        
        // VERIFY ACTION
        String selection = dialog.getSelection();
        if (selection.equals(YES)) dataManager.removeItem(selectedItem);
        
        // UPDATE THE TABLE AND SIZE
        itemsTable.setItems(dataManager.getItems());
        size = dataManager.getItems().size();
        
        // ENABLE AND DISABLE APPROPRIATE CONTROLS
        workspace.updateTableControls(size);
    }
    //6
    public void processMoveUpItem() {
        
    }
    //7
    public void processMoveDownItem() {
        
    }
    //8
    public void processEditItem() {
        
    }
}
