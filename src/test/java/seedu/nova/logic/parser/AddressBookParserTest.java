package seedu.nova.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nova.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.nova.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.nova.testutil.Assert.assertThrows;
import static seedu.nova.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

<<<<<<< HEAD:src/test/java/seedu/nova/logic/parser/AddressBookParserTest.java
import seedu.nova.logic.commands.AddCommand;
import seedu.nova.logic.commands.ClearCommand;
import seedu.nova.logic.commands.DeleteCommand;
import seedu.nova.logic.commands.EditCommand;
import seedu.nova.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.nova.logic.commands.ExitCommand;
import seedu.nova.logic.commands.FindCommand;
import seedu.nova.logic.commands.HelpCommand;
import seedu.nova.logic.commands.ListCommand;
import seedu.nova.logic.parser.exceptions.ParseException;
import seedu.nova.model.common.person.NameContainsKeywordsPredicate;
import seedu.nova.model.common.person.Person;
import seedu.nova.testutil.EditPersonDescriptorBuilder;
import seedu.nova.testutil.PersonBuilder;
import seedu.nova.testutil.PersonUtil;
=======
import seedu.address.logic.commands.AbAddCommand;
import seedu.address.logic.commands.AbClearCommand;
import seedu.address.logic.commands.AbDeleteCommand;
import seedu.address.logic.commands.AbEditCommand;
import seedu.address.logic.commands.AbEditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.AbFindCommand;
import seedu.address.logic.commands.AbListCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
>>>>>>> c6c0bb78e07ef00942b0263e80b55d6c724c2c2b:src/test/java/seedu/address/logic/parser/AddressBookParserTest.java

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
                seedu.address.logic.commands.AbDeleteCommand.COMMAND_WORD + " "
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
                seedu.address.logic.commands.AbFindCommand.COMMAND_WORD + " "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new AbFindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(AbListCommand.COMMAND_WORD) instanceof AbListCommand);
        assertTrue(parser.parseCommand(AbListCommand.COMMAND_WORD + " 3") instanceof AbListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseCommand("unknownCommand"));
    }
}
