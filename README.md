# Reddit NSFW Only

An independent Morphe patch project maintained in **Berkelyum9/reddit-nsfw-only**.
It keeps only posts that Reddit positively identifies as 18+ content.

## Current compatibility test

- Target APK: Reddit Android **2026.24.0**
- Package: `com.reddit.frontpage`
- Test version: **0.2.6**
- Status: experimental; a successful build does not by itself prove the patch applies or filters every screen correctly.

## Intended behaviour

- Preserve the working Home-feed filtering behaviour.
- Keep only confirmed NSFW posts in supported GraphQL feeds, including Search, Latest, Subreddit feeds and profile submissions when their actual mapper is found.
- Apply the same strict NSFW-only policy to legacy listing-backed screens where the legacy constructor is present.
- Attempt to enable mature content, disable NSFW blur and turn off safe search. These preference hooks are optional so a changed settings method does not prevent the feed filter from applying.

Unknown feed mappers are logged/skipped rather than treated as proof that a screen is filtered. The target APK must still be tested in Morphe to confirm runtime compatibility.

## Build and test

Pushes to `feature/reddit-2026.24` run the unit tests, build the Morphe bundle, and verify that the resulting `.mpp` contains DEX code. The workflow artifact is a test build, not a claim of verified runtime compatibility.

## Project origin and license

This repository is maintained separately by **Berkelyum9**. It contains derivative/adapted GPLv3 work from:

- https://github.com/warleysr/reddit-nsfw-blocker
- https://github.com/MorpheApp/morphe-patches
- https://github.com/variablenine/morphe-patches

See `LICENSE` and `NOTICE` for applicable terms.
