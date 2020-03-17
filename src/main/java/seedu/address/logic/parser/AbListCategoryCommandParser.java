package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;

import java.util.Arrays;
import java.util.Set;

import seedu.address.logic.commands.AbListCategoryCommand;
import seedu.address.logic.commands.AbListClassmateCommand;
import seedu.address.logic.commands.AbListTeammateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.category.Category;
import seedu.address.model.person.CategoryContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new AbFindCommand object
 */
public class AbListCategoryCommandParser implements Parser<AbListCategoryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AbFindCommand
     * and returns a AbFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AbListCategoryCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY);
        Set<Category> categoryList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_CATEGORY));

        if (categoryList.size() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AbListCategoryCommand.MESSAGE_USAGE));
        } else if (categoryList.size() > 1) {
            throw new ParseException("Please only provide 1 category.");
        }

        if (categoryList.iterator().next().toString().equals("[classmate]")) {
            String[] classmateList = {"classmate"};
            return new AbListClassmateCommand(new CategoryContainsKeywordsPredicate(Arrays.asList(classmateList)));
        } else {
            String[] teammateList = {"teammate"};
            return new AbListTeammateCommand(new CategoryContainsKeywordsPredicate(Arrays.asList(teammateList)));
        }


        //return new AbListCategoryCommandParser(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
        /*String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            seedu.address.logic.commands.AbFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new AbFindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));*/
    }

}
