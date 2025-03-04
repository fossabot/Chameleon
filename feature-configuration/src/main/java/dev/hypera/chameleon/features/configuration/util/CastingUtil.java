/*
 * Chameleon Framework - Cross-platform Minecraft plugin framework
 *  Copyright (c) 2021-present The Chameleon Framework Authors.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
package dev.hypera.chameleon.features.configuration.util;

import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class CastingUtil {

    public static @NotNull String asString(@NotNull Object o) {
        if (o instanceof String) return (String) o;
        return o.toString();
    }

    public static @Nullable Integer asInt(@NotNull Object o) {
        try {
            if (o instanceof Integer) return (Integer) o;
            else if (o instanceof Long) return ((Long) o).intValue();
            else if (o instanceof String) return Integer.parseInt((String) o);
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static @Nullable Double asDouble(@NotNull Object o) {
        try {
            if (o instanceof Double) return (Double) o;
            else if (o instanceof String) return Double.parseDouble((String) o);
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static @Nullable Long asLong(@NotNull Object o) {
        try {
            if (o instanceof Long) return (Long) o;
            else if (o instanceof String) return Long.parseLong((String) o);
            return null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static @Nullable Boolean asBoolean(@NotNull Object o) {
        if (o instanceof Boolean) return (Boolean) o;
        else if (o instanceof String) return Boolean.parseBoolean((String) o);
        return null;
    }

    public static @Nullable CastingList asList(@NotNull Object o) {
        if (o instanceof Collection) {
            CastingList list = new CastingList();
            list.addAll((Collection<?>) o);
            return list;
        } return null;
    }

    public static @Nullable CastingMap asMap(@NotNull Object o) {
        if (o instanceof Map) {
            CastingMap map = new CastingMap();
            map.putAll((Map<?, ?>) o);
            return map;
        } return null;
    }

}
