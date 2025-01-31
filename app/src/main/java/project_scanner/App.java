package project_scanner;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide a directory path");
            return;
        }

        String directoryPath = args[0];
        try {
            scanDirectory(Paths.get(directoryPath), "", true);
        } catch (IOException e) {
            System.out.println("Error scanning directory: " + e.getMessage());
        }
    }

    static void scanDirectory(Path path, String indent, boolean isLast) throws IOException {
        if (Files.exists(path) && Files.isDirectory(path)) {
            System.out.println(indent + (isLast ? "└── " : "├── ") + path.getFileName().toString());

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                int count = 0;
                Path[] entries = new Path[(int) Files.list(path).count()];
                for (Path entry : stream) {
                    entries[count++] = entry;
                }

                for (int i = 0; i < entries.length; i++) {
                    Path entry = entries[i];
                    boolean isDir = Files.isDirectory(entry);
                    boolean isLastItem = i == entries.length - 1;
                    if (isDir) {
                        scanDirectory(entry, indent + (isLast ? "    " : "│   "), isLastItem);
                    } else {
                        System.out.println(indent + (isLast ? "    └── " : "    ├── ") + entry.getFileName().toString());
                    }
                }
            }
        }
    }
}
