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
import walkingkooka.ToStringBuilder;
import walkingkooka.Value;

import java.util.Objects;
import java.util.Optional;

/**
 * A value type that holds the cache value and some extra meta data.
 * Instances are not meant be marshalled to JSON or serializable.
 */
public final class CacheValue implements Value<Optional<Object>> {

    public static CacheValue with(final Optional<Object> value) {
        return new CacheValue(
            Objects.requireNonNull(value, "value")
        );
    }

    private CacheValue(final Optional<Object> value) {
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
                Objects.requireNonNull(value, "value")
            );
    }

    // Object...........................................................................................................

    @Override
    public int hashCode() {
        return Objects.hashCode(
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
        return this.value.equals(other.value);
    }

    @Override
    public String toString() {
        return ToStringBuilder.empty()
            .label("value")
            .value(this.value)
            .build();
    }
}
