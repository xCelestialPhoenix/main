package seedu.NOVA.logic.commands.AddressBookCommands;

import static java.util.Objects.requireNonNull;

import seedu.NOVA.commons.core.Messages;
import seedu.NOVA.logic.commands.Command;
import seedu.NOVA.logic.commands.CommandResult;
import seedu.NOVA.model.Model;
import seedu.NOVA.model.person.NameContainsKeywordsPredicate;


/**
 * Finds and lists all persons in NOVA book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class AbFindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    public AbFindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AbFindCommand // instanceof handles nulls
                && predicate.equals(((AbFindCommand) other).predicate)); // state check
    }
}
