package seedu.address.logic.commands;

import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showFirstPersonOnly;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getUnsortedAddressAddressBook;
import static seedu.address.testutil.TypicalPersons.getUnsortedEmailAddressBook;
import static seedu.address.testutil.TypicalPersons.getUnsortedNameAddressBook;
import static seedu.address.testutil.TypicalPersons.getUnsortedPhoneAddressBook;
import static seedu.address.testutil.TypicalPersons.getUnsortedRemarkAddressBook;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.StorageStub;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
public class SortCommandTest {

    private Model model;
    private Model expectedModel;
    private Model unsortedNameModel;
    private Model unsortedPhoneModel;
    private Model unsortedEmailModel;
    private Model unsortedAddressModel;
    private Model unsortedRemarkModel;
    private SortCommand sortCommand;

    private int sortOption;

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        unsortedNameModel = new ModelManager(getUnsortedNameAddressBook(), new UserPrefs());
        unsortedPhoneModel = new ModelManager(getUnsortedPhoneAddressBook(), new UserPrefs());
        unsortedEmailModel = new ModelManager(getUnsortedEmailAddressBook(), new UserPrefs());
        unsortedAddressModel = new ModelManager(getUnsortedAddressAddressBook(), new UserPrefs());
        unsortedRemarkModel = new ModelManager(getUnsortedRemarkAddressBook(), new UserPrefs());

        sortOption = 0;
        sortCommand = new SortCommand(sortOption);
        sortCommand.setData(model, new CommandHistory(), new UndoRedoStack(), new StorageStub());
    }

    @Test
    public void execute_sortListIsNotFiltered_showsSameSortList() {
        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_sortListIsFiltered_showsEverything() {
        showFirstPersonOnly(model);
        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        // sort by name success -> returns true
        unsortedNameModel.sortPersons(0);
        assertTrue(expectedModel.equals(unsortedNameModel));

        // sort by phone success -> returns true
        unsortedPhoneModel.sortPersons(1);
        assertTrue(expectedModel.equals(unsortedPhoneModel));

        // sort by email success -> returns true
        unsortedEmailModel.sortPersons(2);
        assertTrue(expectedModel.equals(unsortedEmailModel));

        // sort by address success -> returns true
        unsortedAddressModel.sortPersons(3);
        assertTrue(expectedModel.equals(unsortedAddressModel));

        // sort by remark success -> returns true
        unsortedRemarkModel.sortPersons(4);
        assertTrue(expectedModel.equals(unsortedRemarkModel));
    }
}
