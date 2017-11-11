package seedu.address.logic.commands;
//@@author TanYikai

import static java.util.Objects.requireNonNull;

/**
 * Lists all sorted persons in the address book to the user.
 */
public class SortCommand extends UndoableCommand {
    public static final String COMMAND_WORD = "sort";
    public static final String COMMAND_ALIAS = "st";
    public static final String SHORT_MESSAGE_USAGE = COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Sorted all persons";

    private int sortOption;

    /**
     * @param sortOption is the option in which person is sorted by
     * default is by name
     * 0,1,2,3,4 represents sort by name, phone, email, address, remark respectively
     */
    public SortCommand(int sortOption) {
        requireNonNull(sortOption);
        this.sortOption = sortOption;
    }

    @Override
    public CommandResult executeUndoableCommand() {
        model.sortPersons(sortOption);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
