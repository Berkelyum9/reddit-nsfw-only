# Reddit NSFW Only

An experimental Morphe patch bundle for Reddit Android that deliberately does
the inverse of `warleysr/reddit-nsfw-blocker`.

## Add to Morphe

Add this repository as a **Remote** patch source:

```text
https://github.com/nathan8ate-droid/reddit-nsfw-only
```

Morphe resolves `patches-bundle.json` from `main` and can update the source
automatically when a newer bundle is published.

## Behaviour

- Forces **Show mature content (I'm over 18)** on locally and attempts to sync it
  through Reddit's own preference repository.
- Includes mature content in search.
- Forces NSFW image blurring off and safe search off.
- **Modern Home:** filters Reddit's GraphQL cell response before it becomes feed
  elements, keeping only posts carrying Reddit's post-level NSFW indicator.
- **Legacy/listing-backed screens:** keeps `Link` objects only when
  `getOver18()` is true.
- Strict/fail-closed policy: unknown/unreadable Home edges are removed rather
  than allowed through. A page with zero confirmed NSFW posts may therefore be
  empty instead of leaking one SFW post.

## Why v0.2 changed the feed hook

v0.1 filtered `Listing` / mapped `FeedData` models. Reverse-engineering and
on-device research in `variablenine/morphe-patches` shows modern Reddit Home is
GraphQL cell-backed and the mapped feed elements no longer carry an NSFW flag.
The post-level marker still exists earlier in the response as
`CellIndicatorType.NSFW` / `IndicatorType.NSFW`.

v0.2 therefore hooks the Home page builder and identifies a post by its `t3_`
fullname plus an NSFW enum whose type also contains `ORIGINAL`,
`QUARANTINED` and `SPOILER`. This avoids false positives from unrelated
Reddit enums that also contain an `NSFW` member.

## Supported target

Reddit `2026.39.0` (`com.reddit.frontpage`).

## Compatibility with other Morphe patches

This source is intended to be used alongside normal Morphe Reddit patches. The
official **Hide ads** patch also modifies the legacy `Listing` constructor;
the modern Home hook added here is a separate GraphQL page-builder path.

## Build and publishing

GitHub Actions runs on pushes to `main` (and can be started manually), runs the Android-free scanner/filter tests, builds the Morphe bundle, verifies it contains `classes.dex`, uploads the workflow artifact, and publishes the matching `.mpp` as a GitHub Release asset used by `patches-bundle.json`.

## Origin / license

Modified derivative / adapted GPLv3 work from:

- https://github.com/warleysr/reddit-nsfw-blocker
- https://github.com/MorpheApp/morphe-patches
- https://github.com/variablenine/morphe-patches

See `LICENSE` and `NOTICE`.
