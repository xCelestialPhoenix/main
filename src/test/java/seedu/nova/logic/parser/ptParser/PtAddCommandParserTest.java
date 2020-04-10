package seedu.nova.logic.parser.ptParser;

import static seedu.nova.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.nova.logic.commands.CommandTestUtil.PROJECT_IP;
import static seedu.nova.logic.commands.CommandTestUtil.PT_TASKDESC;
import static seedu.nova.logic.commands.CommandTestUtil.WEEK_ONE;
import static seedu.nova.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.nova.logic.commands.ptcommands.PtAddCommand;
import seedu.nova.logic.parser.ptparsers.PtAddCommandParser;
import seedu.nova.model.progresstracker.PtTask;
import seedu.nova.testutil.PtTaskBuilder;

public class PtAddCommandParserTest {
    private PtAddCommandParser parser = new PtAddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        PtTask expectedPtTask = new PtTaskBuilder().build();

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + PROJECT_IP + WEEK_ONE + PT_TASKDESC,
                new PtAddCommand(expectedPtTask.getPtWeek(), expectedPtTask.getProject().getProjectName(),
                        expectedPtTask.getTaskDesc().toString()));
    }
}
