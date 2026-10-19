# Reddit NSFW Only

An independently maintained Morphe patch bundle for Reddit Android. This project
maintains its own code changes, builds, and releases; it does not download its
patch bundle from the original derivative repository.

## Add to Morphe

Add this repository as a **Remote** patch source:

```text
https://github.com/Berkelyum9/reddit-nsfw-only
```

Morphe reads this project's `patches-bundle.json` and downloads the release
asset published by this repository.

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

## Compatibility

Target app: Reddit Android (`com.reddit.frontpage`).

Compatibility with newer Reddit versions is experimental. Reddit can rename or
restructure methods, and required fingerprints may fail. A successful build
only verifies compilation; each target version needs APK-level fingerprint and
on-device feed checks before it can be called verified.

The expanded Popular/Latest hooks on the development branch are experimental
and are not yet confirmed to filter those screens correctly.

## Compatibility with other Morphe patches

This source is intended to be used alongside normal Morphe Reddit patches. The
official **Hide ads** patch also modifies the legacy `Listing` constructor;
the modern Home hook added here is a separate GraphQL page-builder path.

## Build and publishing

GitHub Actions runs tests, builds the Morphe bundle, verifies it contains
`classes.dex`, uploads the workflow artifact, and publishes release assets
from this repository. Older releases should remain available as separate
versioned releases.

## Origin / license

This is an independently maintained derivative, not a copy of another
project's release pipeline. Portions of the code are derived from GPLv3 work;
required upstream attribution and licence notices are preserved in `LICENSE`
and `NOTICE`:

- https://github.com/warleysr/reddit-nsfw-blocker
- https://github.com/MorpheApp/morphe-patches
- https://github.com/variablenine/morphe-patches

This project is not affiliated with or endorsed by those projects or Reddit.
