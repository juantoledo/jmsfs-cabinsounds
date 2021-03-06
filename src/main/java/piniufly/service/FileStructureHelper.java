package piniufly.service;


import piniufly.ui.model.*;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.commons.io.FilenameUtils.removeExtension;
import static piniufly.ui.model.IconHelper.determineIcon;

public class FileStructureHelper {

    public static UIModel convertToUIModel(String baseDirectoryPath, Container container, String origin)
            throws Exception {

        Map<String, List<Entry>> entries = new HashMap<String, List<Entry>>();

        entries.put(origin, new ArrayList<Entry>());

        Object[] paths = listFilesAndDirsUsingFileWalk(baseDirectoryPath, 2).stream().sorted().toArray();
        for (int i = 1; i < paths.length; i++) {
            Path path = (Path) paths[i];

            if (Files.isDirectory(path)) {
                entries.get(origin).add(new TitleEntry(path.getFileName().toString().substring(2), null, container));
            } else {
                PlayableIcon playableIcon = determineIcon(removeExtension(path.getFileName().toString()));
                entries.get(origin).add(new ToggleButtonEntry(removeExtension(path.getFileName().toString()).substring(2), playableIcon.getStoppedIcon(), playableIcon.getPlayingIcon(), path.toAbsolutePath().toString(), container, origin));
            }

        }
        return new UIModel(entries);
    }


    public static Set<Path> listFilesAndDirsUsingFileWalk(String dir, int depth) throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get(dir), depth)) {
            return stream
                    .collect(Collectors.toSet());
        }
    }

    public static Set<String> listDirsUsingFileWalk(String dir, int depth) throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get(dir), depth)) {
            return stream
                    .filter(file -> Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }

}
