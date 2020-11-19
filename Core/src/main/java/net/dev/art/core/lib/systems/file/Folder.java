package net.dev.art.core.lib.systems.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Folder {

    private String path;
    private File folder;
    private List<File> files = new ArrayList<>();
    private boolean autoCreateFolder = true;

    public Folder(String path) {
        this(new File(path));
        this.path = path;
    }

    public Folder(File folder) {
        this.folder = folder;
        System.out.println(folder.getAbsolutePath());
        if (autoCreateFolder)
            folder.mkdir();
    }

    public Folder addFile(String name) {
        File file = new File(folder.getAbsolutePath() + "/" + name);
        files.add(file);

        return this;
    }

    public void execute() {

        for (File file : files) {
            System.out.println(file.getAbsolutePath());
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
