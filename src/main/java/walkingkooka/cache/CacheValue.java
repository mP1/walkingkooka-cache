/*
 * Copyright 2025 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.cache;

import walkingkooka.Cast;
import walkingkooka.HasId;
import walkingkooka.ToStringBuilder;
import walkingkooka.Value;
import walkingkooka.text.printer.IndentingPrinter;
import walkingkooka.text.printer.TreePrintable;

import java.util.Objects;
import java.util.Optional;

/**
 * A value type that holds the cache value and some extra meta data.
 * Instances are not meant be marshalled to JSON or serializable.
 */
public final class CacheValue implements Value<Optional<Object>>,
    HasId<Optional<CacheKey>>,
    Comparable<CacheValue>,
    TreePrintable {

    public static CacheValue with(final CacheKey key,
                                  final Optional<Object> value) {
        return new CacheValue(
            Objects.requireNonNull(key, "key"),
            Objects.requireNonNull(value, "value")
        );
    }

    private CacheValue(final CacheKey key,
                       final Optional<Object> value) {
        this.key = key;
        this.value = value;
    }

    // Value............................................................................................................

    @Override
    public Optional<Object> value() {
        return this.value;
    }

    private final Optional<Object> value;

    /**
     * Would be setter that returns a CacheValue with the given value creating a new instance if necessary.
     */
    public CacheValue setValue(final Optional<Object> value) {
        return this.value.equals(value) ?
            this :
            new CacheValue(
                this.key,
                Objects.requireNonNull(value, "value")
            );
    }

    // HasId............................................................................................................

    @Override
    public Optional<CacheKey> id() {
        return Optional.of(
            this.key()
        );
    }

    public CacheKey key() {
        return this.key;
    }

    public CacheValue setKey(final CacheKey key) {
        return this.key.equals(key) ?
            this :
            new CacheValue(
                Objects.requireNonNull(key, "key"),
                this.value
            );
    }

    private final CacheKey key;

    // Object...........................................................................................................

    @Override
    public int hashCode() {
        return Objects.hash(
            this.key,
            this.value
        );
    }

    @Override
    public boolean equals(final Object other) {
        return this == other ||
            other instanceof CacheValue &&
                this.equals0(Cast.to(other));
    }

    private boolean equals0(final CacheValue other) {
        return this.key.equals(other.key) &&
            this.value.equals(other.value);
    }

    @Override
    public String toString() {
        return ToStringBuilder.empty()
            .label(this.key.toString())
            .separator("=")
            .value(this.value)
            .build();
    }

    // Comparable.......................................................................................................

    @Override
    public int compareTo(final CacheValue other) {
        return this.key.compareTo(other.key());
    }

    // TreePrintable....................................................................................................

    @Override
    public void printTree(final IndentingPrinter printer) {
        printer.println(this.key.toString());
        printer.indent();
        {
            final Optional<Object> value = this.value();
            if(value.isPresent()) {
                TreePrintable.printTreeOrToString(
                    value.get(),
                    printer
                );
            }
        }
        printer.outdent();
    }
}
