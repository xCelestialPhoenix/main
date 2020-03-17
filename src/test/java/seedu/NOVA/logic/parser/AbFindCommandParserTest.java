package seedu.NOVA.logic.parser;

import static seedu.NOVA.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.NOVA.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.NOVA.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.NOVA.logic.commands.AddressBookCommands.AbFindCommand;
import seedu.NOVA.model.person.NameContainsKeywordsPredicate;

public class AbFindCommandParserTest {

    private AbFindCommandParser parser = new AbFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AbFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        AbFindCommand expectedAbFindCommand =
                new AbFindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedAbFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedAbFindCommand);
    }

}
