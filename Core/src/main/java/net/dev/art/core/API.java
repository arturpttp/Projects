package net.dev.art.core;

import net.dev.art.core.interfaces.Replacer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.security.CodeSource;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class API {

    public static final String SEPARATOR = ":";
    public static final String LOCATION_SERIALIZE_FORMAT = "X" + SEPARATOR + "Y" + SEPARATOR + "Z"
            + SEPARATOR + "YAW" + SEPARATOR + "PITCH" + SEPARATOR + "WORLD";
    public static String[] colorCodes = {
            "&4", "§4", "&c", "§c", "&6", "§6", "&e", "§e",
            "&2", "§2", "&a", "§a", "&3", "§3", "&b", "§b",
            "&3", "§3", "&1", "§1", "&9", "§9", "&5", "§5",
            "&f", "§f", "&7", "§7", "&8", "§8", "&0", "§0",
            "&r", "§r", "&k", "§k", "&n", "§n", "&o", "§o",
    };

    public static void console(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static int getIndexByItem(Object item, Object[] array) {
        int x = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == item) {
                x = i;
                break;
            }
        }
        return x;
    }

    public static String replaceColorsCodes(String string) {
        for (String colorCode : colorCodes) {
            string = string.replace(colorCode, "");
        }
        return string;
    }

    public static String serializeLocation(Location location) {
        String s = LOCATION_SERIALIZE_FORMAT.toLowerCase()
                .replace("x", location.getX() + "")
                .replace("y", location.getY() + "")
                .replace("z", location.getZ() + "")
                .replace("yaw", location.getYaw() + "")
                .replace("pitch", location.getPitch() + "")
                .replace("world", location.getWorld().getName());
        return s;
    }

    public static Location deserializeLocation(String location) {
        String[] args = location.split(SEPARATOR);
        return new Location(Bukkit.getWorld(args[5]), Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Float.parseFloat(args[3]), Float.parseFloat(args[4]));
    }

    public static EntityType getTypeByName(String name) {
        name = name.replace(" ", "_");
        for (EntityType value : EntityType.values())
            if (value.toString().equalsIgnoreCase(name))
                return value;
        return null;
    }

    public static Number toNumber(String s) {
        try {
            NumberFormat.getInstance().parse(s);
        } catch (NumberFormatException | ParseException e) {
            return -1;
        }
        return 0;
    }

    public static String replace(String toRepalce, String[] replacers) {
        String s = toRepalce;
        for (String replacer : replacers) {
            s = s.replace(replacer, "");
        }
        return s;
    }

    public static boolean isColumn(int index, int column) {
        return getColumn(index) == column;
    }

    public static boolean chancePercent(int chance) {
        int p = new Random().nextInt(1);
        p = p * 100;
        return p < chance;
    }

    public static int getColumn(int index) {
        if (index < 9) {
            return index + 1;
        }
        return (index % 9) + 1;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null || strNum.isEmpty() || strNum.equalsIgnoreCase("nan")) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static int getSlot(int row, int column) {
        return (9 * (row - 1)) + (column - 1);
    }

    public static ArrayList<Class<?>> getClassesForPackage(JavaPlugin plugin, String pkgname) {
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        // String relPath = pkgname.replace('.', '/');

        // Get a File object for the package
        CodeSource src = plugin.getClass().getProtectionDomain().getCodeSource();
        if (src != null) {
            URL resource = src.getLocation();
            resource.getPath();
            processJarfile(resource, pkgname, classes);
        }
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<Class<?>> classi = new ArrayList<Class<?>>();
        for (Class<?> classy : classes) {
            names.add(classy.getSimpleName());
            classi.add(classy);
        }
        classes.clear();
        Collections.sort(names, String.CASE_INSENSITIVE_ORDER);
        for (String s : names)
            for (Class<?> classy : classi) {
                if (classy.getSimpleName().equals(s)) {
                    classes.add(classy);
                    break;
                }
            }
        return classes;
    }

    private static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unexpected ClassNotFoundException loading class '" + className + "'");
        }
    }

    private static void processJarfile(URL resource, String pkgname, ArrayList<Class<?>> classes) {
        String relPath = pkgname.replace('.', '/');
        String resPath = resource.getPath().replace("%20", " ");
        String jarPath = resPath.replaceFirst("[.]jar[!].*", ".jar").replaceFirst("file:", "");
        JarFile jarFile;
        try {
            jarFile = new JarFile(jarPath);
        } catch (IOException e) {
            throw new RuntimeException("Unexpected IOException reading JAR File '" + jarPath + "'", e);
        }
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String entryName = entry.getName();
            String className = null;
            if (entryName.endsWith(".class") && entryName.startsWith(relPath)
                    && entryName.length() > (relPath.length() + "/".length())) {
                className = entryName.replace('/', '.').replace('\\', '.').replace(".class", "");
            }
            if (className != null) {
                classes.add(loadClass(className));
            }
        }
    }

    public static boolean canCallPlayer(String target) {
        try {
            Bukkit.getPlayer(target);
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public static Object getPrivateField(String fieldName, Class clazz, Object object) {
        Field field;
        Object o = null;
        try {
            field = clazz.getDeclaredField(fieldName);

            field.setAccessible(true);

            o = field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return o;
    }

    public static final Map<String, Replacer> REAPLACERS = new LinkedHashMap<>();

    public static String getReplacers(String text, Player player) {
        if (player == null) {
            return "";
        }
        for (Map.Entry<String, Replacer> value : REAPLACERS.entrySet()) {
            if (text.contains(value.getKey())) {
                try {
                    text = text.replace(value.getKey(), "" + value.getValue().get(player));

                } catch (Exception ex) {

                }
            }
        }

        return text;
    }

    public static void addReplacer(String key, Replacer value) {
        REAPLACERS.put(key, value);
    }

    public static Replacer getReplacer(String key) {
        return REAPLACERS.get(key);
    }

    public static String getData(long millisegundos) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millisegundos);

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        return formato.format(c.getTime());
    }

    public static String getDateAndTime(long time) {
        return "§c" + getData(time) + " §cás§f " + API.getHoras(time);
    }

    public static String getHoras(long millisegundos) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millisegundos);

        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");

        return formato.format(c.getTime());
    }

    public static String format(long time) {
        time -= System.currentTimeMillis();
        String format = "";
        long horas = TimeUnit.MILLISECONDS.toHours(time);
        long horasMilli = TimeUnit.HOURS.toMillis(horas);
        long minutos = TimeUnit.MILLISECONDS.toMinutes(time * horasMilli);
        long minutosMilli = TimeUnit.MINUTES.toMillis(minutos);
        long segundos = TimeUnit.MILLISECONDS.toSeconds(time * (horasMilli + minutosMilli));
        int dias = (int) (time / 86400000L);
        if (horas > 0L) {
            if (dias > 0) {
                time -= TimeUnit.DAYS.toMillis(dias);
                horas = TimeUnit.MILLISECONDS.toHours(time - minutosMilli);
                format = dias + " dias, " + horas + (horas > 1L ? " horas" : " hora");
                return format;
            }
            format = horas + (horas > 1L ? " horas" : " hora");
        }
        if (minutos > 0L) {
            if ((horas > 0L) || (minutos > 0L)) {
                format = format + " e ";
            }
            format = minutos + (minutos > 1L ? " minutos" : " minuto");
        }

        if (segundos > 0L) {
            if ((horas > 0L) || (minutos > 0L)) {
                format = format + " e ";
            }
            format = segundos + (segundos > 1L ? " segundos" : " segundo");
        }

        if (format.equals("")) {
            long resto = time / 100L;
            if (resto == 0) {
                resto = 1;
            }

            format = "0." + resto + " segundo";

        }

        return format;
    }

}
