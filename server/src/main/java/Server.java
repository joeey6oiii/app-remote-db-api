import commands.CommandManager;
import dataBase.GlobalObj;
import dataBase.Loader;
import defaultClasses.Person;
import yamlsTools.YAMLReader;

import java.util.List;

public class Server {
    public static void main(String[] args) {
        Class<Person> type = Person.class;
        YAMLReader yamlReader = new YAMLReader();
//        List<Person> list = yamlReader.read(path, type);
//        new Loader().load(GlobalObj.dataBase, list);

        CommandManager commandManager = new CommandManager();
        commandManager.startWorking();
    }
}
