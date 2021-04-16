package piniufly.service;


import piniufly.ui.model.Entry;
import piniufly.ui.model.TitleEntry;
import piniufly.ui.model.ToggleButtonEntry;
import piniufly.ui.model.UIModel;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileStructureHelper {

    public static UIModel convertToUIModel(String baseDirectoryPath, Container container)
            throws Exception {

        List<Entry> entries = new ArrayList<Entry>();

        Object[] paths = listFilesUsingFileWalk(baseDirectoryPath, 2).stream().sorted().toArray();
        for (int i = 1; i < paths.length; i++) {
            Path path = (Path) paths[i];

            if(Files.isDirectory(path)){
                entries.add(new TitleEntry(path.getFileName().toString().substring(2), null, container));
            }else{
                entries.add(new ToggleButtonEntry(path.getFileName().toString(), null, path.toAbsolutePath().toString(), container));
            }

        }

        return new UIModel(entries);
    }

    public static Set<Path> listFilesUsingFileWalk(String dir, int depth) throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get(dir), depth)) {
            return stream
                    .collect(Collectors.toSet());
        }
    }
}
