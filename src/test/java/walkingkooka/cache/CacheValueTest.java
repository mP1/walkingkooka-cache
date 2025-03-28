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
import walkingkooka.text.printer.TreePrintableTesting;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class CacheValueTest implements HashCodeEqualsDefinedTesting2<CacheValue>,
    ToStringTesting<CacheValue>,
    TreePrintableTesting {

    private final static CacheKey KEY = CacheKey.with("key123");

    private final static Optional<Object> VALUE = Optional.of("Hello");

    @Test
    public void testWithWithNullKeyFails() {
        assertThrows(
            NullPointerException.class,
            () -> CacheValue.with(
                null,
                VALUE
            )
        );
    }

    @Test
    public void testWithWithNullValueFails() {
        assertThrows(
            NullPointerException.class,
            () -> CacheValue.with(
                KEY,
                null
            )
        );
    }

    @Test
    public void testWith() {
        final CacheValue cacheValue = CacheValue.with(
            KEY,
            VALUE
        );
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

    // setKey...........................................................................................................

    @Test
    public void testSetKeyWithNullFails() {
        assertThrows(
            NullPointerException.class,
            () -> this.createObject().setKey(null)
        );
    }

    @Test
    public void testSetKeyWithSame() {
        final CacheValue cacheValue = CacheValue.with(
            KEY,
            VALUE
        );

        assertSame(
            cacheValue,
            cacheValue.setKey(KEY)
        );
    }

    @Test
    public void testSetKeyWithDifferent() {
        final CacheValue cacheValue = CacheValue.with(
            KEY,
            VALUE
        );

        final CacheKey differentKey = CacheKey.with("Different");

        final CacheValue different = cacheValue.setKey(differentKey);
        assertNotSame(
            cacheValue,
            different
        );

        this.keyAndCheck(
            different,
            differentKey
        );
    }

    private void keyAndCheck(final CacheValue value,
                             final CacheKey expected) {
        this.checkEquals(
            expected,
            value.key()
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
        final CacheValue cacheValue = CacheValue.with(
            KEY,
            VALUE
        );

        assertSame(
            cacheValue,
            cacheValue.setValue(VALUE)
        );
    }

    @Test
    public void testSetValueWithDifferent() {
        final CacheValue cacheValue = CacheValue.with(
            KEY,
            VALUE
        );

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

    @Test
    public void testEqualsDifferentKey() {
        this.checkNotEquals(
            CacheValue.with(
                CacheKey.with("different123"),
                VALUE
            )
        );
    }

    @Test
    public void testEqualsDifferentValue() {
        this.checkNotEquals(
            CacheValue.with(
                KEY,
                Optional.of(
                    "different " + VALUE
                )
            )
        );
    }

    @Override
    public CacheValue createObject() {
        return CacheValue.with(
            KEY,
            VALUE
        );
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createObject(),
            "key123=\"Hello\""
        );
    }

    // TreePrintable....................................................................................................

    @Test
    public void testPrintTree() {
        this.treePrintAndCheck(
            this.createObject(),
            "key123\n" +
                "  \"Hello\"\n"
        );
    }

    // class............................................................................................................

    @Override
    public Class<CacheValue> type() {
        return CacheValue.class;
    }
}
