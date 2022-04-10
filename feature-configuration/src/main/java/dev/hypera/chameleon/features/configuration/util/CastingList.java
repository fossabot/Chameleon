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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CastingList extends ArrayList<Object> {

    public @NotNull Optional<Object> getOptional(int index) {
        return Optional.ofNullable(get(index));
    }

    public @NotNull Optional<Class<?>> getType(int index) {
        return getOptional(index).map(Object::getClass);
    }
    public boolean isType(int index, @NotNull Class<?> type) {
        return getOptional(index).filter(type::isInstance).isPresent();
    }

    public @NotNull Optional<String> getString(int index) {
        return getOptional(index).map(o -> (String) o);
    }
    public @NotNull Optional<Integer> getInt(int index) {
        return getOptional(index).map(o -> {
            if (o instanceof Long) return ((Long) o).intValue();
            else if (o instanceof String) return Integer.parseInt((String) o);
            return (Integer) o;
        });
    }
    public @NotNull Optional<Double> getDouble(int index) {
        return getOptional(index).map(o -> (Double) o);
    }
    public @NotNull Optional<Long> getLong(int index) {
        return getOptional(index).map(o -> (Long) o);
    }
    public @NotNull Optional<Boolean> getBoolean(int index) {
        return getOptional(index).map(o -> {
            if (o instanceof Boolean) return (Boolean) o;
            return Boolean.parseBoolean((String) o);
        });
    }
    public @NotNull Optional<CastingList> getList(int index) {
        return getOptional(index).map(o -> {
            CastingList list = new CastingList();
            list.addAll((List<?>) o);
            return list;
        });
    }

}
