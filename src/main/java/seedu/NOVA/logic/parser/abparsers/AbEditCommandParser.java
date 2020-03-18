package seedu.NOVA.logic.parser.abparsers;

import static java.util.Objects.requireNonNull;
import static seedu.NOVA.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.NOVA.commons.core.index.Index;
import seedu.NOVA.logic.commands.abcommands.AbEditCommand;
import seedu.NOVA.logic.commands.abcommands.AbEditCommand.EditPersonDescriptor;
import seedu.NOVA.logic.parser.ArgumentMultimap;
import seedu.NOVA.logic.parser.ArgumentTokenizer;
import seedu.NOVA.logic.parser.CliSyntax;
import seedu.NOVA.logic.parser.Parser;
import seedu.NOVA.logic.parser.ParserUtil;
import seedu.NOVA.logic.parser.exceptions.ParseException;
import seedu.NOVA.model.tag.Tag;

/**
 * Parses input arguments and creates a new AbEditCommand object
 */
public class AbEditCommandParser implements Parser<AbEditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AbEditCommand
     * and returns an AbEditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AbEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_PHONE, CliSyntax.PREFIX_EMAIL,
                        CliSyntax.PREFIX_ADDRESS, CliSyntax.PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AbEditCommand.MESSAGE_USAGE), pe);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(CliSyntax.PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(CliSyntax.PREFIX_ADDRESS)
                    .get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(CliSyntax.PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(AbEditCommand.MESSAGE_NOT_EDITED);
        }

        return new AbEditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
