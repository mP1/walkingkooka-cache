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

import org.junit.jupiter.api.Test;
import walkingkooka.HashCodeEqualsDefinedTesting2;
import walkingkooka.ToStringTesting;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class CacheValueTest implements HashCodeEqualsDefinedTesting2<CacheValue>,
    ToStringTesting<CacheValue> {

    private final static Optional<Object> VALUE = Optional.of("Hello");

    @Test
    public void testWithWithNullValueFails() {
        assertThrows(
            NullPointerException.class,
            () -> CacheValue.with(null)
        );
    }

    @Test
    public void testWith() {
        final CacheValue cacheValue = CacheValue.with(VALUE);
        this.valueAndCheck(
            cacheValue,
            VALUE
        );
    }

    private void valueAndCheck(final CacheValue value,
                               final Optional<Object> expected) {
        this.checkEquals(
            expected,
            value.value()
        );
    }

    // setValue.........................................................................................................

    @Test
    public void testSetValueWithNullFails() {
        assertThrows(
            NullPointerException.class,
            () -> this.createObject().setValue(null)
        );
    }

    @Test
    public void testSetValueWithSame() {
        final CacheValue cacheValue = CacheValue.with(VALUE);

        assertSame(
            cacheValue,
            cacheValue.setValue(VALUE)
        );
    }

    @Test
    public void testSetValueWithDifferent() {
        final CacheValue cacheValue = CacheValue.with(VALUE);

        final Optional<Object> differentValue = Optional.of("Different");

        final CacheValue different = cacheValue.setValue(differentValue);
        assertNotSame(
            cacheValue,
            different
        );

        this.valueAndCheck(
            different,
            differentValue
        );
    }

    // Object...........................................................................................................

    @Override
    public CacheValue createObject() {
        return CacheValue.with(VALUE);
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createObject(),
            "value=\"Hello\""
        );
    }

    // class............................................................................................................

    @Override
    public Class<CacheValue> type() {
        return CacheValue.class;
    }
}
