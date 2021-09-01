package com.company.ui.util;

import com.company.di.annotations.Singleton;
import java.io.*;
import java.nio.file.Path;
import java.util.List;

@Singleton
public class Serialization {

    public Serialization() {
    }

    public void writeData(List list, String nameFile) throws IOException {
        String fileName = String.format("%s.bin", nameFile);
        Path path = Path.of("ui/src/main/resources", fileName);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toFile(), false))) {
            oos.writeObject(list);
        }
    }

    public List readData(String nameFile) throws IOException, ClassNotFoundException {
        String fileName = String.format("%s.bin", nameFile);
        Path path = Path.of("ui/src/main/resources", fileName);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            List object = (List) ois.readObject();
            return object;
        }
    }
}
