package com.kmanchev.fs;

public class FileSystemService {

    private FileSystem fileSystem;

    public FileSystemService(String rootName) {
        this.fileSystem = new FileSystem(rootName);
    }

    private enum Command {
        RM("rm"),
        CD("cd"),
        LS("ls"),
        CAT("cat"),
        TOUCH("touch"),
        MV("mv"),
        HELP("help");

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
        switch (command) {
            case RM:
                return performRM(inputParts);
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
            default:
                return "Something went wrong.";
        }
    }

    /*private String performRM(String[] inputParts) {

        if (inputParts.length > 3) {
            return "invalid command";
        }
    }

    private String performMV(String[] inputParts) {
    }

    private String performTOUCH(String[] inputParts) {
    }

    private String performCAT(String[] inputParts) {
    }

    private String performLS(String[] inputParts) {
    }

    private String performCD(String[] inputParts) {
    }

    private String performHELP(String[] inputParts) {
    }*/

}

