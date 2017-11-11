package seedu.address.logic.commands;

import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showFirstPersonOnly;
import static seedu.address.testutil.TypicalPersons.getTypicalSortedAddressAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalSortedEmailAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalSortedNameAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalSortedPhoneAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalSortedRemarkAddressBook;
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
//@@author TanYikai
/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
public class SortCommandTest {

    private Model modelName;
    private Model modelPhone;
    private Model modelEmail;
    private Model modelAddress;
    private Model modelRemark;

    private Model expectedModelName;
    private Model expectedModelPhone;
    private Model expectedModelEmail;
    private Model expectedModelAddress;
    private Model expectedModelRemark;

    private Model unsortedNameModel;
    private Model unsortedPhoneModel;
    private Model unsortedEmailModel;
    private Model unsortedAddressModel;
    private Model unsortedRemarkModel;

    private SortCommand sortCommand;

    private int sortOption;

    @Before
    public void setUp() {
        modelName = new ModelManager(getTypicalSortedNameAddressBook(), new UserPrefs());
        modelPhone = new ModelManager(getTypicalSortedPhoneAddressBook(), new UserPrefs());
        modelEmail = new ModelManager(getTypicalSortedEmailAddressBook(), new UserPrefs());
        modelAddress = new ModelManager(getTypicalSortedAddressAddressBook(), new UserPrefs());
        modelRemark = new ModelManager(getTypicalSortedRemarkAddressBook(), new UserPrefs());

        expectedModelName = new ModelManager(modelName.getAddressBook(), new UserPrefs());
        expectedModelPhone = new ModelManager(modelPhone.getAddressBook(), new UserPrefs());
        expectedModelEmail = new ModelManager(modelEmail.getAddressBook(), new UserPrefs());
        expectedModelAddress = new ModelManager(modelAddress.getAddressBook(), new UserPrefs());
        expectedModelRemark = new ModelManager(modelRemark.getAddressBook(), new UserPrefs());

        unsortedNameModel = new ModelManager(getUnsortedNameAddressBook(), new UserPrefs());
        unsortedPhoneModel = new ModelManager(getUnsortedPhoneAddressBook(), new UserPrefs());
        unsortedEmailModel = new ModelManager(getUnsortedEmailAddressBook(), new UserPrefs());
        unsortedAddressModel = new ModelManager(getUnsortedAddressAddressBook(), new UserPrefs());
        unsortedRemarkModel = new ModelManager(getUnsortedRemarkAddressBook(), new UserPrefs());

        sortOption = 0;
        sortCommand = new SortCommand(sortOption);
        sortCommand.setData(modelName, new CommandHistory(), new UndoRedoStack(), new StorageStub());
    }

    @Test
    public void execute_sortListIsNotFiltered_showsSameSortList() {
        assertCommandSuccess(sortCommand, modelName, SortCommand.MESSAGE_SUCCESS, expectedModelName);
    }

    @Test
    public void execute_sortListIsFiltered_showsEverything() {
        showFirstPersonOnly(modelName);
        assertCommandSuccess(sortCommand, modelName, SortCommand.MESSAGE_SUCCESS, expectedModelName);
    }

    @Test
    public void equals() {
        // sort by name success -> returns true
        unsortedNameModel.sortPersons(0);
        assertTrue(expectedModelName.equals(unsortedNameModel));

        // sort by phone success -> returns true
        unsortedPhoneModel.sortPersons(1);
        assertTrue(expectedModelPhone.equals(unsortedPhoneModel));

        // sort by email success -> returns true
        unsortedEmailModel.sortPersons(2);
        assertTrue(expectedModelEmail.equals(unsortedEmailModel));

        // sort by address success -> returns true
        unsortedAddressModel.sortPersons(3);
        assertTrue(expectedModelAddress.equals(unsortedAddressModel));

        // sort by remark success -> returns true
        unsortedRemarkModel.sortPersons(4);
        assertTrue(expectedModelRemark.equals(unsortedRemarkModel));
    }
}
