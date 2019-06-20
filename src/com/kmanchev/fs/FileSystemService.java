package com.kmanchev.fs;

import com.kmanchev.fs.exceptions.NotAllowedOperationException;

import java.util.ArrayList;

public class FileSystemService {

    private FileSystem fileSystem;

    public FileSystemService(String rootName) {
        this.fileSystem = new FileSystem(rootName);
    }

    private enum Command {
        RM("rm"),
        MKDIR("mkdir"),
        CD("cd"),
        LS("ls"),
        CAT("cat"),
        TOUCH("touch"),
        MV("mv"),
        HELP("help"),
        PWD("pwd");

        private final String cmdName;

        Command(String cmdName) {
            this.cmdName = cmdName;
        }

        public String getCommandName() {
            return this.cmdName;
        }
    }

    public String digest(String input) {
        String[] inputParts = input.split("\\s+");

        for (Command currentCmd : Command.values()) {
            if (inputParts[0].equals(currentCmd.getCommandName())) {
                return performCommand(currentCmd, inputParts);
            }
        }
        return "invalid command";
    }

    private String performCommand(Command command, String[] inputParts) {
        try {
            switch (command) {
                case RM:
                    return performRM(inputParts);
                case MKDIR:
                    return performMKDIR(inputParts);
                case CD:
                    return performCD(inputParts);
                case LS:
                    return performLS(inputParts);
                case CAT:
                    return performCAT(inputParts);
                case TOUCH:
                    return performTOUCH(inputParts);
                case MV:
                    return performMV(inputParts);
                case HELP:
                    return performHELP(inputParts);
                case PWD:
                    return performPWD(inputParts);
                default:
                    return "Something went wrong.";
            }
        } catch (NotAllowedOperationException e) {
            return "Could not perform operation. Cause: " + e.getMessage();
        }
    }

    private String performPWD(String[] inputParts) {
        if (inputParts.length > 1) {
            return "invalid command";
        }

        return fileSystem.getCurrentDirFullPath();
    }

    private String performMKDIR(String[] inputParts) {
        fileSystem.addDir(inputParts[1]);
        return "OK";
    }

    private String performRM(String[] inputParts) throws NotAllowedOperationException {

        if (inputParts.length > 3) {
            return "invalid command";
        }

        if (inputParts.length == 2) {
            String toDelete = inputParts[1];

            if (fileSystem.isChildFile(toDelete)) {
                fileSystem.removeFile(toDelete);
                return "OK";
            }

            if (fileSystem.isChildDir(toDelete)) {
                fileSystem.removeDir(toDelete);
                return "OK";
            }
        }

        if (inputParts.length == 3) {
            String toDelete = inputParts[2];
            if (inputParts[1].equals("-r")) {
                if (fileSystem.isChildDir(toDelete)) {
                    fileSystem.removeChildDirContent(toDelete);
                    return "OK";
                }
            }
        }

        return "Invalid command";
    }


    private String performMV(String[] inputParts) {
        return null;
    }

    private String performTOUCH(String[] inputParts) {
        if (inputParts.length > 3) {
            return "Invalid command";
        }

        if (inputParts.length == 2) {
            fileSystem.addFile(inputParts[1], "");
            return "OK";
        }

        if (inputParts.length == 3) {
            fileSystem.addFile(inputParts[1], inputParts[1]);
            return "OK";
        }

        return "Invalid command";
    }

    private String performCAT(String[] inputParts) {
        return null;
    }

    private String performLS(String[] inputParts) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> childrenInfo = (inputParts.length > 1 && inputParts[1].equals("-la")) ?
                fileSystem.getCurrentDirChildrenDetailed() : fileSystem.getCurrentDirChildren();
        for (String currentChild : childrenInfo) {
            sb.append(currentChild).append("\n");
        }
        return sb.toString();
    }

    private String performCD(String[] inputParts) {
        if (inputParts.length > 2) {
            return "Invalid command";
        }

        if (inputParts.length == 2) {
            if (inputParts[1].equals("..")) {
                fileSystem.goToParentDir();
                return "OK";
            }
        }

        return "Invalid command";
    }

    private String performHELP(String[] inputParts) {
        return null;
    }

}

