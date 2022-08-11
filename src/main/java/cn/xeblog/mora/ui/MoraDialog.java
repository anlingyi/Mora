package cn.xeblog.mora.ui;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author anlingyi
 * @date 2022/8/11 7:58 PM
 */
public class MoraDialog extends DialogWrapper {

    public MoraDialog() {
        super(true);
        setTitle("猜拳游戏？");
        setResizable(false);
        setCrossClosesWindow(false);
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return new MoraGame(() -> SwingUtilities.invokeLater(() -> this.close(0)));
    }

    @Override
    protected @NotNull Action[] createActions() {
        return new Action[]{};
    }

}
