package seedu.NOVA.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.NOVA.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.NOVA.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.NOVA.testutil.Assert.assertThrows;
import static seedu.NOVA.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.NOVA.logic.commands.AddressBookCommands.AbAddCommand;
import seedu.NOVA.logic.commands.AddressBookCommands.AbClearCommand;
import seedu.NOVA.logic.commands.AddressBookCommands.AbDeleteCommand;
import seedu.NOVA.logic.commands.AddressBookCommands.AbEditCommand;
import seedu.NOVA.logic.commands.AddressBookCommands.AbEditCommand.EditPersonDescriptor;
import seedu.NOVA.logic.commands.AddressBookCommands.AbFindCommand;
import seedu.NOVA.logic.commands.AddressBookCommands.AbHelpCommand;
import seedu.NOVA.logic.commands.AddressBookCommands.AbListCommand;
import seedu.NOVA.logic.commands.Common.ExitCommand;
import seedu.NOVA.logic.parser.exceptions.ParseException;
import seedu.NOVA.model.person.NameContainsKeywordsPredicate;
import seedu.NOVA.model.person.Person;
import seedu.NOVA.testutil.EditPersonDescriptorBuilder;
import seedu.NOVA.testutil.PersonBuilder;
import seedu.NOVA.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AbAddCommand command = (AbAddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AbAddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(AbClearCommand.COMMAND_WORD) instanceof AbClearCommand);
        assertTrue(parser.parseCommand(AbClearCommand.COMMAND_WORD + " 3") instanceof AbClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        AbDeleteCommand command = (AbDeleteCommand) parser.parseCommand(
                AbDeleteCommand.COMMAND_WORD + " "
                        + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new AbDeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        AbEditCommand command = (AbEditCommand) parser.parseCommand(AbEditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new AbEditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        AbFindCommand command = (AbFindCommand) parser.parseCommand(
                AbFindCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new AbFindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(AbHelpCommand.COMMAND_WORD) instanceof AbHelpCommand);
        assertTrue(parser.parseCommand(AbHelpCommand.COMMAND_WORD + " 3") instanceof AbHelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(AbListCommand.COMMAND_WORD) instanceof AbListCommand);
        assertTrue(parser.parseCommand(AbListCommand.COMMAND_WORD + " 3") instanceof AbListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AbHelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseCommand("unknownCommand"));
    }
}
