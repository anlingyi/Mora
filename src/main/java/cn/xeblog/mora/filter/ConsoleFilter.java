package cn.xeblog.mora.filter;

import cn.xeblog.mora.ui.MoraDialog;
import com.intellij.execution.filters.ConsoleFilterProvider;
import com.intellij.execution.filters.Filter;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author anlingyi
 * @date 2022/8/11 11:46 PM
 */
public class ConsoleFilter implements ConsoleFilterProvider {

    @Override
    public Filter @NotNull [] getDefaultFilters(@NotNull Project project) {
        return new Filter[]{(line, entireLength) -> {
            if (line.contains("java.lang.NullPointerException")) {
                SwingUtilities.invokeLater(() -> new MoraDialog().show());
            }

            return null;
        }};
    }

}
