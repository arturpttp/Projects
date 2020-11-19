package net.dev.art.core.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Serialization {

    public static final String SEPARATOR = "|";

    public static String serializeList(List<String> list) {
        String ret = "";
        for (String str : list) {
            if (!ret.equals(""))
                ret += SEPARATOR + str;
            else
                ret += str;
        }
        return ret;
    }

    public static List<String> deserializeList(String serialized) {
        List<String> list = new ArrayList<>();
        for (String s : serialized.split(SEPARATOR)) {
            System.out.println(s);
        }
        return list;
    }

}
