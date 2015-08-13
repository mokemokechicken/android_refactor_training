package jp.yumemi.lab.refactorme.base;

import java.util.HashMap;
import java.util.Map;

// 各モデルの参照を保持するクラス

public class ModelContainer {
    public interface Tag {}

    private static Map<Tag, Object> container = new HashMap<>();

    private ModelContainer() {
    }

    public static void register(Tag tag, Object object) {
        container.put(tag, object);
    }

    public static Object get(Tag tag) {
        return container.get(tag);
    }
}
