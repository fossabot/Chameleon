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
package dev.hypera.chameleon.annotations;

import dev.hypera.chameleon.annotations.processing.generation.Generator;
import dev.hypera.chameleon.annotations.processing.generation.impl.bungeecord.BungeeCordGenerator;
import dev.hypera.chameleon.annotations.processing.generation.impl.minestom.MinestomGenerator;
import dev.hypera.chameleon.annotations.processing.generation.impl.spigot.SpigotGenerator;
import dev.hypera.chameleon.annotations.processing.generation.impl.velocity.VelocityGenerator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jetbrains.annotations.NotNull;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface Plugin {

	@NotNull String id();
	@NotNull String name() default "";
	@NotNull String version();
	@NotNull String description() default "";
	@NotNull String url() default "";
	@NotNull String[] authors() default {};
	@NotNull PlatformDependency[] dependencies() default {};

	@NotNull Platform[] platforms() default {};


	enum Platform {

		BUNGEECORD(BungeeCordGenerator.class),
		MINESTOM(MinestomGenerator.class),
		SPIGOT(SpigotGenerator.class),
		VELOCITY(VelocityGenerator.class);

		private final @NotNull Class<? extends Generator> generator;

		Platform(@NotNull Class<? extends Generator> generator) {
			this.generator = generator;
		}

		public @NotNull Class<? extends Generator> getGenerator() {
			return generator;
		}

	}


}
