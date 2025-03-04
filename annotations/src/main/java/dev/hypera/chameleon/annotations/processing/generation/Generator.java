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
package dev.hypera.chameleon.annotations.processing.generation;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import dev.hypera.chameleon.annotations.Plugin;
import dev.hypera.chameleon.annotations.Plugin.Platform;
import dev.hypera.chameleon.core.data.PluginData;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import org.jetbrains.annotations.NotNull;

public abstract class Generator {

    public static final @NotNull String INDENT = "    ";

    public abstract void generate(@NotNull Plugin data, @NotNull TypeElement plugin, @NotNull ProcessingEnvironment env) throws Exception;

    protected @NotNull CodeBlock createPluginData(@NotNull Plugin data) {
        return CodeBlock.builder().add(
            "$T pluginData = new $T($S, $S, $S, $S, $T.asList($L), $T.asList($L))",
            PluginData.class, PluginData.class, data.name().isEmpty() ? (data.id().isEmpty() ? "Unknown" : data.id()) : data.name(), data.version(), data.description(), data.url(),
            Arrays.class, data.authors().length > 0 ? '"' + String.join("\",\"", data.authors()) + '"' : "",
            Arrays.class, CodeBlock.builder().add(Arrays.stream(data.platforms().length > 0 ? data.platforms() : Platform.values()).map(p -> "$1T." + p.name()).collect(Collectors.joining(", ")), PluginData.Platform.class).build()
        ).build();
    }
    protected @NotNull ClassName clazz(@NotNull String p, @NotNull String n) {
        return ClassName.get(p, n);
    }

}
