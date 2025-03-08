[![Build Status](https://github.com/mP1/walkingkooka-cache/actions/workflows/build.yaml/badge.svg)](https://github.com/mP1/walkingkooka-cache/actions/workflows/build.yaml/badge.svg)
[![Coverage Status](https://coveralls.io/repos/github/mP1/walkingkooka-cache/badge.svg?branch=master)](https://coveralls.io/repos/github/mP1/walkingkooka-cache?branch=master)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/mP1/walkingkooka-cache.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/walkingkooka-cache/context:java)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/mP1/walkingkooka-cache.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/walkingkooka-cache/alerts/)
![](https://tokei.rs/b1/github/mP1/walkingkooka-cache)
[![J2CL compatible](https://img.shields.io/badge/J2CL-compatible-brightgreen.svg)](https://github.com/mP1/j2cl-central)

Not intended to be a complex or network aware replicating cache. Just a few simple class/interface definitions that should
suffice for a cache that is intended to be sticky and stuck to a single instance. Caches are of course not long term,
and entries could and should expire to avoid growing and requiring too much memory.

Initially there will probably be just a store that leverages a [TreeMapStore](https://github.com/mP1/walkingkooka-store/blob/master/src/main/java/walkingkooka/store/Stores.java)
and a background thread which periodically iterates thru all entries and removes those that have expired.