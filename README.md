# Reddit NSFW Only

**Maintainer:** [Berkelyum9](https://github.com/Berkelyum9)

**Development assistance:** This project has been developed with assistance from [ChatGPT by OpenAI](https://chatgpt.com/), including troubleshooting, code changes, and GitHub workflow support. Project ownership and maintenance remain with Berkelyum9.

Reddit NSFW Only is an independently maintained Morphe patch bundle for Reddit on Android. Its goal is to show NSFW posts only, rather than block NSFW posts.

## Add to Morphe

Use this repository as the remote patch source:

```text
https://github.com/Berkelyum9/reddit-nsfw-only
```

Morphe uses the bundle metadata in this repository to identify and update the patch source.

## Current behavior

- Enables Reddit's mature-content preference where supported.
- Attempts to keep only confirmed NSFW posts in the modern Home feed.
- Includes a legacy listing filter for posts with Reddit's NSFW flag.
- Uses a fail-closed approach in the modern Home filter: posts that cannot be identified as NSFW may be removed.

**Important:** Home-feed filtering is the currently confirmed behavior on the tested setup. Filtering in Popular, Latest, subreddit pages, user profiles, search, and every other feed is still experimental and has not been confirmed across all screens. An empty feed can occur if the patch cannot identify NSFW posts.

## Target version

- Reddit Android: `2026.39.0`
- Package: `com.reddit.frontpage`

Compatibility with newer Reddit versions must be tested before it can be considered supported.

## Compatibility

This patch source is intended to be used alongside other Morphe patches. Check for package/provider conflicts if you install multiple modified Reddit builds.

## Build and releases

This repository's GitHub Actions workflow builds the Morphe patch bundle and publishes release assets. Stable and experimental releases are available from the [Releases page](https://github.com/Berkelyum9/reddit-nsfw-only/releases).

- [Source code](https://github.com/Berkelyum9/reddit-nsfw-only)
- [Report a problem](https://github.com/Berkelyum9/reddit-nsfw-only/issues)

## Credits and license

This project adapts and builds on prior open-source work, including:

- [reddit-nsfw-blocker by warleysr](https://github.com/warleysr/reddit-nsfw-blocker)
- [Morphe patches](https://github.com/MorpheApp/morphe-patches)
- [Morphe patches by variablenine](https://github.com/variablenine/morphe-patches)

This repository is maintained independently by **Berkelyum9**. Attribution and licensing obligations for upstream work remain in effect. See `LICENSE` and `NOTICE`.
