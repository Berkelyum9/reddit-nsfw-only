# Reddit NSFW Only

**Maintainer:** [Berkelyum9](https://github.com/Berkelyum9)

**Development assistance:** This project has been developed with assistance from [ChatGPT by OpenAI](https://chatgpt.com/), including troubleshooting, code changes, and GitHub workflow support. Project ownership and maintenance remain with Berkelyum9.

Reddit NSFW Only is an independently maintained Morphe patch bundle for Reddit on Android. Its goal is to show NSFW posts only, rather than block NSFW posts. This project maintains its own code changes, builds, and releases.

## Add to Morphe

Use this repository as the remote patch source:

```text
https://github.com/Berkelyum9/reddit-nsfw-only
```

Morphe reads this project's `patches-bundle.json` and downloads the release asset published by this repository.

## Current behavior

- Enables Reddit's mature-content preference where supported.
- Attempts to keep only confirmed NSFW posts in the modern Home feed.
- Includes a legacy listing filter for posts with Reddit's NSFW flag.
- The experimental branch adds optional hooks intended for Popular, Latest, Search, All, and subreddit feeds.
- Uses a fail-closed approach in the modern Home filter: posts that cannot be identified as NSFW may be removed.

**Important:** Home-feed filtering is the currently confirmed behavior on the tested setup. Filtering in Popular, Latest, subreddit pages, user profiles, search, and every other feed remains experimental and is not yet confirmed across all screens. A successful build confirms compilation, not correct on-device behavior.

## Target version

- Reddit Android: `2026.39.0`
- Package: `com.reddit.frontpage`

Compatibility with newer Reddit versions must be tested before it can be considered supported.

## Compatibility with other Morphe patches

This source is intended to be used alongside other Morphe patches. Check for package/provider conflicts if you install multiple modified Reddit builds.

## Build and releases

GitHub Actions builds the Morphe patch bundle and publishes versioned release assets. Existing releases should remain available rather than being overwritten. See the [Releases page](https://github.com/Berkelyum9/reddit-nsfw-only/releases).

## Credits and license

This project adapts and builds on prior open-source work, including:

- [reddit-nsfw-blocker by warleysr](https://github.com/warleysr/reddit-nsfw-blocker)
- [Morphe patches](https://github.com/MorpheApp/morphe-patches)
- [Morphe patches by variablenine](https://github.com/variablenine/morphe-patches)

This repository is maintained independently by **Berkelyum9**. Attribution and licensing obligations for upstream work remain in effect. See `LICENSE` and `NOTICE`. This project is not affiliated with or endorsed by those projects or Reddit.
