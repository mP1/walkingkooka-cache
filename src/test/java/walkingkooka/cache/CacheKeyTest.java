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
import walkingkooka.naming.NameTesting2;
import walkingkooka.reflect.ClassTesting2;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.text.CaseSensitivity;

public final class CacheKeyTest implements ClassTesting2<CacheKey>,
    NameTesting2<CacheKey, CacheKey> {

    @Override
    public void testTypeNaming() {
        throw new UnsupportedOperationException();
    }

    // Comparator ......................................................................................................

    @Test
    public void testSort() {
        final CacheKey a1 = CacheKey.with("a1");
        final CacheKey b2 = CacheKey.with("B2");
        final CacheKey c3 = CacheKey.with("C3");
        final CacheKey d4 = CacheKey.with("d4");

        this.compareToArraySortAndCheck(
            d4, c3, a1, b2,
            b2, c3, a1, d4
        );
    }

    @Override
    public CacheKey createName(final String name) {
        return CacheKey.with(name);
    }

    @Override
    public CaseSensitivity caseSensitivity() {
        return CacheKey.CASE_SENSITIVITY;
    }

    @Override
    public String nameText() {
        return "hello";
    }

    @Override
    public String differentNameText() {
        return "different";
    }

    @Override
    public String nameTextLess() {
        return "abc";
    }

    @Override
    public int minLength() {
        return CacheKey.MIN_LENGTH;
    }

    @Override
    public int maxLength() {
        return CacheKey.MAX_LENGTH;
    }

    @Override
    public String possibleValidChars(final int position) {
        return 0 == position ?
            ASCII_LETTERS :
            ASCII_LETTERS_DIGITS + ".";
    }

    @Override
    public String possibleInvalidChars(final int position) {
        return CONTROL + BYTE_NON_ASCII;
    }

    // class............................................................................................................

    @Override
    public Class<CacheKey> type() {
        return CacheKey.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}