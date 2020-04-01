package seedu.nova.logic.commands.eventcommands;

import static java.util.Objects.requireNonNull;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.nova.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDate;

import seedu.nova.logic.commands.Command;
import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.model.event.DateNotFoundException;
import seedu.nova.model.event.EventNotFoundException;
import seedu.nova.model.Model;

/**
 * adds a note into an Event.
 */
public class EventAddNoteCommand extends Command {
    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note to an event. \n"
            + "Parameters: "
            + PREFIX_DESC + "[description] "
            + PREFIX_TIME + "[YYYY-MM-DD] "
            + PREFIX_INDEX + "[index]"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESC + "Remember to bring charger! "
            + PREFIX_TIME + "2020-03-10 "
            + PREFIX_INDEX + "2";

    public static final String MESSAGE_SUCCESS = "New note has been added: \n%1$s";

    private String desc;
    private LocalDate date;
    private int index;

    public EventAddNoteCommand(String desc, LocalDate date, int index) {
        requireNonNull(date);
        this.desc = desc;
        this.date = date;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            String response = model.addNote(desc, date, index);
            return new CommandResult(String.format(MESSAGE_SUCCESS, response));

        } catch (DateNotFoundException e) {
            throw new CommandException("Invalid date - you have no events on that date.");

        } catch (EventNotFoundException e) {
            throw new CommandException("Invalid index.");
        }
    }

}
