package ui;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

public class cursor
{
    public void update(@NotNull AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        Editor editor = event.getData(PlatformDataKeys.EDITOR);
        Document document = editor.getDocument();
        final CaretModel caretModel = editor.getCaretModel();
        // Getting the primary caret ensures we get the correct one of a possible many.
        final Caret primaryCaret = caretModel.getPrimaryCaret();
        // Get the caret information
        LogicalPosition logicalPos = primaryCaret.getLogicalPosition();
        VisualPosition visualPos = primaryCaret.getVisualPosition();
        int caretOffset = primaryCaret.getOffset();

        // Build and display the caret report.
        String report = logicalPos + "\n" + visualPos + "\n" +
                "Offset: " + caretOffset;
        Messages.showInfoMessage(report, "Caret Parameters Inside The Editor");



        //var C_poistion= document.getCaretModel()


       // boolean enabled = project != null && editor != null && canEnable();
        //event.getPresentation().setEnabled(enabled);
    }
//    private boolean canEnable() {
//        SelectionModel selectionModel = editor.getSelectionModel();
//        if (!selectionModel.hasSelection()) {
//            return false;
//        }
//        Document document = editor.getDocument();
//
//        int lineNumberSelStart  = document.getLineNumber(selectionModel.getSelectionStart());
//        int lineNumberSelEnd    = document.getLineNumber(selectionModel.getSelectionEnd());
//
//        return lineNumberSelEnd > lineNumberSelStart;
//    }

}
