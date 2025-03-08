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

import java.util.Optional;

public class TreeMapStoreCacheStoreTest implements CacheStoreTesting<TreeMapStoreCacheStore> {

    @Test
    public void testSaveAndLoad() {
        final TreeMapStoreCacheStore store = this.createStore();

        final CacheValue value = this.value();

        store.save(value);

        this.loadAndCheck(
            store,
            value.key(),
            value
        );
    }

    @Override
    public TreeMapStoreCacheStore createStore() {
        return TreeMapStoreCacheStore.empty();
    }

    @Override
    public CacheKey id() {
        return CacheKey.with("key123");
    }

    @Override
    public CacheValue value() {
        return CacheValue.with(
            this.id(),
            Optional.of("Value456")
        );
    }

    // class............................................................................................................

    @Override
    public Class<TreeMapStoreCacheStore> type() {
        return TreeMapStoreCacheStore.class;
    }
}
